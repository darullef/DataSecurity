import BBS.*;
import AES.*;
import RSA.*;
import Diffi_Hellman.*;
import Steganography.*;
import CW8.*;
import CW9.*;
import CW10.*;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Scanner;

public class Main {

    public static void BBS()
    {
        long timeStart1 = System.currentTimeMillis();
        BBS bbs1 = new BBS(10000, 20000, 20000);
        bbs1.print();
        String result1 = bbs1.generateBBS();
        System.out.println(result1);
        long timeEnd1 = System.currentTimeMillis() - timeStart1;
        System.out.println("n = 20000: " + timeEnd1 / 1.000);

        FIPS_Tests t = new FIPS_Tests(result1);
        t.monoBitTest();
        t.runsTest();
        t.longRunTest();
        t.pokerTest();
    }

    private static void CBC() throws Exception
    {
        System.out.println("CBC");

        File small = new File("txt\\1MB.txt");
        File mid = new File("txt\\5MB.txt");
        File big = new File("txt\\10MB.txt");

        Scanner smallScan = new Scanner(small);
        Scanner midScan = new Scanner(mid);
        Scanner bigScan = new Scanner(big);

        String smallString = smallScan.nextLine();
        String midString = midScan.nextLine();
        String bigString = bigScan.nextLine();

        String key = "abcdefghijklmop";

        long bigTimeStart = System.currentTimeMillis();
        CBC cbc3 = new CBC();
        byte[] xxx3 = cbc3.encryption(bigString, key);
        cbc3.decryption(xxx3, key);
        long bigTimeEnd = System.currentTimeMillis() - bigTimeStart;
        System.out.println("10MB file time: " + bigTimeEnd);

        long midTimeStart = System.currentTimeMillis();
        CBC cbc2 = new CBC();
        byte[] xxx2 = cbc2.encryption(midString, key);
        cbc2.decryption(xxx2, key);
        long midTimeEnd = System.currentTimeMillis() - midTimeStart;
        System.out.println("5MB file time: " + midTimeEnd);

        long smallTimeStart = System.currentTimeMillis();
        CBC cbc1 = new CBC();
        byte[] xxx1 = cbc1.encryption(smallString, key);
        cbc1.decryption(xxx1, key);
        long smallTimeEnd = System.currentTimeMillis() - smallTimeStart;
        System.out.println("1MB file time: " + smallTimeEnd);
    }

    private static void CTR() throws Exception
    {
        System.out.println("CTR");

        File small = new File("txt\\1MB.txt");
        File mid = new File("txt\\5MB.txt");
        File big = new File("txt\\10MB.txt");

        Scanner smallScan = new Scanner(small);
        Scanner midScan = new Scanner(mid);
        Scanner bigScan = new Scanner(big);

        String smallString = smallScan.nextLine();
        String midString = midScan.nextLine();
        String bigString = bigScan.nextLine();

        String key = "abcdefghijklmop";

        long bigTimeStart = System.currentTimeMillis();
        CTR ctr1 = new CTR();
        byte[] xxx3 = ctr1.encryption(bigString, key);
        ctr1.decryption(xxx3, key);
        long bigTimeEnd = System.currentTimeMillis() - bigTimeStart;
        System.out.println("10MB file time: " + bigTimeEnd);

        long midTimeStart = System.currentTimeMillis();
        CTR ctr2 = new CTR();
        byte[] xxx2 = ctr2.encryption(midString, key);
        ctr2.decryption(xxx2, key);
        long midTimeEnd = System.currentTimeMillis() - midTimeStart;
        System.out.println("5MB file time: " + midTimeEnd);

        long smallTimeStart = System.currentTimeMillis();
        CTR ctr3 = new CTR();
        byte[] xxx1 = ctr3.encryption(smallString, key);
        ctr3.decryption(xxx1, key);
        long smallTimeEnd = System.currentTimeMillis() - smallTimeStart;
        System.out.println("1MB file time: " + smallTimeEnd);
    }

