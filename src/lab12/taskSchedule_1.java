package lab12;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class taskSchedule_1 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        ArrayList<task>num=new ArrayList<>();
//        task[] num=new task[n];
        for (int i = 1; i <=n ; i++) {
            num.add(new task(i,sc.nextInt()));
        }
        for (int i = 0; i <n ; i++) {
            num.get(i).penalty=sc.nextInt();
        }
        num.sort(new Comparator<task>() {
            @Override
            public int compare(task p1, task p2) {
                return Integer.compare(p2.penalty, p1.penalty);
            }
        });
        task[]ans=new task[n+1];
        int k=n;
        for (int i = 0; i <n ; i++) {
            int j=num.get(i).deadlines;
            while (j>=1&&ans[j]!=null){
                j--;
            }
            if (j<=0){
                while (ans[k]!=null){
                    k--;
                }
                ans[k]=num.get(i);
                k--;
            }else {
                ans[j]=num.get(i);
            }
        }
        for (int i = 1; i < n+1; i++) {
            System.out.print(ans[i].id+" ");
        }
        System.out.println("");
    }
}
class task{
    int id;
    int deadlines;
    int penalty;

    public task(int id, int deadlines) {
        this.id = id;
        this.deadlines = deadlines;
//        this.penalty = penalty;
    }
}
/*
7
3 3 3 3 3 6 3
70 60 50 40 30 20 10
 */
