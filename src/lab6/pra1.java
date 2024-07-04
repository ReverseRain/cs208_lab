package lab6;

import java.util.Arrays;
import java.util.Scanner;

public class pra1 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        node1[]num=new node1[n];
        for (int i = 0; i < n; i++) {
            num[i]=new node1();
            num[i].start=sc.nextInt();
            num[i].end=sc.nextInt();
        }
        Arrays.sort(num, (n1, n2) -> Integer.compare(n1.start, n2.start));
        int cnt=1;
        node1 tem=num[0];
        for (int i = 1; i < n; i++) {
            if (num[i].start>tem.end){
                System.out.println(i);
                cnt++;
                tem=num[i];
            }
        }
        System.out.println(cnt);
    }
}
class node1{
    int start;
    int end;

    public int getStart() {
        return start;
    }
}
