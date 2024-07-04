package lab14;

import java.util.Scanner;

public class countSub {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt();
        int[] num=new int[a];
        for (int i = 0; i < a; i++) {
            num[i]=sc.nextInt();
        }
        System.out.println(dp(num,a));
        int[] ans=new int[3];
        merge(0,a-1,ans,num);
        System.out.println(ans[2]);
        System.out.println(greedy(num,a));
    }
    public static void merge(int l,int r,int[]LR,int[]num){
        if (l==r){
            LR[0]=LR[1]=LR[2]=num[l];
            return;
        }
        int[] L_lr=new int[3];
        int[] R_lr=new int[3];
        int mid=(l+r)/2;
        merge(l,mid,L_lr,num);
        merge(mid+1,r,R_lr,num);
        int t=l;
        int sumL=0;
        while (t<=r){
            sumL+=num[t];
            LR[0]=Math.max(sumL,LR[0]);
            t++;
        }
        t=r;
        int sumR=0;
        while (t>=l){
            sumR+=num[t];
            LR[1]=Math.max(sumR,LR[1]);
            t--;
        }
        LR[2]=Math.max(L_lr[2],R_lr[2]);
        LR[2]=Math.max(L_lr[1]+R_lr[0],LR[2]);
    }

    public static int dp(int[]num,int n){
        int a=num[0];
        int b=0;
        int ans=a;
        for (int i = 1; i < n; i++) {
            b=Math.max(a+num[i],num[i]);
            ans=Math.max(b,ans);
            a=b;
        }
        return ans;
    }

    public static int greedy(int[]num,int n){
        int preSum=0,maximumSum=0;
        for(int i = 0; i < n; i++){
            if (preSum < 0)
                preSum = 0;
            preSum = preSum + num[i];
            if (preSum > maximumSum)
                maximumSum = preSum;
        }
        return maximumSum;
    }
}
