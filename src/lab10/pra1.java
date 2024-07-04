package lab10;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class pra1 {
    public static void main(String[] args) {
        long timeStart = System.currentTimeMillis();
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        nodeA[]num=new nodeA[n];
        for (int i = 0; i < n; i++) {
            num[i]=new nodeA(sc.nextInt(),sc.nextInt());
        }
        Arrays.sort(num, (n1, n2) -> Integer.compare(n1.x, n2.x));
        long ans=closest(num,0,n-1);
        System.out.println(ans);
        long timeEnd = System.currentTimeMillis();
        long timeCost = timeEnd - timeStart;
//        System.out.println(timeCost);
    }
    public static long closest(nodeA[]num,int start,int end){

        if (start>=end){
            return Long.MAX_VALUE;
        }
        int mid=(start+end)/2;
        long left=closest(num,start,mid);
        long right=closest(num,mid+1,end);
        long ans=Math.min(left,right);
        int lS=mid,rS=mid+1;
        ArrayList<nodeA> tem=new ArrayList<>();
        while (lS>=start&&Math.pow(num[lS].x-num[mid].x,2)<=ans){
            tem.add(num[lS]);
            lS--;
        }

        while (rS<=end&&Math.pow(num[mid+1].x-num[rS].x,2)<=ans){
            tem.add(num[rS]);
            rS++;
        }
        tem.sort((n1, n2) -> Integer.compare(n1.y, n2.y));
        for (int i = 0; i < tem.size(); i++) {
            int cnt=1;
            while (cnt<=11&&i+cnt<tem.size()){
                nodeA temNode=tem.get(i+cnt);

                ans=Math.min(ans,(long)Math.pow(temNode.x-tem.get(i).x,2)
                        +(long)Math.pow(temNode.y-tem.get(i).y,2));
                cnt++;
            }
        }
        return ans;
    }
}
class nodeA{
    int x;
    int y;
    public nodeA(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
