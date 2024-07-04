package lab12;

import java.util.Scanner;

public class taskSchedule_2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        task2[] num=new task2[n+1];
        for (int i = 1; i <=n; i++) {
            num[i]=new task2(i-1, sc.nextInt(),sc.nextInt(),sc.nextInt());
        }
        int total=0;
        for (int i = 1; i <=n; i++) {
            total=Math.max(num[i].deadline,total);
        }
//        System.out.println(total);
        int[][]map=new int[n+1][total+1];
        for (int i = 0; i <=total ; i++) {
            if (i<num[1].time){
                map[1][i]=num[1].penalty;
            }else {
                map[1][i]=0;
            }
        }
        for (int i = 2; i <=n ; i++) {
            for (int j = 0; j <=total; j++) {
                if (j<num[i].time){
                    map[i][j]=map[i-1][j]+num[i].penalty;
                }else {
                    map[i][j]=Math.min(map[i-1][Math.min(j,num[i].deadline)-num[i].time],map[i-1][j]+num[i].penalty);
                }
            }
        }
        System.out.println(map[n][total]);
        /*
        1 2 3 4 5
        1 2 3 4 5
        1 2 3 4 55
         */
        /*
        1 1 1
        2 2 2
        3 3 3
        4 4 4
        5 5 55
         */
    }
}
class task2{
    int id;
    int time;
    int deadline;
    int penalty;

    public task2(int id, int time, int deadline, int penalty) {
        this.id = id;
        this.time = time;
        this.deadline = deadline;
        this.penalty = penalty;
    }
}
