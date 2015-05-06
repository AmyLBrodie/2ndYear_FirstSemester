import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
/**
 * Module containing utility methods.
 * 
 * @author Stephan Jamieson
 * @version 24/4/2015
 */
public class FileUtil {

    private FileUtil() {}
    
    
    /**
     * Load the dictionary with the word definitions in the given file. <br>
     * 
     * &lt;lexicion&gt; ::= {<entry>} <br>  
     * &lt;entry&gt; ::=  &lt;word type&gt; ‘:’ &lt;word&gt; ‘:’ [&lt;description&gt;] <br> 
     * &lt;word type&gt; ::= ‘ a’|’v’|’n’ <br>
     * &lt;word&gt; ::=  {&lt;letter&gt;}+ <br>
     * &lt;description&gt; ::=  {&lt;character&gt;} <br>
     * <br>
     * The lexicion contains 0 or more entries. <br>
     * An entry consists of word type followed by a colon, followed by the word, followed by a colon, optionally followed by a description. <br> 
     * The word type is represented by a single character; either ‘a’, ‘v’, or ‘n’. <br>
     * A word consists of a sequence of one or more letters. <br>
     * A description consists of 1 or more characters (generally, it’s a word phrase). <br>
     */
    public static void load(Dictionary dictionary, String filename) throws FileNotFoundException, IOException { 
        // Implement this.
        File file = new File(filename);
        Scanner scan = new Scanner(file);
        
        while (scan.hasNext()){
            String temp = scan.nextLine();
            String wordT = temp.substring(0, temp.indexOf(":"));
            String word = temp.substring(temp.indexOf(":")+1, temp.lastIndexOf(":")).trim();
            String description = temp.substring(temp.lastIndexOf(":"));
            WordType wordType;
            Definition definition;
            
            if (description.length() > 2){
                description = description.substring(description.indexOf(":")+1).trim();
            }
            else{
                description = "";
            }
            
            wordType = WordType.toWordType(wordT);
            
            definition = new Definition(wordType, description);
            dictionary.insert(word, definition);
            
        }
    }
}
