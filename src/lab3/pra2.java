package lab3;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class pra2 {
    public static void main(String[] args) {
        QReader1 sc=new QReader1();
        QWriter1 out=new QWriter1();
        long n=sc.nextInt();
        long m=sc.nextInt();
        node2[]num=new node2[(int)n+1];
        for (int i = 1; i <=n; i++) {
            num[i]=new node2();
        }
        long a=sc.nextInt();
        long b=sc.nextInt();
        int x,y;
        for (int i = 0; i < m; i++) {
            x=sc.nextInt();y=sc.nextInt();
            num[x].children.add(num[y]);
            num[y].children.add(num[x]);
        }
        num[(int)b].isVisited=true;
        num[(int)b].isVisited2=true;
        node2[] queue1=new node2[(int)n+1];
        long head=0,rear=0;
        queue1[(int) rear++]=num[(int)a];
        num[(int)a].isVisited2=true;
        num[(int)a].isVisited=true;
        node2 start,tem;
        while (head<rear){
            start=queue1[(int) head++];
            for (int i = 0; i <start.children.size() ; i++) {
                tem=start.children.get(i);
                if (!tem.isVisited){
                    tem.isVisited=true;
                    queue1[(int) rear++]=tem;
                }
            }
        }
        long head2=0,rear2=0;
        node2[] queue2=new node2[(int)n+1];
        queue2[(int) rear2++]=num[(int)b];
        while (head2<rear2){
            start=queue2[(int)head2++];
            for (int i = 0; i <start.children.size() ; i++) {
                tem=start.children.get(i);
                if (!tem.isVisited2){
                    tem.isVisited2=true;
                    queue2[(int)rear2++]=tem;
                }
            }
        }
        for (int i = 1; i <=n ; i++) {
            if (num[i].isVisited&&num[i].isVisited2&&i!=a&&i!=b){
                rear--;
                rear2--;
            }
        }
        long ans=(rear-1)*(rear2-1);
        out.print(ans);
        out.close();
    }
}
class node2{
    boolean isVisited=false;
    boolean isVisited2=false;
    ArrayList<node2> children=new ArrayList<>();
}
class QWriter1 implements Closeable {
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
class QReader1 {
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