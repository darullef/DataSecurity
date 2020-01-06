package BBS;

import java.text.DecimalFormat;

public class FIPS_Tests {

    private String key;

    public FIPS_Tests(String binarySeries)
    {
        this.key = binarySeries;
    }

    public void monoBitTest()
    {
        int x = monoBitTestCalculations();
        System.out.println("Mono Bit Test");
        System.out.println("Biggest series: " + x);
        if(monoBitTestFPIS_140_1(x))
        {
            System.out.println("Mono Bit Test FIPS 140-1 passed");
        }
        else System.out.println("Mono Bit Test FIPS 140-1 failed");

        if(monoBitTestFPIS_140_2(x))
        {
            System.out.println("Mono Bit Test FIPS 140-2 passed");
        }
        else System.out.println("Mono Bit Test FIPS 140-2 failed");
    }

    private int monoBitTestCalculations()
    {
        String[] key_arr = this.key.split("");
        int counter = 0;

        for(int i = 0; i < key_arr.length; i++)
        {
            if(key_arr[i].equals("1")) counter++;
        }
        return counter;
    }

    private boolean monoBitTestFPIS_140_1(int x)
    {
        return 9654 < x && x < 10346;
    }

    private boolean monoBitTestFPIS_140_2(int x)
    {
        return 9725 < x && x < 10275;
    }

    public void runsTest()
    {
        int[] x = runsTestCalculations();
        System.out.println("Runs Test");
        System.out.println("Length of run: 1 -> " + x[0]);
        System.out.println("Length of run: 2 -> " + x[1]);
        System.out.println("Length of run: 3 -> " + x[2]);
        System.out.println("Length of run: 4 -> " + x[3]);
        System.out.println("Length of run: 5 -> " + x[4]);
        System.out.println("Length of run: 6+ -> " + x[5]);

        if(runsTestFPIS_140_1(x))
        {
            System.out.println("Runs Test FIPS 140-1 passed");
        }
        else System.out.println("Runs Test FIPS 140-1 failed");

        if(runsTestFPIS_140_2(x))
        {
            System.out.println("Runs Test FIPS 140-2 passed");
        }
        else System.out.println("Runs Test FIPS 140-2 failed");
    }

    private int[] runsTestCalculations()
    {
        String[] key_arr = this.key.split("");
        int[] counters = {0, 0, 0, 0, 0, 0};
        int runSize = 0;

        for(int i = 1; i < key_arr.length; i++)
        {
            if(key_arr[i].equals(key_arr[i-1]))
            {
                runSize++;
            }
            else
            {
                switch (runSize)
                {
                    case(0): break;
                    case(1): counters[0]++; break;
                    case(2): counters[1]++; break;
                    case(3): counters[2]++; break;
                    case(4): counters[3]++; break;
                    case(5): counters[4]++; break;
                    default: counters[5]++; break;
                }
                runSize = 0;
            }
        }
        return counters;
    }

    private boolean runsTestFPIS_140_1(int[] arr)
    {
        boolean result = false;

        if(2267 < arr[0] && arr[0] < 2733)
        {
            if(1079 < arr[1] && arr[1] < 1421)
            {
                if(502 < arr[2] && arr[2] < 748)
                {
                    if(223 < arr[3] && arr[3] < 402)
                    {
                        if(90 < arr[4] && arr[4] < 223)
                        {
                            if(90 < arr[5] && arr[5] < 223)
                            {
                                result = true;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private boolean runsTestFPIS_140_2(int[] arr)
    {
        boolean result = false;

        if(2343 < arr[0] && arr[0] < 2657)
        {
            if(1135 < arr[1] && arr[1] < 1365)
            {
                if(542 < arr[2] && arr[2] < 708)
                {
                    if(251 < arr[3] && arr[3] < 373)
                    {
                        if(111 < arr[4] && arr[4] < 201)
                        {
                            if(111 < arr[5] && arr[5] < 201)
                            {
                                result = true;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public void longRunTest()
    {
        int[] x = longRunTestCalculations();
        System.out.println("Long Run Test");
        System.out.println("Long run of 0: " + x[0] + ", long run of 1: " + x[1]);

        if(longRunTestFPIS_140_1(x))
        {
            System.out.println("Long Run Test FIPS 140-1 passed");
        }
        else System.out.println("Long Run Test FIPS 140-1 failed");

        if(longRunTestFPIS_140_2(x))
        {
            System.out.println("Long Run Test FIPS 140-2 passed");
        }
        else System.out.println("Long Run Test FIPS 140-2 failed");
    }

    private int[] longRunTestCalculations()
    {
        String[] key_arr = this.key.split("");
        int[] counters = {0, 0};
        int temp_counter0 = 0;
        int temp_counter1 = 0;

        for(int i = 0; i < key_arr.length; i++)
        {
            if(key_arr[i].equals("0"))
            {
                temp_counter0++;
                temp_counter1 = 0;
                if(temp_counter0 > counters[0])
                {
                    counters[0] = temp_counter0;
                }
            }
            else
            {
                temp_counter1++;
                temp_counter0 = 0;
                if(temp_counter1 > counters[1])
                {
                    counters[1] = temp_counter1;
                }
            }
        }
        return counters;
    }

    private boolean longRunTestFPIS_140_1(int[] arr)
    {
        return arr[0] < 34 && arr[1] < 34;
    }

    private boolean longRunTestFPIS_140_2(int[] arr)
    {
        return arr[0] < 26 && arr[1] < 26;
    }

    public void pokerTest()
    {
        DecimalFormat df = new DecimalFormat("#.##");
        double x = pokerTestCalculations();
        System.out.println("Poker Test");
        System.out.println("Value of poker test: " + df.format(x));

        if(pokerTestFPIS_140_1(x))
        {
            System.out.println("Poker Test FIPS 140-1 passed");
        }
        else System.out.println("Poker Test FIPS 140-1 failed");

        if(pokerTestFPIS_140_2(x))
        {
            System.out.println("Poker Test FIPS 140-2 passed");
        }
        else System.out.println("Poker Test FIPS 140-2 failed");
    }

    private double pokerTestCalculations()
    {
        String[] key_arr = this.key.split("");
        int[] value_arr = new int[16];
        int sum = 0;

        for(int i = 0; i < key_arr.length; i += 4)
        {
            String binary = key_arr[i] + key_arr[i+1] + key_arr[i+2] + key_arr[i+3];
            int decimal = Integer.parseInt(binary, 2);
            value_arr[decimal]++;
        }

        for (int value : value_arr) sum += Math.pow(value, 2);

        return 0.0032 * sum - 5000;
    }

    private boolean pokerTestFPIS_140_1(double x)
    {
        return 1.03 < x && x < 57.4;
    }

    private boolean pokerTestFPIS_140_2(double x)
    {
        return 2.16 < x && x < 46.17;
    }
}
