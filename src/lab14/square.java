package lab14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class square {
    public static void main(String[] args) {
        QReaderA sc=new QReaderA();
//        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt();
        int[][]map=new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j]=sc.nextInt();
            }
        }
        int[][]dp=new int[n][m];
        int ans=0;
        for (int i = 0; i < n; i++) {
            dp[i][0]=map[i][0];
            ans=Math.max(dp[i][0],ans);
        }
        for (int i = 0; i < m; i++) {
            dp[0][i]=map[0][i];
            ans=Math.max(dp[0][i],ans);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (map[i][j]==0){
                    dp[i][j]=0;
                }else {
                    if (dp[i-1][j]>0
                            &&dp[i-1][j-1]>0
                            &&dp[i][j-1]>0){
                        dp[i][j]=Math.min(dp[i-1][j],Math.min(dp[i-1][j-1],dp[i][j-1]))+1;
                    }else {
                        dp[i][j]=1;
                    }
                }
                ans=Math.max(dp[i][j],ans);
            }
        }
        System.out.println(ans);
    }
}
class QReaderA {
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
