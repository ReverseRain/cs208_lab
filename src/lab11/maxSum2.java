package lab11;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class maxSum2 {
    public static ArrayList<nodeA2> list=new ArrayList<>();
    public static void main(String[] args) {
        long timeStart = System.currentTimeMillis();
        QReader2 sc=new QReader2();
        QWriter2 out=new QWriter2();
//        Scanner sc=new Scanner(System.in);
        long n=sc.nextLong(),m=sc.nextLong();
        long[]num=new long[(int)n];
        for (int i = 0; i < n; i++) {
            num[i]=sc.nextLong();
        }
        list.add(new nodeA2(1,0));
        nodeA2 start=new nodeA2(0,n-1);
        list.add(start);
                buildTree(0,n-1,num,start);
        for (int i = 1; i < list.size(); i++) {
            System.out.println(list.get(i).lMax+" "+list.get(i).rMax+" "+list.get(i).tMax);
        }

//        long timeEnd = System.currentTimeMillis();
//        long timeCost = timeEnd - timeStart;
//        System.out.println(timeCost);
        for (int i = 0; i < m; i++) {
            long left=sc.nextLong(),right=sc.nextLong();
            nodeA2 ans=test(left,right,1);
            System.
                    out.println(ans.tMax);
        }

//        timeEnd = System.currentTimeMillis();
//        timeCost = timeEnd - timeStart;
//        System.out.println(timeCost);out.close();
    }
    public static nodeA2 buildTree(long l,long r,long[]num,nodeA2 ans){

        if (l==r){
            ans.sum=num[(int)l];
            ans.lMax=num[(int)l];
            ans.rMax=num[(int)l];
            ans.tMax=num[(int)l];
//            System.out.println("叶节点 "+ans.tMax);
            return ans;
        }
        long mid=(l+r)/2;

        nodeA2 left=new nodeA2(l,mid);nodeA2 right=new nodeA2(mid+1,r);
        list.add(left);list.add(right);
                buildTree(l,mid,num,left);
                buildTree(mid+1,r,num,right);
        merge(left,right,ans);
//        System.out.println("中间节点 "+ans.tMax+" lMax "+ans.lMax+" rMax "+ans.rMax);
        return ans;
    }
    public static nodeA2 merge(nodeA2 left,nodeA2 right,nodeA2 ans){
        if (left==null){
            return right;
        }
        if (right==null){
            return left;
        }
        ans.sum=left.sum+right.sum;
        ans.lMax=Math.max(left.sum+ right.lMax,left.lMax);
        ans.rMax=Math.max(right.sum+ left.rMax, right.rMax);
        ans.tMax=Math.max(Math.max(right.tMax, left.tMax), right.lMax+ left.rMax);
        return ans;
    }
    public static nodeA2 test(long left,long right,int index){
//        System.out.println(index+" "+left+" "+right);
        nodeA2 point=list.get(index);
        if (point.l>=left&&point.r<=right){
//            System.out.println(point.lMax+" "+point.rMax+" "+point.tMax+" "+index);
            return point;
        }
        nodeA2 lNode=null,rNode=null;
        if (2*index<list.size()&&list.get(2*index).l<=right){
            lNode=test(left,right,2*index);
        }
        if (2*index+1<list.size()&&list.get(2*index+1).l<=right){
            rNode=test(left,right,2*index+1);
        }
        if (lNode==null){
//            System.out.println(rNode.lMax+" "+rNode.rMax+" "+rNode.tMax);
            return rNode;
        }else if (rNode==null){
//            System.out.println(lNode.lMax+" "+lNode.rMax+" "+lNode.tMax);
            return lNode;
        }else {
            nodeA2 ans=new nodeA2(lNode.l,rNode.r);
            merge(lNode,rNode,ans);
//            System.out.println(ans.lMax+" "+ans.rMax+" "+ans.tMax);
            return ans;
        }

    }
}
class nodeA2{
    long sum;
    long lMax;
    long rMax;

    public nodeA2( long l, long r) {
        this.l = l;
        this.r = r;
    }

    long tMax;
    long l;
    long r;
}
class QReader2 {
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

class QWriter2 implements Closeable {
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
