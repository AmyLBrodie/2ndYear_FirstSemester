
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author Amy Brodie
 * @version 08/05/2015
 */
public class SearchPerformance {
    
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
    
    public static void main(String[] args) throws FileNotFoundException, IOException{
        String [] list = new String[100];
        ArrayList<String> lexicon = new ArrayList(4997);
        ArrayList<String> nonsense = new ArrayList(100);
        int totalProbesLP1 = 0, totalProbesQP1 = 0, totalProbesSC1 = 0, totalProbesLP2 = 0, totalProbesQP2 = 0, totalProbesSC2 = 0, totalProbesLP3 = 0, totalProbesQP3 = 0, totalProbesSC3 = 0;
        File file = new File("lexicon.txt");
        Scanner scan = new Scanner(file);
        
        //create list of all words from lexicon
        while (scan.hasNext()){
            String temp = scan.nextLine();
            String word = temp.substring(temp.indexOf(":")+1, temp.lastIndexOf(":")).trim();
            lexicon.add(word);
        }
        
        Random randomGenerator = new Random();
        
        for (int i=0; i<100; i++){
            int a = 97;
            int z = 122;
            nonsense.add(Character.toString((char)(a + i)) + Character.toString((char)(z - i)));
        }
        
        System.out.println("Running 100 trials...");
        System.out.println();
        
        // run 100 trials
        for (int j=0; j<100; j++){
        
            // create the list of 100 random words
            for (int i=0; i<20; i++){
                int random = randomGenerator.nextInt(4997);
                list[i] = lexicon.get(random);
            }

            for (int i=20; i<30; i++){
                int random = randomGenerator.nextInt(100);
                list[i] = nonsense.get(random);
            }

            for (int i=30; i<60; i++){
                int random = randomGenerator.nextInt(4997);
                list[i] = lexicon.get(random);
            }

            for (int i=60; i<70; i++){
                int random = randomGenerator.nextInt(100);
                list[i] = nonsense.get(random);
            }

            for (int i=70; i<100; i++){
                int random = randomGenerator.nextInt(4997);
                list[i] = lexicon.get(random);
            }

            Dictionary dictionaryLP = null;
            Dictionary dictionaryQP = null;
            Dictionary dictionarySC = null;

            int value, probesLP, probesQP, probesSC;
            double loadFactorLP, loadFactorQP, loadFactorSC;

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

            for (int i =0; i<100; i++){
                dictionaryLP.containsWord(list[i]);
                dictionaryQP.containsWord(list[i]);
                dictionarySC.containsWord(list[i]);
            }

            probesLP = dictionaryLP.getSearchProbes();
            probesQP = dictionaryQP.getSearchProbes();
            probesSC = dictionarySC.getSearchProbes();

            totalProbesLP1 += probesLP;
            totalProbesQP1 += probesQP;
            totalProbesSC1 += probesSC;

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

            for (int i =0; i<100; i++){
                dictionaryLP.containsWord(list[i]);
                dictionaryQP.containsWord(list[i]);
                dictionarySC.containsWord(list[i]);
            }

            probesLP = dictionaryLP.getSearchProbes();
            probesQP = dictionaryQP.getSearchProbes();
            probesSC = dictionarySC.getSearchProbes();

            totalProbesLP2 += probesLP;
            totalProbesQP2 += probesQP;
            totalProbesSC2 += probesSC;


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
                   // System.err.println("QPHashtable - IllegalStateException: " + e.getMessage());
                }
            } 
            if (loadFactorSC < 0.9 || loadFactorSC > 1.1){
                value = nextPrime((int) (dictionarySC.size() / 1));
                dictionarySC.empty();
                dictionarySC = new SCHashtable(value);
                FileUtil.load(dictionarySC, "lexicon.txt");
            } 

            for (int i =0; i<100; i++){
                dictionaryLP.containsWord(list[i]);
                dictionaryQP.containsWord(list[i]);
                dictionarySC.containsWord(list[i]);
            }

            probesLP = dictionaryLP.getSearchProbes();
            probesQP = dictionaryQP.getSearchProbes();
            probesSC = dictionarySC.getSearchProbes();

