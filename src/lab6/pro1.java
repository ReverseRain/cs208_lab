package lab6;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class pro1 {
    public static void main(String[] args) {
        QReader1 sc=new QReader1();
        QWriter1 out=new QWriter1();
//        Scanner sc=new Scanner(System.in);
        long n=sc.nextLong(),k=sc.nextLong();
        Comparator<nodeA> itemComparator = new Comparator<nodeA>() {
            @Override
            public int compare(nodeA o1, nodeA o2) {
                return Long.compare(-o1.getA(), -o2.getA());
            }
        };
        PriorityQueue<nodeA> queue = new PriorityQueue<>(itemComparator);
        nodeA[] num=new nodeA[(int)n];
        num[0]=new nodeA();
        num[0].a=sc.nextLong();
        for (int i = 1; i < n; i++) {
            num[i]=new nodeA();
            num[i].a=sc.nextLong();
            num[i-1].back=num[i];
            num[i].front=num[i-1];
        }
        for (int i = 0; i < n; i++) {
            queue.add(num[i]);
        }
        long ans=0;
        while (k>0&& !queue.isEmpty()){
            nodeA tem=queue.poll();
            while (tem.isVisited){
                tem=queue.poll();
            }
            tem.isVisited=true;
            if (tem==null||tem.a<=0){
                k=0;
                break;
            }
            ans+=tem.a;
//            queue.remove(tem.back);
//            queue.remove(tem.front);
            nodeA re=regret(tem);
            if (re.a>0){
                queue.add(re);
            }
            k--;
        }
//        System.
                out.println(ans);
        out.close();
    }
    public static nodeA regret(nodeA s){
        nodeA ans=new nodeA();
        long a=-s.a;
        if (s.front!=null){
            a+=s.front.a;
            s.front.isVisited=true;
            if (s.front.front!=null){
                s.front.front.back=ans;
                ans.front=s.front.front;
            }
        }
        if (s.back!=null){
            a+=s.back.a;
            s.back.isVisited=true;
            if (s.back.back!=null){
                s.back.back.front=ans;
                ans.back=s.back.back;
            }
        }
        ans.a=a;
        return ans;
    }
}
class nodeA{
    long a;
    nodeA front;
    nodeA back;
    boolean isVisited=false;
    public long getA() {
        return a;
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
