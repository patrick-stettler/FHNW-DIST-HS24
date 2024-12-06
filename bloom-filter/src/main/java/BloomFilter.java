import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.BitSet;


public class BloomFilter {

    private final BitSet bitSet;
    private final int bitSetSize;
    private final int numHashFunctions;
    private final HashFunction[] hashFunctions;

    public BloomFilter(int n, double p) {
        
        // Berechnung von m und k
        this.bitSetSize = calculateOptimalSize(n, p);
        this.numHashFunctions = calculateOptimalNumHashFunctions(n, bitSetSize);
        this.bitSet = new BitSet(bitSetSize);
        this.hashFunctions = new HashFunction[numHashFunctions];

        // Initialisierung der Hash-Funktionen mit verschiedenen Seeds
        for (int i = 0; i < numHashFunctions; i++) {
            hashFunctions[i] = Hashing.murmur3_128(i);
        }
    }

    private int calculateOptimalSize(int n, double p) {
        return (int) (-n * Math.log(p) / (Math.pow(Math.log(2), 2)));
    }

    private int calculateOptimalNumHashFunctions(int n, int m) {
        return (int) (m / n * Math.log(2));
    }

    public void add(String value) {
        for (HashFunction hashFunction : hashFunctions) {
            int hash = Math.abs(hashFunction.hashString(value, StandardCharsets.UTF_8).asInt()) % bitSetSize;
            bitSet.set(hash);
        }
    }

    public boolean mightContain(String value) {
        for (HashFunction hashFunction : hashFunctions) {
            int hash = Math.abs(hashFunction.hashString(value, StandardCharsets.UTF_8).asInt()) % bitSetSize;
            if (!bitSet.get(hash)) {
                return false;
            }
        }
        return true;
    }

    public int getBitSetSize() {
        return bitSetSize;
    }

    public int getNumHashFunctions() {
        return numHashFunctions;
    }

}