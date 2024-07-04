package lab5;

import java.lang.reflect.Array;
import java.util.*;

public class pra1 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt();
        nodeA[]num=new nodeA[n+1];
        for (int i = 1; i <= n; i++) {
            num[i]=new nodeA();
            num[i].id=i;
        }
        for (int i = 0; i < m; i++) {
            int x=sc.nextInt(),y=sc.nextInt();
            num[x].children.add(num[y]);
            num[y].cnt++;
        }
        int top=0;
        Comparator<nodeA> itemComparator = new Comparator<nodeA>() {
            @Override
            public int compare(nodeA o1, nodeA o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        };
        PriorityQueue<nodeA> queue = new PriorityQueue<>(itemComparator);
        for (int i = 1; i <=n; i++) {
            queue.add(num[i]);
        }
//        while (top<n){
//            for (int i = 1; i <=n ; i++) {
//                if (num[i].cnt==0){
//                    for (int j = 0; j < num[i].children.size(); j++) {
//                        num[i].children.get(j).cnt--;
//                    }
//                    System.out.print(i+" ");
//                    num[i].cnt--;
//                    break;
//                }
//            }
//            top++;
//        }
        while (!queue.isEmpty()){
            nodeA tem=queue.poll();
            while (tem.cnt!=0){
                nodeA tem2=tem;
                tem=queue.poll();
                queue.add(tem2);
            }
            for (int i = 0; i < tem.children.size(); i++) {
                tem.children.get(i).cnt--;
            }
            System.out.print(tem.id+" ");
        }
        System.out.println("");
    }
}
class nodeA{
    int cnt=0;
    int id;
    ArrayList<nodeA> children=new ArrayList<>();

    public int getId() {
        return id;
    }
}
