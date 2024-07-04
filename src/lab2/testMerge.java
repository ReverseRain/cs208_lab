package lab2;

import java.util.Scanner;

public class testMerge {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        long[] num=new long[n];
        for (int i = 0; i < n; i++) {
            num[i]=sc.nextInt();
        }
        long[]ans=new long[n];
        Sort(num,ans,0,n-1);
        for (int i = 0; i < n; i++) {
            System.out.print(num[i]+" ");
        }
    }
    public static void Merge(long[]num,long[]ans,long left,long right,long mid){
        int i=(int)left,j=(int)mid+1,m=(int)left;
        while (i<=mid&&j<=right){
            ans[m++]=num[i]<=num[j]?num[i++]:num[j++];
        }

        while (i<=mid){
            ans[m++]=num[i++];
        }
        while (j<=right){
            ans[m++]=num[j++];
        }
        for (int k = (int)left; k <=right ; k++) {
            num[k]=ans[k];
        }
    }
    static void Sort(long[]num,long[]ans,long left,long right){
        if (left<right){
            long mid=(left+right)/2;
            Sort(num,ans,left,mid);
            Sort(num,ans,mid+1,right);
            Merge(num,ans,left,right,mid);
        }
    }
}
