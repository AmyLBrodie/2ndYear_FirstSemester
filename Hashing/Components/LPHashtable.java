import java.util.List;
/**
 * Simple hash table implementation of Dictionary using linear probing.
 * 
 * @author Stephan Jamieson 
 * @version 24/4/2015
 */
public class LPHashtable implements Dictionary
{
    private final static int DEFAULT_SIZE = 50;
 
    private Entry[] table;
    private int entries;
 
    public LPHashtable() { this(DEFAULT_SIZE); }
    
    public LPHashtable(int size) { 
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
            while (table[value] != null && ! table[value].isEntryFor(word)){
                value += 1; 
                if (value > table.length){
                    value = 0;
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
                    while (! table[value].isEntryFor(word)){
                        value += 1; 
                        if (value > table.length){
                            value = 0;
                        }
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
        if (table[value] == null){
            table[value] = new EntryImpl(word, definition);
            entries +=1;
        }
        else if (table[value] != null && table[value].isEntryFor(word)){
            table[value].addDefinition(definition);
        }
        else if (table[value] != null && ! table[value].isEntryFor(word)){
            while (table[value] != null){
                value += 1;
                if (value > table.length){
                    value = 0;
                }
            }
            table[value] = new EntryImpl(word, definition);
            entries +=1;
        }
    }
        
    public boolean isEmpty() { return entries == 0; }
    
    public void empty() { this.table = new Entry[this.table.length]; this.entries=0; }
    
    public int size() { return this.entries; }
    
    /* Hash Table Functions */
    
    /**
     * Obtain the current load factor (entries / table size).
     */
    public double loadFactor() { return entries/(double)table.length; }
        
    
    /* DEBUGGING CODE */
    /**
     * Print the contents of the given hashtable.
     */
    public static void debug_print(LPHashtable hashtable) {
        Entry[] table = hashtable.table;
        for(int i=0; i<table.length; i++) {
            System.out.printf("\n%4d : %s", i, table[i]);
        }
    }
            
}
