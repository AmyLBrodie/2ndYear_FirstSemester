
/**
 *
 * @author Amy Brodie
 * @version 07/05/2015
 */
public interface ChainedEntry extends Entry {
    ChainedEntry getNext();
    
    boolean hasNext();
}
