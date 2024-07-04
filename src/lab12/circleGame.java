package lab12;

import java.util.Scanner;

public class circleGame {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while (t>0){
            long n=sc.nextLong();
            long m=sc.nextLong();
            long[][] mapPoint=new long[2][(int)n];
            long k=m%2;
            mapPoint[(int)k][0]=1;
            for (int i = (int)m; i >0; i--) {
                k=i%2;
                for (int j = 0; j < n; j++) {
                    if (j==0){
                        mapPoint[(int)(k+1)%2][j]=
                                (mapPoint[(int)k][j+1]+mapPoint[(int)k][(int)n-1])%((int)Math.pow(10,9)+7);
                    }else if (j==n-1){
                        mapPoint[(int)(k+1)%2][j]=
                                (mapPoint[(int)k][0]+mapPoint[(int)k][j-1])%((int)Math.pow(10,9)+7);
                    }else {
                        mapPoint[(int)(k+1)%2][j]=
                                (mapPoint[(int)k][j+1]+mapPoint[(int)k][j-1])%((int)Math.pow(10,9)+7);
                    }
                }
            }
            System.out.println(mapPoint[0][0]);
            t--;
        }
    }
}
