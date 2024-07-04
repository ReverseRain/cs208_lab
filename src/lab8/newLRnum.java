package lab8;

import java.util.Scanner;

public class newLRnum {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        for (int i = 0; i < n; i++) {
            int x=sc.nextInt(),y=sc.nextInt();
            int t1=0,t2=0;
            while (x-1>Math.pow(2,t1)-1){
                t1++;
            }
            while (y>Math.pow(2,t2)-1){
                t2++;
            }
            System.out.println(NumberOfL(y,t2)-NumberOfL(x-1,t1));
        }
    }
    public static int NumberOfL(int end,int power){
        if (end==0){
            return 0;
        }
        if (end==Math.pow(2,power)-1){
            return (int)Math.pow(2,power-1);
        }

        int offset=(int)Math.pow(2,power-1);
        int end2=offset-(end-offset)-1;
        int end1=offset-1;

        int ans=1+offset/2+end-offset-(NumberOfL(end1,power-1)-NumberOfL(end2,power-1));

        return ans;
    }
}