    private static void ECB() throws Exception
    {
        System.out.println("ECB");

        File small = new File("txt\\1MB.txt");
        File mid = new File("txt\\5MB.txt");
        File big = new File("txt\\10MB.txt");

        Scanner smallScan = new Scanner(small);
        Scanner midScan = new Scanner(mid);
        Scanner bigScan = new Scanner(big);

        String smallString = smallScan.nextLine();
        String midString = midScan.nextLine();
        String bigString = bigScan.nextLine();

        String key = "qwertyuiopasdfgh";

        long bigTimeStart = System.currentTimeMillis();
        String xxx3 = ECB.encryption(bigString, key);
        ECB.decryption(xxx3, key);
        long bigTimeEnd = System.currentTimeMillis() - bigTimeStart;
        System.out.println("10MB file time: " + bigTimeEnd);

        long midTimeStart = System.currentTimeMillis();
        String xxx2 = ECB.encryption(midString, key);
        ECB.decryption(xxx2, key);
        long midTimeEnd = System.currentTimeMillis() - midTimeStart;
        System.out.println("5MB file time: " + midTimeEnd);

        long smallTimeStart = System.currentTimeMillis();
        String xxx1 = ECB.encryption(smallString, key);
        ECB.decryption(xxx1, key);
        long smallTimeEnd = System.currentTimeMillis() - smallTimeStart;
        System.out.println("1MB file time: " + smallTimeEnd);
    }

    private static void CFB() throws Exception
    {
        System.out.println("CFB");

        File small = new File("txt\\1MB.txt");
        File mid = new File("txt\\5MB.txt");
        File big = new File("txt\\10MB.txt");

        Scanner smallScan = new Scanner(small);
        Scanner midScan = new Scanner(mid);
        Scanner bigScan = new Scanner(big);

        String smallString = smallScan.nextLine();
        String midString = midScan.nextLine();
        String bigString = bigScan.nextLine();

        String key = "abcdefghijklmop";

        long bigTimeStart = System.currentTimeMillis();
        CFB cfb1 = new CFB();
        byte[] xxx3 = cfb1.encryption(bigString, key);
        cfb1.decryption(xxx3, key);
        long bigTimeEnd = System.currentTimeMillis() - bigTimeStart;
        System.out.println("10MB file time: " + bigTimeEnd);

        long midTimeStart = System.currentTimeMillis();
        CFB cfb2 = new CFB();
        byte[] xxx2 = cfb2.encryption(midString, key);
        cfb2.decryption(xxx2, key);
        long midTimeEnd = System.currentTimeMillis() - midTimeStart;
        System.out.println("5MB file time: " + midTimeEnd);

        long smallTimeStart = System.currentTimeMillis();
        CFB cfb3 = new CFB();
        byte[] xxx1 = cfb3.encryption(smallString, key);
        cfb3.decryption(xxx1, key);
        long smallTimeEnd = System.currentTimeMillis() - smallTimeStart;
        System.out.println("1MB file time: " + smallTimeEnd);
    }

    private static void OFB() throws Exception
    {
        System.out.println("OFB");

        File small = new File("txt\\1MB.txt");
        File mid = new File("txt\\5MB.txt");
        File big = new File("txt\\10MB.txt");

        Scanner smallScan = new Scanner(small);
        Scanner midScan = new Scanner(mid);
        Scanner bigScan = new Scanner(big);

        String smallString = smallScan.nextLine();
        String midString = midScan.nextLine();
        String bigString = bigScan.nextLine();

        String key = "abcdefghijklmop";

        long bigTimeStart = System.currentTimeMillis();
        OFB ofb1 = new OFB();
        byte[] xxx3 = ofb1.encryption(bigString, key);
        ofb1.decryption(xxx3, key);
        long bigTimeEnd = System.currentTimeMillis() - bigTimeStart;
        System.out.println("10MB file time: " + bigTimeEnd);

        long midTimeStart = System.currentTimeMillis();
        OFB ofb2 = new OFB();
        byte[] xxx2 = ofb2.encryption(midString, key);
        ofb2.decryption(xxx2, key);
        long midTimeEnd = System.currentTimeMillis() - midTimeStart;
        System.out.println("5MB file time: " + midTimeEnd);

        long smallTimeStart = System.currentTimeMillis();
        OFB ofb3 = new OFB();
        byte[] xxx1 = ofb3.encryption(smallString, key);
        ofb3.decryption(xxx1, key);
        long smallTimeEnd = System.currentTimeMillis() - smallTimeStart;
        System.out.println("1MB file time: " + smallTimeEnd);
    }

