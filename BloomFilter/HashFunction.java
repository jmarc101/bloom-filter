/**
 * Devoir 3 - IFT 2015 - Bloom Filters
 * @author Jean-Marc Prud'homme (20137035)
 * @author Jean-Daniel Toupin - 20046724
 */
import java.util.*;

/**
 * Fonciton qui cree les hashfunctions universal. Base sur wikipedia sur le universal Hashing des integer
 * https://en.wikipedia.org/wiki/Universal_hashing
 */
public class HashFunction{
    
    private int hashNum;
    private int numBits;

    /**
     * Constructeur des functions de Hashage
     * 
     * @param hashNum  Kieme fonction de hashage = hashNum
     * @param numBits  nombre de btis dans le bitset
     */
    public HashFunction(int hashNum, int numBits){
        this.hashNum = hashNum;
        this.numBits = numBits;
    } 
    

    public int hash(byte[] bits){

        //Creation du Random
        Random rng1 = new Random();


        //placeholder 
        int result = 0;

        for (int i = 0; i < bits.length; i++){
            rng1.setSeed(bits[i] + i + hashNum);
            result += (rng1.nextInt(numBits));
        }
        

        

        return Math.abs((result % numBits));
    }
}