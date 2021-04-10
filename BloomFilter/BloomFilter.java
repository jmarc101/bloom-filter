/**
 * Devoir 3 - IFT 2015 - Bloom Filters
 * @author Jean-Marc Prud'homme (20137035)
 * @author Jean-Daniel Toupin - 20046724
 */
import java.util.ArrayList;
  /*
  * Utiliser wiki pour trouver les formule de probability, etc
  * https://en.wikipedia.org/wiki/Bloom_filter
  */
public class BloomFilter {

    private BitSet bitSet;
    private int m;
    private ArrayList<HashFunction> hashFunc = new ArrayList<>();
    private int numElement;
    private int[] hashCounter;

    /**
     * Crée un filtre de Bloom basé sur la taille de l'ensemble de bits et du
     * nombre de fonctions de hachage.
     *
     * @param numBits taille de l'ensemble de bits dans le bitset
     * @param numHashes nombre de fonctions de hachage
     */
    public BloomFilter(int numBits, int numHashes) {
        
        //Edge case
        if (numBits == 0){return;}
        
        //Creation du bitSet et fonctions de hashages
        this.m = numBits;
        bitSet = new BitSet(this.m);

        for (int i = 1; i <= numHashes; i++){
            hashFunc.add(new HashFunction(i, numBits));
        }

        //pour tester si les foncgtions sont equiprobables
        this.hashCounter = new int[numBits];
        
    }

    /**
     * Crée un filtre de Bloom basé sur le nombre d'éléments attendus et de la
     * probabilité de faux positifs désirée.
     * @param numElems nombre d'éléments à insérer
     * @param falsePosProb probabilité de faux positifs
     */
    public BloomFilter(int numElems, double falsePosProb) {

        //Edge case
        if (numElems == 0){return;}

        // Calculer le bucketSize optimal et le nombre de formule de hashage
        double mOptimal = Math.ceil(-numElems * Math.log(falsePosProb) / Math.pow(Math.log(2), 2 ));
        double kOptimal = Math.ceil(-Math.log10(falsePosProb)/Math.log10(2));
        this.m = (int)mOptimal;

        //Creation des fonctions
        for (int i = 1; i <= kOptimal; i++){
            hashFunc.add(new HashFunction(i, this.m));
        }

        //Bitset
        bitSet = new BitSet(this.m);
        this.hashCounter = new int[(int)Math.ceil(mOptimal)];


    }

    /**
     * Ajoute un élément au filtre de Bloom.
     *
     * @param key l'élément à insérer
     */
    public void add(byte[] key) {
        
        if (key.length == 0){return;}
        numElement++;

        for (HashFunction h : hashFunc){
            hashCounter[h.hash(key)]++;
            bitSet.set(h.hash(key));
        }
    }

    /**
     * Cherche pour l'élément dans le filtre de Bloom.
     *
     * @param key l'élément à trouver
     * @return si l'élément est possiblement dans le filtre
     */
    public boolean contains(byte[] key) {
        if (key.length == 0){return true;}

        for (HashFunction h : hashFunc){
            if (!bitSet.get(h.hash(key))){
                return false;
            }
        }
        return true; 
    }

    /**
     * Remet à zéro le filtre de Bloom.
     */
    public void reset() {

        int size = bitSet.getSize();
        bitSet = new BitSet(size);
    }

    /**
     * Retourne le nombre de bits du filtre de Bloom.
     *
     * @return nombre de bits
     */
    public int size() {
        return bitSet.getSize();
    }

    /**
     * Retourne le nombre d'éléments insérés dans le filtre de Bloom.
     *
     * @return nombre d'éléments insérés
     */
    public int count() {
        return numElement;
    }

    /**
     * Retourne la probabilité actuelle de faux positifs.
     *
     * @return probabilité de faux positifs
     */
    public double fpp() {

        // formule trouver sur wikipedia fpp = (1-exp(-k*n/m))^k
        double fpp = Math.pow(1.0 - Math.exp((double)-hashFunc.size()*numElement / m), hashFunc.size());
        return fpp; 
    }

    /**
     * Fonction pour tester si mes HashFunctions sont equiprobables
     */
    public void showCounter(){
        for (int i = 0; i< hashCounter.length; i++){
            System.out.println(i + " - " + hashCounter[i]) ;
        }
    }

    /**
     * utiliser pour tester le bloomfilter
     * @return
     */
    public ArrayList<HashFunction> getHashFunc() {
        return hashFunc;
    }
}
