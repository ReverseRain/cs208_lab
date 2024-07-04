package lab9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class pairs {
    public static long L,R;
    public static long count=0;
    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
        QReaderA sc=new QReaderA();
        long n=sc.nextLong();
        L=sc.nextLong();R=sc.nextLong();
        long[] num=new long[(int)n];
        for (int i = 0; i <n; i++) {
            num[i]=sc.nextLong();
        }
        long[]pre=new long[(int)n];
        pre[0]=num[0];
        for (int i = 1; i <n ; i++) {
            pre[i]=pre[i-1]+num[i];
        }
        long[]ans=new long[(int)n];
        Sort(pre,ans,0,n-1);
        long start=-1,end=-1;
        for (int i = 0; i < n; i++) {
            if (pre[i]>=L&&start==-1){
                start=i;
            }
            if (pre[i]>R&&end==-1){
                end=i;
            }
            if (start!=-1&&end!=-1){
                break;
            }
        }
        if (end==-1){
            end=n;
        }
        count+=(end-start);
        System.out.println(count);
    }
    public static void Merge(long[]num,long[]ans,long left,long right,long mid){
        int start=(int)left,jL=(int)mid+1,jR=(int)mid+1;
        while (start<=mid){
            while (jL<=right&&!(num[jL]-num[start]>=L)){
                jL++;
            }
            while (jR<=right&&!(num[jR]-num[start]>R)){
                jR++;
            }
            count+=(jR-jL);
            start++;
        }
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
class QReaderA {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}
