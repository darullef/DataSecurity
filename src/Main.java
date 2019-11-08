import BBS.*;

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

    public static void main(String[] args)
    {
        BBS();
    }
}