            totalProbesLP3 += probesLP;
            totalProbesQP3 += probesQP;
            totalProbesSC3 += probesSC;
        }
        
        double outputAverageLP1, outputAverageQP1, outputAverageSC1, outputAverageLP2, outputAverageQP2, outputAverageSC2, outputAverageLP3, outputAverageQP3, outputAverageSC3;
        
        // calculate averages
        outputAverageLP1 = (double) totalProbesLP1/100;
        outputAverageQP1 = (double) totalProbesQP1/100;
        outputAverageSC1 = (double) totalProbesSC1/100;
        outputAverageLP2 = (double) totalProbesLP2/100;
        outputAverageQP2 = (double) totalProbesQP2/100;
        outputAverageSC2 = (double) totalProbesSC2/100;
        outputAverageLP3 = (double) totalProbesLP3/100;
        outputAverageQP3 = (double) totalProbesQP3/100;
        outputAverageSC3 = (double) totalProbesSC3/100;
        
        double sc_qp1, sc_lp1, qp_lp1, sc_qp2, sc_lp2, qp_lp2, sc_qp3, sc_lp3, qp_lp3;
        
        // calculate percentages
        qp_lp1 = (((double)outputAverageLP1-outputAverageQP1)/outputAverageLP1)*100;
        sc_lp1 = (((double)outputAverageLP1-outputAverageSC1)/outputAverageLP1)*100;
        sc_qp1 = (((double)outputAverageQP1-outputAverageSC1)/outputAverageQP1)*100;
        qp_lp2 = (((double)outputAverageLP2-outputAverageQP2)/outputAverageLP2)*100;
        sc_lp2 = (((double)outputAverageLP2-outputAverageSC2)/outputAverageLP2)*100;
        sc_qp2 = (((double)outputAverageQP2-outputAverageSC2)/outputAverageQP2)*100;
        qp_lp3 = (((double)outputAverageLP3-outputAverageQP3)/outputAverageLP3)*100;
        sc_lp3 = (((double)outputAverageLP3-outputAverageSC3)/outputAverageLP3)*100;
        sc_qp3 = (((double)outputAverageQP3-outputAverageSC3)/outputAverageQP3)*100;
        
        
        // print results to screen
        System.out.println("Results for load factor 0.5: ");
        System.out.println("---------------------------");
        System.out.println("Average Number of Linear Probes: " + Double.toString(Math.round(outputAverageLP1)));
        System.out.println("Average Number of Quadratic Probes: " + Double.toString(Math.round(outputAverageQP1)));
        System.out.println("Average Number of Sequential Chaining Probes: " + Double.toString(Math.round(outputAverageSC1)));
        System.out.println("% difference between quadratic probing and linear probing: " + Double.toString(Math.round(qp_lp1)) + "%");
        System.out.println("% difference between sequential chaining and linear probing: " + Double.toString(Math.round(sc_lp1))+ "%");
        System.out.println("% difference between sequential chaining and quadratic probing: " + Double.toString(Math.round(sc_qp1))+ "%");
        System.out.println();
        
        System.out.println("Results for load factor 0.75: ");
        System.out.println("---------------------------");
        System.out.println("Average Number of Linear Probes: " + Double.toString(Math.round(outputAverageLP2)));
        System.out.println("Average Number of Quadratic Probes: " + Double.toString(Math.round(outputAverageQP2)));
        System.out.println("Average Number of Sequential Chaining Probes: " + Double.toString(Math.round(outputAverageSC2)));
        System.out.println("% difference between quadratic probing and linear probing: " + Double.toString(Math.round(qp_lp2)) + "%");
        System.out.println("% difference between sequential chaining and linear probing: " + Double.toString(Math.round(sc_lp2))+ "%");
        System.out.println("% difference between sequential chaining and quadratic probing: " + Double.toString(Math.round(sc_qp2))+ "%");
        System.out.println();
        
        System.out.println("Results for load factor 1: ");
        System.out.println("---------------------------");
        System.out.println("Average Number of Linear Probes: " + Double.toString(Math.round(outputAverageLP3)));
        System.out.println("Average Number of Quadratic Probes: " + Double.toString(Math.round(outputAverageQP3)));
        System.out.println("Average Number of Sequential Chaining Probes: " + Double.toString(Math.round(outputAverageSC3)));
        System.out.println("% difference between quadratic probing and linear probing: " + Double.toString(Math.round(qp_lp3)) + "%");
        System.out.println("% difference between sequential chaining and linear probing: " + Double.toString(Math.round(sc_lp3))+ "%");
        System.out.println("% difference between sequential chaining and quadratic probing: " + Double.toString(Math.round(sc_qp3))+ "%");
        System.out.println();
    }
}
