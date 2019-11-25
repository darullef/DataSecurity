package Diffi_Hellman;

import java.math.BigInteger;
import java.util.*;

public class DiffiHellman {

    private BigInteger n;
    private BigInteger g;
    private BigInteger X;
    private BigInteger Y;
    private BigInteger x;
    private BigInteger y;
    private BigInteger kA;
    private BigInteger kB;

    private Random rand = new Random();

    public DiffiHellman(int minRange, int maxRange)
    {
        this.n = generateN(minRange, maxRange);
        this.g = generateG();
        this.x = BigInteger.valueOf(rand.nextInt(maxRange) + minRange);
        this.y = BigInteger.valueOf(rand.nextInt(maxRange) + minRange);
    }

    private BigInteger generateN(int minRange, int maxRange)
    {
        int x = rand.nextInt(maxRange) + minRange;
        while(isPrime(x))
        {
            x = rand.nextInt(maxRange) + minRange;
        }
        return new BigInteger(String.valueOf(x));
    }

    private boolean isPrime(int x)
    {
        if(x <= 1)
        {
            return true;
        }
        for(int i = 2; i < x; i++)
        {
            if(x % i == 0) return true;
        }
        return false;
    }

    private BigInteger generateG()
    {
        return getPrimitiveRoot();
    }

    private Map<Integer, Integer> getPrimeFactor(int p) {
        Map<Integer, Integer> map = new HashMap<>();
        while (p % 2 == 0) {
            insertToMap(2, map);
            p /= 2;
        }

        for (int i = 3; i <= Math.sqrt(p); i += 2) {
            while (p % i == 0) {
                insertToMap(i, map);
                p /= i;
            }
        }

        if (p > 2)
            insertToMap(p, map);
        return map;
    }

    private void insertToMap(int i, Map<Integer, Integer> map) {
        map.merge(i, 1, Integer::sum);
    }

    private BigInteger getPrimitiveRoot()
    {
        int m = this.n.intValue() - 1;
        int primeRoot = 0;
        Map<Integer, Integer> primeFactor = getPrimeFactor(m);
        primeFactor.replaceAll((k, v) -> m / k);
        for (int i = 2; i <= m; i++) {
            boolean notPrimeRoot = false;
            Set<Integer> reminder = new HashSet<>();
            for (Map.Entry<Integer, Integer> map : primeFactor.entrySet()) {
                if(BigInteger.valueOf(i).modPow(BigInteger.valueOf(map.getValue()), BigInteger.valueOf(this.n.intValue())).equals(BigInteger.ONE))
                    notPrimeRoot = true;
            }
            if (!notPrimeRoot) {
                primeRoot = i;
                break;
            }
        }
        return new BigInteger(String.valueOf(primeRoot));
    }

    private void calculateX()
    {
        X = this.g.modPow(this.x, this.n);
    }

    private void calculateY()
    {
        Y = this.g.modPow(this.y, this.n);
    }

    private void calculate_kA()
    {
        this.kA = this.Y.modPow(this.x, this.n);
    }

    private void calculate_kB()
    {
        this.kB = this.X.modPow(this.y, this.n);
    }

    public void generateKey()
    {
        calculateX();
        calculateY();
        calculate_kA();
        calculate_kB();
        System.out.println(this.kA + " | " + this.kB);
    }
}
