package lab12;

import java.util.Scanner;

public class comXOR {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),k=sc.nextInt();
        long[]num=new long[n];
        for (int i = 0; i < n; i++) {
            num[i]=sc.nextLong();
        }

        long[] pre=new long[1+n];
        pre[1]=num[0];
        for (int i = 2; i <=n ; i++) {
            pre[i]=num[i-1]^pre[i-1];
        }
        long[][]map=new long[n+1][2];
        for (int i = 1; i <=n; i++) {
            map[i][1]=pre[i];
            for (int j = 2; j <=k; j++) {
                long tem=0;
                for (int l = j; l <=i ; l++) {
                    long compare=(map[l-1][(j+1)%2]+(pre[l-1]^pre[i]));
                    if (compare>=tem){
                        tem=compare;

                    }
                }
            }
        }
        System.out.println(map[n][k%2]);
    }

}
