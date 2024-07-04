package lab5;

import java.io.*;
import java.util.*;

public class path {
    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
        QReaderB sc=new QReaderB();
        QWriterB out=new QWriterB();
        long n=sc.nextInt();
        long s=sc.nextInt();
        nodeB[]num=new nodeB[(int)n+1];
        for (int i = 1; i <=n ; i++) {
            num[i]=new nodeB();
        }
        long x,y,w;
        for (int i = 0; i < n-1; i++) {
            x=sc.nextInt();y=sc.nextInt();
            w= sc.nextInt();
            num[(int) x].list.add(num[(int)y]);
            num[(int)y].list.add(num[(int)x]);
            num[(int)x].weight.add(w);
            num[(int)y].weight.add(w);
        }
        dfs(num[1]);
        nodeB u=num[1].d.get(0);
        for (int i = 1; i <=n ; i++) {
            num[i].isVisited=false;
            num[i].d=new ArrayList<>();
        }
        long dia=dfs(u);
        for (int i = 0; i < u.d.size(); i++) {
            u.d.get(i).isVisited=false;
        }
        if (dia<=s){
            long max=0;
            for (int i = 0; i <u.d.size() ; i++) {
                max=Math.max(max,dfs2(u.d.get(i)));
            }
//            System.
                    out.println(max);
        }else {
//            System.
                    out.println(towPoint(u.d,dia,s));
        }
        out.close();
    }
    public static long dfs(nodeB start){
        start.isVisited=true;
        long cnt=0;
        for (int i = 0; i < start.list.size(); i++) {
            nodeB tem=start.list.get(i);
            if (!tem.isVisited){
                long temCnt=dfs(tem)+start.weight.get(i);
                if (temCnt>cnt){
                    cnt=temCnt;
                    start.d=tem.d;
                    start.way=start.weight.get(i);
                }
            }
        }
        start.d.add(start);
        return cnt;
    }
    public static long dfs2(nodeB start){
        start.isVisited=false;
        long cnt=0;
        for (int i = 0; i < start.list.size(); i++) {
            nodeB tem=start.list.get(i);
            if (tem.isVisited){
                long temCnt=dfs2(tem)+start.weight.get(i);
                if (temCnt>cnt){
                    cnt=temCnt;
                }
            }
        }
        return cnt;
    }
    public static long towPoint(ArrayList<nodeB> list,long path,long s){
        long start=0,end=0;
        long ans=path;
        long len=0;
        long left=0;
        while (end<list.size()){
            if (end!=start){
                len+=list.get((int)end).way;
            }
            while (len>s&&start+1<list.size()){
                start++;
                len-=list.get((int) start).way;
                left+=list.get((int)start).way;
            }
            ans= Math.min(ans,Math.max(left,path-(left+len)));
            end++;
        }
        return ans;
    }

}
class nodeB{
    ArrayList<nodeB> list=new ArrayList<>();
    ArrayList<Long> weight=new ArrayList<>();
    ArrayList<nodeB> d=new ArrayList<>();
    boolean isVisited=false;
    long way=0;
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
class QWriterB implements Closeable {
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
