package BBS;

import java.math.BigInteger;
import java.util.Random;

public class BBS {

    private int p;
    private int q;
    private BigInteger m;
    private BigInteger seed;
    private int n;

    private Random rand = new Random();

    public BBS()
    {
        this.p = 0;
        this.q = 0;
        this.m = BigInteger.valueOf(0);
        this.seed = BigInteger.valueOf(0);
        this.n = 0;
    }

    public BBS(int minRange, int maxRange, int nTimes)
    {
        this.p = getRandomPQ(minRange, maxRange);
        this.q = getRandomPQ(minRange, maxRange);
        this.m = BigInteger.valueOf(this.p * this.q);
        this.seed = BigInteger.valueOf(getRandomSeed(minRange, maxRange));
        this.n = nTimes;
    }

    private boolean isPrime(int x)
    {
        if(x <= 1)
        {
            return false;
        }
        for(int i = 2; i < x; i++)
        {
            if(x % i == 0) return false;
        }
        return true;
    }

    private boolean check4mod3(int x)
    {
        return n % 4 == 3;
    }

    private int nwd_1(int x, int y)
    {
        while(x != y)
        {
            if( x > y)
            {
                x = x - y;
            }
            else
            {
                y = y - x;
            }
        }
        return x;
    }

    private int getRandomPQ(int minRange, int maxRange)
    {
        int x = rand.nextInt(maxRange) + minRange;
        while(!isPrime(x) && !check4mod3(x))
        {
            x = rand.nextInt(maxRange) + minRange;
        }
        return x;
    }

    private int getRandomSeed(int minRange, int maxRange)
    {
        int x = rand.nextInt(maxRange) + minRange;
        while(nwd_1(x , this.m.intValue()) == 1)
        {
            x = rand.nextInt(maxRange) + minRange;
        }
        return x;
    }

    public String generateBBS()
    {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < this.n; i++)
        {
            this.seed = (this.seed.pow(2)).mod(m);
            String binaryBigInteger = this.seed.toString(2);
            char[] tempArr = binaryBigInteger.toCharArray();
            result.append(tempArr[tempArr.length - 1]);
        }
        return result.toString();
    }

    public void print()
    {
        System.out.println("p: " + this.p + ", q: " + this.q + ", m: " + this.m + ", seed: " + this.seed);
    }
}
