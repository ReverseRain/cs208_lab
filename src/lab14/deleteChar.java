package lab14;

import java.util.Scanner;

public class deleteChar {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String a=sc.nextLine();
        String b=sc.nextLine();
        char[]aNum=a.toCharArray();
        char[]bNum=b.toCharArray();
        int n=a.length(),m=b.length();
        int[][]dp=new int[n+1][m+1];
        for (int i = 1; i <=n ; i++) {
            dp[i][0]=i;
        }
        for (int i = 1; i <=m ; i++) {
            dp[0][i]=i;
        }
        for (int i = 1; i <=n ; i++) {
            for (int j = 1; j <=m ; j++) {
                if (aNum[i-1]==bNum[j-1]){
                    dp[i][j]=dp[i-1][j-1];
                }else {
                    dp[i][j]=Math.min(dp[i][j-1],dp[i-1][j])+1;
                }
            }
        }
        System.out.println(dp[n][m]);
    }
}
