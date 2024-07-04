package lab4;

import java.util.Scanner;

public class pra2 {
    public static int[][]direction={{-2,-1},{-1,-2},{2,1},{1,2},{-1,2},{2,-1},{1,-2},{-2,1}};
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[][]map=new int[n+1][n+1];
        int x1=sc.nextInt()+n/2;
        int y1=sc.nextInt()+n/2;
        int x2=sc.nextInt()+n/2;
        int y2=sc.nextInt()+n/2;
        map[x1][y1]=-1;
        int[]queue1=new int[(n+1)*(n+1)];
        int[]queue2=new int[(n+1)*(n+1)];
        int[]queueAns=new int[(n+1)*(n+1)];
        int head=0,rear=0;
        queueAns[rear]=0;
        queue1[rear]=x1;
        queue2[rear++]=y1;
        boolean isFind=false;
        while (head<rear){
            int ans=head;
            int x=queue1[head];
            int y=queue2[head++];
            for (int i = 0; i < direction.length; i++) {
                int xNew=x+direction[i][0];
                int yNew=y+direction[i][1];
                if (xNew>=0&&xNew<=n&&yNew>=0&&yNew<=n){
                    if (map[xNew][yNew]!=-1){
                        queue1[rear]=xNew;
                        queue2[rear]=yNew;
                        queueAns[rear++]=queueAns[ans]+1;
                        map[xNew][yNew]=-1;
                    }
                }
                if (xNew==x2&&yNew==y2){
                    System.out.println(queueAns[rear-1]);
                    isFind=true;
                    break;
                }
            }
            if (isFind){
                break;
            }
        }
    }
}
class node2{
    int x,y;
    public node2(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
