import BBS.*;
import AES.*;
import RSA.*;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {

    public static void BBS()
    {
        long timeStart1 = System.currentTimeMillis();
        BBS bbs1 = new BBS(0, 100000, 20000);
        bbs1.print();
        String result1 = bbs1.generateBBS();
        System.out.println(result1);
        long timeEnd1 = System.currentTimeMillis() - timeStart1;
        System.out.println("n = 20000: " + timeEnd1 / 1.000);

        /*
        long timeStart2 = System.currentTimeMillis();
        BBS bbs2 = new BBS(0, 100000, 40000);
        bbs2.print();
        String result2 = bbs2.generateBBS();
        System.out.println(result2);
        long timeEnd2 = System.currentTimeMillis() - timeStart2;
        System.out.println("n = 40000: " + timeEnd2);

        long timeStart3 = System.currentTimeMillis();
        BBS bbs3 = new BBS(0, 100000, 60000);
        bbs3.print();
        String result3 = bbs3.generateBBS();
        System.out.println(result3);
        long timeEnd3 = System.currentTimeMillis() - timeStart3;
        System.out.println("n = 60000: " + timeEnd3);

        long timeStart4 = System.currentTimeMillis();
        BBS bbs4 = new BBS(0, 100000, 80000);
        bbs4.print();
        String result4 = bbs4.generateBBS();
        System.out.println(result4);
        long timeEnd4 = System.currentTimeMillis() - timeStart4;
        System.out.println("n = 80000: " + timeEnd4);

        long timeStart5 = System.currentTimeMillis();
        BBS bbs5 = new BBS(0, 100000, 100000);
        bbs5.print();
        String result5 = bbs5.generateBBS();
        System.out.println(result5);
        long timeEnd5 = System.currentTimeMillis() - timeStart5;
        System.out.println("n = 1000000: " + timeEnd5);
        */


        FIPS_Tests t = new FIPS_Tests(result1);
        t.monoBitTest();
        t.runsTest();
        t.longRunTest();
        t.pokerTest();
    }

    public static void CBC() throws Exception {

        File small = new File("1MB.txt");
        File mid = new File("5MB.txt");
        File big = new File("10MB.txt");

        Scanner smallScan = new Scanner(small);
        Scanner midScan = new Scanner(mid);
        Scanner bigScan = new Scanner(big);

        String smallString = smallScan.nextLine();
        String midString = midScan.nextLine();
        String bigString = bigScan.nextLine();

        String key = "abcdefghijklmop";

        long smallTimeStart = System.currentTimeMillis();
        CBC cbc1 = new CBC();
        byte[] xxx1 = cbc1.encryption(smallString, key);
        cbc1.decryption(xxx1, key);
        long smallTimeEnd = System.currentTimeMillis() - smallTimeStart;
        System.out.println("1MB file time: " + smallTimeEnd);

        long midTimeStart = System.currentTimeMillis();
        CBC cbc2 = new CBC();
        byte[] xxx2 = cbc2.encryption(smallString, key);
        cbc2.decryption(xxx2, key);
        long midTimeEnd = System.currentTimeMillis() - midTimeStart;
        System.out.println("5MB file time: " + midTimeEnd);

        long bigTimeStart = System.currentTimeMillis();
        CBC cbc3 = new CBC();
        byte[] xxx3 = cbc3.encryption(smallString, key);
        cbc3.decryption(xxx3, key);
        long bigTimeEnd = System.currentTimeMillis() - bigTimeStart;
        System.out.println("10MB file time: " + bigTimeEnd);


    }

    public static void RSA() throws UnsupportedEncodingException {
        RSA rsa = new RSA(0, 100000);
        rsa.print();
        String text = "Ala ma kota ale kot nie ma ali";
        rsa.doRSA(text);
    }

    public static void main(String[] args) throws Exception
    {
        //BBS();
        CBC();
        //RSA();
    }
}
