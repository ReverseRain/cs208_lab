package lab8;

import java.util.Scanner;

public class LRnum {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        for (int i = 0; i < n; i++) {
            int start=sc.nextInt(),end=sc.nextInt();
            System.out.println(search((int)(Math.sqrt(2*(end+1))),start, end, true));
        }


    }
    public static int search(int n,int start,int end,boolean L){
        int len=(int)Math.pow(2,n)-1;
        System.out.println("start = "+start+" end = "+end+" n = "+n+" len = "+len);
        if (end==start||n==1){
            if (L){
                return 1;
            }else {
                return 0;
            }
        }else if (end<(len+1)/2){
            return search(n-1,start,end,L);
        } else if (start>=(len+1)/2) {
            return search(n-1,-(len+1)/2+start,-(len+1)/2+end,!L);
        }else {

            return search(n-1,start,(len-1)/2,L)+search(n-1,1,-(len+1)/2+end,!L)+1;
        }
    }
}
