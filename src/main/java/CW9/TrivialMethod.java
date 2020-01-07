package CW9;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;

public class TrivialMethod {
    
    private int k;
    private int n;
    private int s;
    private int o;
    private int[] arr;
    
    public TrivialMethod(int k)
    {
        this.k = k;
        this.n = rand() - 1;
        this.s = rand() - 1;
        this.o = 0;
        System.out.println("k: " + this.k + ", secret: " + this.s + ", n: " + this.n);
        this.arr = createArr();
    }
    
    private int rand()
    {
        Random random = new Random();
        return random.nextInt(this.k);
    }
    
    private int[] createArr()
    {
        int[] temp = new int[this.n];
        for(int i = 0; i < this.n; i++)
        {
            temp[i] = rand();
        }
        System.out.println(Arrays.toString(temp));
        return temp;
    }

    private void split()
    {
        int temp = this.s;
        for(int i : this.arr)
        {
            temp -= i;
        }
        this.o = temp % this.k;
    }

    private void recovery()
    {
        int temp = this.o;
        for(int i : this.arr)
        {
            temp += i;
        }
        int result = temp % this.k;
        System.out.println("secret: " + temp + " % " + this.k + " = " + result);
    }

    public void init()
    {
        split();
        recovery();
    }
}
