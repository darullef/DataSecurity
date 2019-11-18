package RSA;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class RSA {

    private int p;
    private int q;
    private BigInteger n;
    private BigInteger phi;
    private int e;
    private int d;

    private Random random = new Random();

    public RSA(int minRange, int maxRange)
    {
        this.p = getRandomPQ(minRange, maxRange);
        this.q = getRandomPQ(minRange, maxRange);
        this.n = BigInteger.valueOf(this.p * this.q);
        this.phi = BigInteger.valueOf((this.p - 1) * (this.q - 1));
        this.e = getE(minRange, maxRange);
        this.d = getD(minRange, maxRange);
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
        int x = random.nextInt(maxRange) + minRange;
        while(isPrime(x))
        {
            x = random.nextInt(maxRange) + minRange;
        }
        return x;
    }

    private int getE(int minRange, int maxRange)
    {
        int x = random.nextInt(maxRange) + minRange;
        while(isPrime(x) && nwd_1(x, this.phi.intValue()) == 2)
        {
            x = random.nextInt(maxRange) + minRange;
        }
        return x;
    }

    private int getD(int minRange, int maxRange)
    {
        int x = random.nextInt(maxRange) + minRange;
        while((e * d) % n.intValue() == (1 % phi.intValue()) % n.intValue())
        {
            x = random.nextInt(maxRange) + minRange;
        }
        return x;
    }

    private String encryption(String message)
    {
        BigInteger messageBytes = new BigInteger(message.getBytes());
        messageBytes.modPow(BigInteger.valueOf(e), n);
        return messageBytes.toString();
    }

    private String decryption(String message) {
        BigInteger messageBytes = new BigInteger(message);
        messageBytes.modPow(BigInteger.valueOf(d), n);
        return new String(messageBytes.toByteArray(), StandardCharsets.UTF_8);
    }

    public void print()
    {
        System.out.println("p: " + this.p + ", q: " + this.q + ", n: " + this.n + ", phi: " +
                this.phi + ", e: " + this.e + ", d:" + this.d);
    }

    public void doRSA(String text) {
        System.out.println("Plain text: " + text);
        String encrypted = encryption(text);
        System.out.println("Encrypted message: " + encrypted);
        String decrypted = decryption(encrypted);
        System.out.println("Result: " + decrypted);
    }
}
