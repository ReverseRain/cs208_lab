package lab12;

import java.util.Scanner;

public class xorSum {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),k=sc.nextInt();
        long[]num=new long[n];
        for (int i = 0; i < n; i++) {
            num[i]=sc.nextLong();
        }
        long[][] map=new long[n+1][k];
        for (int i = 1; i <=n ; i++) {
            map[i][0]=num[i-1]^map[i-1][0];
        }
        for (int i = 1; i <=n ; i++) {
            for (int j = 1; j <k&&j<i; j++) {
                long tem=0;
                for (int l = j+1; l <=i ; l++) {
                    long compare=(map[l-1][j-1]+(map[l-1][0]^map[i][0]));
                    if (compare>=tem){
                        tem=compare;
                    }
                }
                map[i][j]=tem;
            }
        }
        System.out.println(map[n][k-1]);

    }
}
