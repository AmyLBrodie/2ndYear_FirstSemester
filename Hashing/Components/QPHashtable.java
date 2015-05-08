
import java.util.List;


/**
 *
 * @author Amy Brodie
 * @version 07/05/2015
 */
public class QPHashtable implements Dictionary{
    private final static int DEFAULT_SIZE = 50;
 
    private Entry[] table;
    private int entries;
    private int iProbes;
    private int sProbes;
    private int probes;
 
    public QPHashtable() { this(DEFAULT_SIZE); }
    
    public QPHashtable(int size) { 
        this.table = new Entry[size];
        this.entries = 0;
    }
    

    private int hashFunction(String key) {
        // Your hash function here.
        int hashValue = 0;
        
        for (int i=0; i<key.length(); i++){
            hashValue = hashValue * 37 + key.charAt(i);
        }
        
        hashValue = hashValue%table.length;
        
        if (hashValue<0){
            hashValue += table.length;
        }
        
        return hashValue;
    }
    
    
    public boolean containsWord(String word) {
        // Implement this.
        int value = hashFunction(word);
        boolean flag = false;
        probes = 0;
        if (table[value] == null){
            flag = false;
        }
        else if (table[value] != null && table[value].isEntryFor(word)){
            flag = true;
        }
        else if (table[value] != null && ! table[value].isEntryFor(word)){
            int i = 1, previousValue;
            while (table[value] != null && ! table[value].isEntryFor(word)){
                previousValue = value;
                value = previousValue + 2*i - 1;
                if (value > table.length-1){
                    value -= table.length;
                }
                if (value == hashFunction(word)){
                    break;
                }
                probes += 1;
            }
            if (table[value]!= null && table[value].isEntryFor(word)){
                flag = true;
            }
            else{
                flag =  false;
            }
        }
        setSearchProbeSum(probes);
        return flag;
    }
    
    public List<Definition> getDefinitions(String word) {
        // Implement this.
        int value = hashFunction(word);
        if (entries != 0){
            if (containsWord(word)){
                if (table[value].isEntryFor(word)){
                    return table[value].getDefinitions();
                }
                else if (! table[value].isEntryFor(word)){
                    int i = 1, previousValue;
                    while (! table[value].isEntryFor(word)){
                        previousValue = value;
                        value = previousValue + 2*i - 1;
                        if (value > table.length){
                            value -= table.length;
                        }
                        i += 1;
                    }
                    return table[value].getDefinitions();
                }
            }
        }
        return null;
    }
    
    public void insert(String word, Definition definition) {        
        // Implement this.
        int value = hashFunction(word);
        boolean flag = false;
        probes = 0;
        if (table[value] == null){
            table[value] = new EntryImpl(word, definition);
            entries +=1;
        }
        else if (table[value] != null && table[value].isEntryFor(word)){
            table[value].addDefinition(definition);
        }
        else if (table[value] != null && ! table[value].isEntryFor(word)){
            int i = 1, previousValue;
            while (table[value] != null){
                previousValue = value;
                value = previousValue + 2*i - 1;
                if (value > table.length-1){
                    value %= table.length;
                }
                if (table[value] != null && table[value].isEntryFor(word)){
                    table[value].addDefinition(definition);
                    probes += 1;
                    flag = true;
                    break;
                }
                i += 1;
                probes += 1;
                if (probes > table.length-1){
                    throw new IllegalStateException("Number of probes has exceeded the table size.");
                }
            }
            if (! flag){
                table[value] = new EntryImpl(word, definition);
                entries +=1;
            }
        }
        
        if (loadFactor() > 0.5){
            rehash();
        }
        if (probes > table.length-1){
            System.out.println("#");
            throw new IllegalStateException("Number of probes has exceeded the table size.");
        }
        setInsertProbeSum(probes);
    }
    
    public void rehash(){
        Entry[] oldTable = new Entry[table.length];
        for (int i=0; i<table.length; i++){
            if (table[i] != null){
                oldTable[i] = new EntryImpl(table[i]);
            }
        }
        
        
        empty();
        table = new Entry[nextPrime(4*table.length)];
        
        for (int i=0; i<oldTable.length; i++){
            if (oldTable[i] != null){
                List<Definition> temp = oldTable[i].getDefinitions();
                for (int j=0; j<temp.size(); j++){
                    insert(oldTable[i].getWord(), temp.get(j));
                }
            }
        }
        
    }
    
    private int nextPrime(int n){
        while (! isPrime(n)){
            n += 1;
        }
        return n;
    }
    
    private boolean isPrime(int n){
        for (int i=2; i<Math.sqrt(n) + 1;i++){
            if (n % i == 0 && n != i){
                return false;
            }
        }
        return true;
    }
        
    public boolean isEmpty() { return entries == 0; }
    
    public void empty() { this.table = new Entry[this.table.length]; this.entries=0; }
    
    public int size() { return this.entries; }
    
    /* Hash Table Functions */
    
    /**
     * Obtain the current load factor (entries / table size).
     */
    public double loadFactor() { return entries/(double)table.length; }

    
    private void IllegalStateException(String s) {
        throw new UnsupportedOperationException(s); 
    }

    
   public void setSearchProbeSum(int probes){
        sProbes += probes;
    }
    
    public void setInsertProbeSum(int probes){
        iProbes += probes;
    }
    
    public Integer getSearchProbes(){
        return sProbes;
    }
    
    public Integer getInsertProbes(){
        return iProbes;
    } 
   
}
