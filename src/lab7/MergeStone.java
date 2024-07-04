package lab7;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MergeStone {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        long t=sc.nextLong();
        while (t>0){
            long n=sc.nextLong(),k=sc.nextLong();
            Comparator<Long> itemComparator = new Comparator<Long>() {
                @Override
                public int compare(Long o1, Long o2) {
                    return Long.compare(-o1, -o2);
                }
            };
            PriorityQueue<Long> queue1 = new PriorityQueue<>(itemComparator);
            Comparator<Long> itemComparator2 = new Comparator<Long>() {
                @Override
                public int compare(Long o1, Long o2) {
                    return Long.compare(o1, o2);
                }
            };
            PriorityQueue<Long> queue2 = new PriorityQueue<>(itemComparator2);
            for (int i = 0; i < n; i++) {
                long tem=sc.nextLong();
                queue1.add(tem);
                queue2.add(tem);
            }
            long max=0;
            while (queue1.size()>1){
                long a=queue1.poll();
                long b= queue1.poll();
                max+=(a+b);
                queue1.add(a+b);
            }
            System.out.print(max+" ");
            long min=0;
            long y=queue2.size()%(k)+1;
            long tem=0;
            while (y>0&&queue2.size()>0){
                tem+=queue2.poll();
                y--;
            }
            min+=tem;
            queue2.add(tem);
            while (queue2.size()>1){
                tem=0;
                y=k;
                while (y>0&&queue2.size()>0){
                    tem+=queue2.poll();
                    y--;
                }
                min+=tem;
            }
            System.out.println(min);
            t--;
        }
    }
}