    public static void AES() throws Exception
    {
        CBC();
        CTR();
        ECB();
        CFB();
        OFB();
    }

    public static void errPropagation() throws Exception
    {
        OFB ofb = new OFB();
        String key = "abcdefghijklmop";
        String txt = "Ania ma kota ale kot nie ma ali Ania ma kota ale kot nie ma ali";
        System.out.println("Input: " + txt);
        byte[] arr = ofb.encryption(txt, key);
        arr[40] = 0;
        String result = ofb.decryption(arr, key);
        System.out.println("Output: " + result + ", size: " + arr.length);
    }

    private static void RSA() {
        RSA rsa = new RSA(10, 1000000000);
        rsa.print();
        String text = "Ala ma kota ale kot nie ma ali";
        rsa.doRSA(text);
    }

    private static void DiffiHellman()
    {
        DiffiHellman dh = new DiffiHellman(10, 100000);
        dh.generateKey();
    }

    private static void Steganography() throws IOException {
        Stegano stegano = new Stegano("cat.jpg", "test");
        stegano.print();
        stegano.encryprion();
        stegano.decryption("txt\\XY.txt");
    }

    private static void CW8() throws NoSuchAlgorithmException {
        DigestFunction md5 = new MD5();
        md5.checkTime("ala ma kota kot ma ale");

        DigestFunction sha1 = new SHA1();
        sha1.checkTime("ala ma kota kot ma ale");

        DigestFunction sha2_1 = new SHA2("SHA-224");
        sha2_1.checkTime("ala ma kota kot ma ale");

        DigestFunction sha2_2 = new SHA2("SHA-256");
        sha2_2.checkTime("ala ma kota kot ma ale");

        DigestFunction sha2_3 = new SHA2("SHA-384");
        sha2_3.checkTime("ala ma kota kot ma ale");

        DigestFunction sha2_4 = new SHA2("SHA-512");
        sha2_4.checkTime("ala ma kota kot ma ale");

        DigestFunction sha3 = new SHA3();
        sha3.checkTime("ala ma kota kot ma ale");
    }

    private static void CW9_trivial()
    {
        TrivialMethod tw = new TrivialMethod(100);
        tw.init();
    }

    public static void Shamir() {
        int CERTAINTY = 1024;
        SecureRandom random = new SecureRandom();

        BigInteger secret = new BigInteger("12345678900987654321");
        BigInteger prime = new BigInteger(secret.bitLength() + 1, CERTAINTY, random);

        int needed = 3;
        int available = 5;

        System.out.println("Secret: " + secret + ", needed: " + needed + ", available: " + available + ", prime: " + prime);
        SecretShare[] shares = Shamir.split(secret, needed, available, prime, random);

        SecretShare[] sharesToViewSecret = new SecretShare[] {shares[1],shares[4]}; // 1 & 4
        Shamir.combine(sharesToViewSecret, prime);

        sharesToViewSecret = new SecretShare[] {shares[0],shares[1],shares[3]}; // 0 & 1 & 3
        Shamir.combine(sharesToViewSecret, prime);

        sharesToViewSecret = new SecretShare[] {shares[0],shares[1],shares[2],shares[3]}; // 0 & 1 &  2 & 3
        Shamir.combine(sharesToViewSecret, prime);
    }

    private static void Cw10() throws IOException
    {
        VisualCryptography vc = new VisualCryptography("cat.jpg");
        vc.createShares();
        vc.mergeShares();
    }

    public static void main(String[] args) throws Exception
    {
        Cw10();
    }
}
