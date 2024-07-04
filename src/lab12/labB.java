package lab12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class labB {
    public static void main(String[] args) {
       QReader1 in=new QReader1();
        int n=in.nextInt();
        int k=in.nextInt();
        long[] arr=new long[n+1];
        for (int i = 1; i < n+1 ; i++) {
            arr[i]=in.nextLong();
        }
        long[] prexor=new long[n+1];
        long[][] opt=new long[k+1][n+1];




        for (int i = 1; i < n+1; i++) {
            prexor[i]=prexor[i-1]^arr[i];
        }

        for (int i = 1; i < n+1; i++) {
            opt[1][i]=xor(1,i,prexor);
        }

        for (int i = 2; i < k+1; i++) {
            for (int j = i; j < n+1; j++) {
                long max=Long.MIN_VALUE;
                for (int l = i-1; l <=j-1; l++) {
                    long tem=opt[i-1][l]+xor(l+1,j,prexor);
                   max= Math.max(max, tem);
                }
                opt[i][j]=max;

            }
        }
        System.out.println(opt[k][n]);

    }
    static long xor(int l,int r,long[] arr){
        return arr[r]^arr[l-1];
    }
}


class QReader1 {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}