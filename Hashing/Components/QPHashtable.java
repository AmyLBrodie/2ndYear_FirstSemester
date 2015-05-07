
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
        if (table[value] == null){
            return false;
        }
        else if (table[value] != null && table[value].isEntryFor(word)){
            return true;
        }
        else if (table[value] != null && ! table[value].isEntryFor(word)){
            int i = 1, previousValue;
            while (table[value] != null && ! table[value].isEntryFor(word)){
                previousValue = value;
                value = previousValue + 2*i - 1;
                if (value > table.length){
                    value -= table.length;
                }
                if (value == hashFunction(word)){
                    break;
                }
            }
            if (table[value]!= null && table[value].isEntryFor(word)){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
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
        int probes = 0;
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
                if (value > table.length){
                    value -= table.length;
                }
                i += 1;
                probes += 1;
            }
            table[value] = new EntryImpl(word, definition);
            entries +=1;
        }
        
        if (loadFactor() > 0.5){
            rehash();
        }
        
        if (probes > table.length){
            IllegalStateException("Number of probes has exceeded the table size.");
        }
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
        System.out.println(s);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
