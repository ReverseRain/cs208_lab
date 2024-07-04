package lab11;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class maxSum {

    public static void main(String[] args) {
        QReaderA sc=new QReaderA();
        QWriterA out=new QWriterA();
//        Scanner sc=new Scanner(System.in);
        long n=sc.nextLong(),m=sc.nextLong();
        long[]num=new long[(int)n];
        for (int i = 0; i < n; i++) {
            num[i]=sc.nextLong();
        }
        nodeA start=buildTree(0,n-1,num);


        for (int i = 0; i < m; i++) {
            long left=sc.nextLong(),right=sc.nextLong();
            nodeA ans=test(left-1,right-1,start);
//            System.
                    out.println(ans.tMax);
        }


        out.close();
    }
    public static nodeA buildTree(long l,long r,long[]num){
        if (l==r){
            nodeA ans=new nodeA(l,r);
            ans.sum=num[(int)l];
            ans.lMax=num[(int)l];
            ans.rMax=num[(int)l];
            ans.tMax=num[(int)l];
            return ans;
        }
        long mid=(l+r)/2;
        nodeA left=buildTree(l,mid,num);
        nodeA right=buildTree(mid+1,r,num);
        nodeA ans=merge(left,right);
        return ans;
    }
    public static nodeA merge(nodeA left,nodeA right){
        nodeA ans=new nodeA(left.l,right.r);
        ans.sum=left.sum+right.sum;
        ans.lMax=Math.max(left.sum+ right.lMax,left.lMax);
        ans.rMax=Math.max(right.sum+ left.rMax, right.rMax);
        ans.tMax=Math.max(Math.max(right.tMax, left.tMax), right.lMax+ left.rMax);
        ans.left=left;
        ans.right=right;
        return ans;
    }
    public static nodeA test(long left,long right,nodeA point){
        if (point.l>=left&&point.r<=right){
            return point;
        }
        if (point.l>right||point.r<left){
            return null;
        }
        nodeA lNode=null,rNode=null;
        if (point.left!=null&&point.left.l<=right){
            lNode=test(left,right,point.left);
        }
        if (point.right!=null&&point.right.l<=right){
            rNode=test(left,right,point.right);
        }
        if (lNode==null){
            return rNode;
        }else if (rNode==null){
            return lNode;
        }else {
            return merge(lNode,rNode);
        }

    }
}
class nodeA{
    long sum;
    long lMax;
    long rMax;
    nodeA left;
    nodeA right;

    public nodeA( long l, long r) {
        this.l = l;
        this.r = r;
    }

    long tMax;
    long l;
    long r;
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

class QWriterA implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}
