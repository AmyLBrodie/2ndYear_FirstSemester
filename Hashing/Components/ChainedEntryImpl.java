


/**
 *
 * @author Amy Brodie
 * @version 07/05/2015
 */
public class ChainedEntryImpl extends EntryImpl implements ChainedEntry{
    
    private ChainedEntryImpl next;
    
    public ChainedEntryImpl (String word, Definition definition){
        super(word, definition);
        next = null;
    }
    
    public ChainedEntryImpl (String word, Definition definition, ChainedEntryImpl node){
        super(word, definition);
        next = node;
    }
    
    public ChainedEntryImpl (Entry entry){
        super(entry);
    }
    
    public ChainedEntry getNext(){
        return next;
    }
    
    public boolean hasNext(){
        if (next == null){
            return false;
        }
        else {
            return true;
        }
    }
    
    
    
}
