package RSA;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class RSA {

    private int p;
    private int q;
    private BigInteger n;
    private BigInteger phi;
    private BigInteger e;
    private BigInteger d;

    private Random random = new Random();

    public RSA(int minRange, int maxRange)
    {
        this.p = getRandomPQ(minRange, maxRange);
        this.q = getRandomPQ(minRange, maxRange);
        this.n = BigInteger.valueOf(this.p).multiply(BigInteger.valueOf(this.q));
        this.phi = BigInteger.valueOf(this.p - 1).multiply(BigInteger.valueOf(this.q - 1));
        this.e = getE(minRange, maxRange);
        this.d = e.modInverse(phi);
    }

    public boolean isPrime(int num)
    {
        if (num == 2)
            return true;
        if (num < 2 || num % 2 == 0)
            return false;
        for (int i = 3; i * i <= num; i += 2)
            if (num % i == 0)
                return false;
        return true;
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
        while((isPrime(x)))
        {
            x = random.nextInt(maxRange) + minRange;
        }
        return x;
    }

    private BigInteger getE(int minRange, int maxRange)
    {
        BigInteger x = BigInteger.valueOf(random.nextInt(maxRange) + minRange);
        while(x.gcd(phi).intValue() != 1)
        {
            x = BigInteger.valueOf(random.nextInt(maxRange) + minRange);
        }
        return x;
    }

    private String encryption(String message)
    {
        BigInteger messageBytes = new BigInteger(message.getBytes());
        messageBytes.modPow(e, n);
        return messageBytes.toString();
    }

    private String decryption(String message) {
        BigInteger messageBytes = new BigInteger(message);
        messageBytes.modPow(d, n);
        return new String(messageBytes.toByteArray(), StandardCharsets.UTF_8);
    }

    public void print()
    {
        System.out.println("p: " + this.p + ", q: " + this.q + ", n: " + this.n + ", phi: " +
                this.phi + ", e: " + this.e + ", d: " + this.d);
    }

    public void doRSA(String text) {
        System.out.println("Plain text: " + text);
        String encrypted = encryption(text);
        System.out.println("Encrypted message: " + encrypted);
        String decrypted = decryption(encrypted);
        System.out.println("Result: " + decrypted);
    }
}
