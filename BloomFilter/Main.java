

public class Main{

    public static void main(String[] args) {

        BloomFilter bloom = new BloomFilter(100, .1);
        // BloomFilter bloom = new BloomFilter(100, 100000);

        System.out.println(bloom.size());
        System.out.println(bloom.getHashFunc().size());
        
        byte[] b1 = {0};

        byte[] b2 = {};
        byte[] b3 = {21,35,23,4,55};

        byte[] b4 = {111,2,43,65,5};

        byte[] b6 = {99,25,13,01,2};
        byte[] b5 = {11,29,91,22,57,19,24,92,32,22,1,2,9,2,52};


        bloom.add(b1);
        bloom.add(b2);
        bloom.add(b3);
        bloom.add(b4);
        bloom.add(b5);
        // bloom.showCounter();
        
        System.out.println(bloom.contains(b1));
        System.out.println(bloom.contains(b2));
        System.out.println(bloom.contains(b3));
        System.out.println(bloom.contains(b4));
        System.out.println(bloom.contains(b5));
        System.out.println(bloom.contains(b6));

        // System.out.println(bloom.fpp());
        
        // bloom.showCounter();
        


     }

    
}