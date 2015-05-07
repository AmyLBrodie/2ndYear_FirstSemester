
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author Amy Brodie
 * @version 07/05/2015
 */
public class LoadPerformance {
    
    private  static int nextPrime(int n){
        while (! isPrime(n)){
            n += 1;
        }
        return n;
    }
    
    private static boolean isPrime(int n){
        for (int i=2; i<Math.sqrt(n) + 1;i++){
            if (n % i == 0 && n != i){
                return false;
            }
        }
        return true;
    }
    
    
    public static void main(String[] args) throws IOException{
        
        Dictionary dictionaryLP = null;
        Dictionary dictionaryQP = null;
        Dictionary dictionarySC = null;
        
        int value, probesLP, probesQP, probesSC;
        double loadFactorLP, loadFactorQP, loadFactorSC, sc_qp, sc_lp, qp_lp;
    /*=========================================================================
     * Load Factor 0.5
     *==========================================================================*/

        dictionaryLP = new LPHashtable(7481);
        dictionaryQP = new QPHashtable(7481);
        dictionarySC = new SCHashtable(3739);

        FileUtil.load(dictionaryLP, "lexicon.txt");
        FileUtil.load(dictionaryQP, "lexicon.txt");
        FileUtil.load(dictionarySC, "lexicon.txt");

        loadFactorLP = dictionaryLP.size()/7481.0;
        loadFactorQP = dictionaryQP.size()/7481.0;
        loadFactorSC = dictionarySC.size()/3739.0;

        if (loadFactorLP < 0.4 || loadFactorLP > 0.6){
            value = nextPrime((int) (dictionaryLP.size() / 0.5));
            dictionaryLP.empty();
            dictionaryLP = new LPHashtable(value);
            FileUtil.load(dictionaryLP, "lexicon.txt");
        }
        if (loadFactorQP < 0.4 || loadFactorQP > 0.6){
            value = nextPrime((int) (dictionaryQP.size() / 0.5));
            dictionaryQP.empty();
            dictionaryQP = new QPHashtable(value);
            FileUtil.load(dictionaryQP, "lexicon.txt");
        } 
        if (loadFactorSC < 0.4 || loadFactorSC > 0.6){
            value = nextPrime((int) (dictionarySC.size() / 0.5));
            dictionarySC.empty();
            dictionarySC = new SCHashtable(value);
            FileUtil.load(dictionarySC, "lexicon.txt");
        } 

        probesLP = dictionaryLP.getInsertProbes();
        probesQP = dictionaryQP.getInsertProbes();
        probesSC = dictionarySC.getInsertProbes();

        System.out.println("Results for load factor 0.5: ");
        System.out.println("-----------------------------");
        System.out.println("Linear Probing = " + probesLP);
        System.out.println("Quadratic Probing = " + probesQP);
        System.out.println("Sequential Chaining = " + probesSC);
        System.out.println("-----------------------------");
        
        qp_lp = (((double)probesLP-probesQP)/probesLP)*100;
        sc_lp = (((double)probesLP-probesSC)/probesLP)*100;
        sc_qp = (((double)probesQP-probesSC)/probesQP)*100;
        
        System.out.println("% difference between quadratic probing and linear probing: " + Double.toString(Math.round(qp_lp)) + "%");
        System.out.println("% difference between sequential chaining and linear probing: " + Double.toString(Math.round(sc_lp))+ "%");
        System.out.println("% difference between sequential chaining and quadratic probing: " + Double.toString(Math.round(sc_qp))+ "%");
        System.out.println();
        
        
        

    /*=========================================================================
     * Load Factor 0.75
     *==========================================================================*/

        dictionaryLP = new LPHashtable(7481);
        dictionaryQP = new QPHashtable(7481);
        dictionarySC = new SCHashtable(3739);

        FileUtil.load(dictionaryLP, "lexicon.txt");
        FileUtil.load(dictionaryQP, "lexicon.txt");
        FileUtil.load(dictionarySC, "lexicon.txt");

        loadFactorLP = dictionaryLP.size()/7481.0;
        loadFactorQP = dictionaryQP.size()/7481.0;
        loadFactorSC = dictionarySC.size()/3739.0;

        if (loadFactorLP < 0.7 || loadFactorLP > 0.8){
            value = nextPrime((int) (dictionaryLP.size() / 0.75));
            dictionaryLP.empty();
            dictionaryLP = new LPHashtable(value);
            FileUtil.load(dictionaryLP, "lexicon.txt");
        }
        if (loadFactorQP < 0.7 || loadFactorQP > 0.8){
            value = nextPrime((int) (dictionaryQP.size() / 0.75));
            dictionaryQP.empty();
            dictionaryQP = new QPHashtable(value);
            FileUtil.load(dictionaryQP, "lexicon.txt");
        } 
        if (loadFactorSC < 0.7 || loadFactorSC > 0.8){
            value = nextPrime((int) (dictionarySC.size() / 0.75));
            dictionarySC.empty();
            dictionarySC = new SCHashtable(value);
            FileUtil.load(dictionarySC, "lexicon.txt");
        } 

        probesLP = dictionaryLP.getInsertProbes();
        probesQP = dictionaryQP.getInsertProbes();
        probesSC = dictionarySC.getInsertProbes();

        System.out.println("Results for load factor 0.75: ");
        System.out.println("------------------------------");
        System.out.println("Linear Probing = " + probesLP);
        System.out.println("Quadratic Probing = " + probesQP);
        System.out.println("Sequential Chaining = " + probesSC);
        System.out.println("-----------------------------");
        
        qp_lp = (((double)probesLP-probesQP)/probesLP)*100;
        sc_lp = (((double)probesLP-probesSC)/probesLP)*100;
        sc_qp = (((double)probesQP-probesSC)/probesQP)*100;
        
        System.out.println("% difference between quadratic probing and linear probing: " + Double.toString(Math.round(qp_lp)) + "%");
        System.out.println("% difference between sequential chaining and linear probing: " + Double.toString(Math.round(sc_lp))+ "%");
        System.out.println("% difference between sequential chaining and quadratic probing: " + Double.toString(Math.round(sc_qp))+ "%");
        System.out.println();


    /*=========================================================================
     * Load Factor 1
     *==========================================================================*/

        dictionaryLP = new LPHashtable(7481);
        dictionaryQP = new QPHashtable(7481);
        dictionarySC = new SCHashtable(3739);

        FileUtil.load(dictionaryLP, "lexicon.txt");
        FileUtil.load(dictionaryQP, "lexicon.txt");
        FileUtil.load(dictionarySC, "lexicon.txt");

        loadFactorLP = dictionaryLP.size()/7481.0;
        loadFactorQP = dictionaryQP.size()/7481.0;
        loadFactorSC = dictionarySC.size()/3739.0;

        if (loadFactorLP < 0.9 || loadFactorLP > 1.1){
            value = nextPrime((int) (dictionaryLP.size() / 1));
            dictionaryLP.empty();
            dictionaryLP = new LPHashtable(value);
            FileUtil.load(dictionaryLP, "lexicon.txt");
        }
        if (loadFactorQP < 0.9 || loadFactorQP > 1.1){
            value = nextPrime((int) (dictionaryQP.size() / 1));
            dictionaryQP.empty();
            dictionaryQP = new QPHashtable(value);
            try{
                FileUtil.load(dictionaryQP, "lexicon.txt");
            }
            catch(IllegalStateException e){
                System.err.println("QPHashtable - IllegalStateException: " + e.getMessage());
            }
        } 
        if (loadFactorSC < 0.9 || loadFactorSC > 1.1){
            value = nextPrime((int) (dictionarySC.size() / 1));
            dictionarySC.empty();
            dictionarySC = new SCHashtable(value);
            FileUtil.load(dictionarySC, "lexicon.txt");
        } 

        probesLP = dictionaryLP.getInsertProbes();
        probesQP = dictionaryQP.getInsertProbes();
        probesSC = dictionarySC.getInsertProbes();

        System.out.println("Results for load factor 1: ");
        System.out.println("---------------------------");
        System.out.println("Linear Probing = " + probesLP);
        System.out.println("Quadratic Probing = " + probesQP);
        System.out.println("Sequential Chaining = " + probesSC);
        System.out.println("-----------------------------");
        
        qp_lp = (((double)probesLP-probesQP)/probesLP)*100;
        sc_lp = (((double)probesLP-probesSC)/probesLP)*100;
        sc_qp = (((double)probesQP-probesSC)/probesQP)*100;
        
        System.out.println("% difference between quadratic probing and linear probing: " + Double.toString(Math.round(qp_lp)) + "%");
        System.out.println("% difference between sequential chaining and linear probing: " + Double.toString(Math.round(sc_lp))+ "%");
        System.out.println("% difference between sequential chaining and quadratic probing: " + Double.toString(Math.round(sc_qp))+ "%");
        System.out.println();
        
        
        

        
    }
}
