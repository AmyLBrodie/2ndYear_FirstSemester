
import java.util.List;


/**
 *
 * @author Amy Brodie
 * @version 07/05/2015
 */
public class SCHashtable implements Dictionary{
    private final static int DEFAULT_SIZE = 50;
 
    private ChainedEntry[] table;
    private int entries;
    private int probes;
    private int iProbes;
    private int sProbes;
 
    public SCHashtable() { this(DEFAULT_SIZE); }
    
    public SCHashtable(int size) { 
        this.table = new ChainedEntry[size];
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
        boolean flag = false;
        int value = hashFunction(word);
        probes = 0;
        if (table[value] == null){
            flag = false;
        }
        else if (table[value] != null && table[value].isEntryFor(word)){
            flag = true;
        }
        else if (table[value] != null && ! table[value].isEntryFor(word)){
            while (table[value].hasNext()){
                if (table[value].getNext().isEntryFor(word)){
                    flag = true;
                    break;
                }
                probes += 1;
                table[value] = table[value].getNext();
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
                    while (table[value].hasNext()){
                        if (table[value].getNext().isEntryFor(word)){
                            return table[value].getDefinitions();
                        }
                        table[value] = table[value].getNext();
                    }
                }
            }
        }
        return null;
    }
    
    public void insert(String word, Definition definition) {        
        // Implement this.
        int value = hashFunction(word);
        probes = 0;
        if (table[value] == null){
            table[value] = new ChainedEntryImpl(word, definition);
            entries +=1;
        }
        else if (table[value] != null && table[value].isEntryFor(word)){
            table[value].addDefinition(definition);
        }
        else if (table[value] != null && ! table[value].isEntryFor(word)){
            table[value] = new ChainedEntryImpl(word, definition, (ChainedEntryImpl) table[value]);
            entries +=1;
            probes += 1;
        }
        
        setInsertProbeSum(probes);
    }
        
    public boolean isEmpty() { return entries == 0; }
    
    public void empty() { this.table = new ChainedEntry[this.table.length]; this.entries=0; }
    
    public int size() { return this.entries; }
    
    /* Hash Table Functions */
    
    /**
     * Obtain the current load factor (entries / table size).
     */
    public double loadFactor() { return entries/(double)table.length; }
    
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
