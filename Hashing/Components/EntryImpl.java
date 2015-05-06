
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author amy
 */
public class EntryImpl implements Entry {
    
    private String word;
    private List<Definition> definitions = new ArrayList<Definition>();
    private Definition def;
    
    public EntryImpl (String word, Definition definition){
        this.word = word;
        this.definitions.add(definition);
    }
    
    public String getWord(){
        return word;
    }
    
    public List<Definition> getDefinitions(){
        return definitions;
    }
    
    public void addDefinition(WordType wordType, String description){
        def = new Definition(wordType, description);
        addDefinition(def);
    }
    
    public void addDefinition(Definition definition){
        definitions.add(definition);
    }
    
    public boolean isEntryFor(String word){
        if (this.word == null){
            return false;
        }
        else if (this.word.equals(word)){
            return true;
        }
        else{
            return false;
        }
    }

}
