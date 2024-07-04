package lab4;

import java.util.ArrayList;
import java.util.Scanner;

public class pra1 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        node[]list=new node[n];
        for (int i = 0; i < n; i++) {
            list[i]=new node(sc.nextInt());
        }
        for (int i = 0; i < n; i++) {
            int t=sc.nextInt();
            list[i].children.add(list[t-1]);
        }
        for (int i = 1; i <n ; i++) {
            list[i-1].children.add(list[i]);
            list[i].children.add(list[i-1]);
        }
        node[]queue=new node[n];
        int head=0,rear=0;
        queue[rear++]=list[0];
        list[0].cnt=0;
        list[0].isVisited=true;
        while (head<rear){
            node tem=queue[head++];
            for (int i = 0; i < tem.children.size(); i++) {
                node target=tem.children.get(i);
                if (!target.isVisited){
                    queue[rear++]=target;
                    target.isVisited=true;
                    target.cnt=tem.cnt+1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(list[i].cnt+" ");
        }
    }
}
class node{
    int point;

    public node(int point) {
        this.point = point;
    }

    ArrayList<node> children=new ArrayList<>();
    boolean isVisited=false;
    int cnt;
}