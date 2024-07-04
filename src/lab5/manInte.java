package lab5;

import java.util.Scanner;

public class manInte {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        node2[]num=new node2[n];
        for (int i = 0; i < n; i++) {
            num[i]=new node2();
            num[i].name=sc.next();
            num[i].start=sc.nextInt();
            num[i].end= sc.nextInt();
        }
        node2[]stack=new node2[n];
        Sort(num,stack,0,n-1);
        int top=0;
        for (int i = 0; i <n ; i++) {
            if (top==0||stack[top-1].end<=num[i].start){
                stack[top++]=num[i];
            }
        }
        for (int i = 0; i < top; i++) {
            System.out.print(stack[i].name+" ");
        }
        if (top==0){
            System.out.print("Impossible");
        }
        System.out.println("");
    }
    public static void Merge(node2[]num,node2[]ans,long left,long right,long mid){
        int i=(int)left,j=(int)mid+1,m=(int)left;
        while (i<=mid&&j<=right){
            ans[m++]=num[i].end<=num[j].end?num[i++]:num[j++];
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
    static void Sort(node2[]num,node2[]ans,long left,long right){
        if (left<right){
            long mid=(left+right)/2;
            Sort(num,ans,left,mid);
            Sort(num,ans,mid+1,right);
            Merge(num,ans,left,right,mid);
        }
    }
}
class node2{
    String name;
    int start;
    int end;
}
