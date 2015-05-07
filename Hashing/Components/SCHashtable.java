
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
        int value = hashFunction(word);
        if (table[value] == null){
            return false;
        }
        else if (table[value] != null && table[value].isEntryFor(word)){
            return true;
        }
        else if (table[value] != null && ! table[value].isEntryFor(word)){
            while (table[value].hasNext()){
                if (table[value].getNext().isEntryFor(word)){
                    return true;
                }
                table[value] = table[value].getNext();
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
        }
    }
        
    public boolean isEmpty() { return entries == 0; }
    
    public void empty() { this.table = new ChainedEntry[this.table.length]; this.entries=0; }
    
    public int size() { return this.entries; }
    
    /* Hash Table Functions */
    
    /**
     * Obtain the current load factor (entries / table size).
     */
    public double loadFactor() { return entries/(double)table.length; }
}
