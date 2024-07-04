package lab9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class swapTime {
    public static long a=0;
    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
        QReaderB sc=new QReaderB();
        long n=sc.nextLong();
        node[]numA=new node[(int)n];
        node[]numB=new node[(int)n];
        for (int i = 0; i < n; i++) {
            numA[i]=new node(sc.nextLong(),i);
        }
        for (int i = 0; i < n; i++) {
            numB[i]=new node(sc.nextLong(),i);
        }
        Arrays.sort(numA, (n1, n2) -> Long.compare(n1.value, n2.value));
        Arrays.sort(numB, (n1, n2) -> Long.compare(n1.value, n2.value));
        long[]num=new long[(int)n];
        for (int i = 0; i < n; i++) {
            num[numA[i].index]=numB[i].index;
        }
        long[]ans=new long[(int)n];
        Sort(num,ans,0,n-1);
        System.out.println(a);
    }
    public static void Merge(long[]num,long[]ans,long left,long right,long mid){
        int i=(int)left,j=(int)mid+1,m=(int)left;
        while (i<=mid&&j<=right){
//            ans[m++]=num[i]<=num[j]?num[i++]:num[j++];
            if (num[i]<=num[j]){
                ans[m++]=num[i++];
            }else {
                ans[m++]=num[j++];
                a+=(mid+1-i);
            }
        }

        while (i<=mid){
            ans[m++]=num[i++];
            a+=(mid+1-i);
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
class QReaderB {
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
class node{
    long value;

    public node(long value, int index) {
        this.value = value;
        this.index = index;
    }

    int index;
}
