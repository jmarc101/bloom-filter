/**
 * Devoir 3 - IFT 2015 - Bloom Filters
 * @author Jean-Marc Prud'homme (20137035)
 * @author Jean-Daniel Toupin - 20046724
 */
public class BitSet {

    private int[] bitSet;
    private int size;

    /**
     * Crée un ensemble de bits, d'une certaine taille. Ils sont initialisés à
     * {@code false}.
     *
     * @param nbits taille initiale de l'ensemble
     */
    public BitSet(int nbits) {
        
        // nombre de bits divise par 30 nous donne le nombre de int necessaire
        // on divise par 30 parce les int ne prennent seulement que 2^31-1 comme int max.
        this.bitSet = new int[(int)Math.ceil((double)nbits / 30)];
        this.size = nbits;
    }

    /**
     * Retourne la valeur du bit à l'index spécifié.
     *
     * @param bitIndex l'index du bit
     * @return la valeur du bit à l'index spécifié
     */
    public boolean get(int bitIndex) {

        //on fait un HIGH/LOW avec l'index
        int numInt = bitIndex / 30;
        int index = bitIndex % 30;
        int b = bitSet[numInt];

        //retourne true si le bit == 1
        return binaryBit(b, index);
        
    }

    /**
     * Définit le bit à l'index spécifié comme {@code true}.
     *
     * @param bitIndex l'index du bit
     */
    public void set(int bitIndex) {

        //on fait un HIGH/LOW avec l'index
        int numInt = bitIndex / 30;
        int index = bitIndex % 30;

        //Si le bit != 1 alors on rajoute 2^index au int
        if (!binaryBit(bitSet[numInt], index)){
            bitSet[numInt] += Math.pow(2, index);
        }
        

    }

    /**
     * Définit le bit à l'index spécifié comme {@code false}.
     *
     * @param bitIndex l'index du bit
     */
    public void clear(int bitIndex) {

        //on fait un HIGH/LOW avec l'index
        int numInt = bitIndex / 30;
        int index = bitIndex % 30;

        //si le bit == 1 alors on enleve 2^index au int
        if (binaryBit(bitSet[numInt], index)){
            bitSet[numInt] -= Math.pow(2, index);
        }

    }

    /**
     * retourn le grandeur du bitset
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * Fonction qui regarde si le bit a l'index i == 1. 
     * @param bitSet le bitset en forme de int a regarder
     * @param index l'index recherché
     * @return  true si index == 1
     */
    public static boolean binaryBit(int bitSet, int index){

        int num = bitSet;
        int i  = 0;

        //While loop pour passer a travers le int
        while (num > 0 && i <= index) {
            int bit = num % 2;
            num /= 2;
            
            // si le bit == 1 et on est au bon index alors bingo
            if (bit == 1 && i == index){
                return true;
            }
            i++;
        }

        //sinon fail
        return false;

     }




}
