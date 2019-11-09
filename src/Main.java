import BBS.*;
import AES.*;

public class Main {

    public static void BBS()
    {
        BBS bbs = new BBS(0, 100000, 20000);
        bbs.print();
        String result = bbs.generateBBS();
        System.out.println(result);

        FIPS_Tests t = new FIPS_Tests(result);
        t.monoBitTest();
        t.runsTest();
        t.longRunTest();
        t.pokerTest();
    }

    public static void CBC() throws Exception {
        CBC cbc = new CBC();
        String plainText = "ala ma kota ale kot nie ma ali";
        String key = "abcdefghijklmop";
        byte[] x = cbc.encryption(plainText, key);
        String result = cbc.decryption(x, key);
        System.out.println(result);
    }

    public static void main(String[] args) throws Exception
    {
        //BBS();
        //CBC();
    }
}
