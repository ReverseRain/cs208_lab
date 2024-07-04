package lab13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class stocks {
    public static void main(String[] args) {
        QReader1 sc=new QReader1();
//        Scanner sc=new Scanner(System.in);
        int m=sc.nextInt(),n=sc.nextInt(),d=sc.nextInt();
        int t1=1;
        long[][]num=new long[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                num[i][j]=sc.nextLong();
            }
        }

        while (t1<=n-1){
            long[] dp=new long[d+1];
            for (int i = 1; i <=m; i++) {
                for (long j = 1; j <=d ; j++) {
                    if (j-num[i][t1]>=0){
                        dp[(int)j]=Math.max(dp[(int)j],dp[(int)(j-num[i][t1])]-num[i][t1]+num[i][t1+1]);
                    }
                }
            }
            d+=dp[d];
            t1++;
        }
        System.out.println(d);
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
