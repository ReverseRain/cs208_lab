package lab5;

import java.io.*;
import java.util.*;

public class pro1 {
    public static QWriterA out=new QWriterA();
    public static ArrayList<Integer>ans=new ArrayList<>();
    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
        QReaderA sc=new QReaderA();
        int t=sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n=sc.nextInt(),m=sc.nextInt();
            node1[]num=new node1[n+1];
            for (int j = 1; j <=n ; j++) {
                num[j]=new node1();
                num[j].id=j;
            }
            for (int j = 0; j < m; j++) {
                int x=sc.nextInt(),y=sc.nextInt();
                num[x].after.add(num[y]);
                num[y].before.add(num[x]);
                num[y].cnt++;
            }
            for (int j = 1; j <=n ; j++) {
                Collections.sort(num[j].before, Comparator.comparing(node1::getId));
            }
            boolean Nocycle=true;
            for (int j = 1; j <=n ; j++) {
                if (!num[j].isVisited){
                    if (num[j].cnt>0){
                        Nocycle=dfs(num[j]);
                    }else {
                        for (int k = 0; k < num[j].after.size(); k++) {
                            num[j].after.get(k).cnt--;
                        }
                        ans.add(j);
//                        out.print(j+" ");
                        num[j].isVisited=true;
                    }
                    if (!Nocycle){
                        break;
                    }
                }
            }
            if (!Nocycle){
                out.println(-1);
            }else {
                for (int j = 0; j < ans.size(); j++) {
                    out.print(ans.get(j));
                    out.print(" ");
                }
                out.println("");
            }
            ans=new ArrayList<>();
        }
        out.close();
    }
    public static boolean dfs(node1 start){
        if (start.isBefore){
            return false;
        }else if (start.isVisited){
            return true;
        }else if (start.cnt<=0){
            for (int i = 0; i < start.after.size(); i++) {
                start.after.get(i).cnt--;
            }
            ans.add(start.id);
//            out.print(start.id+" ");
            start.isVisited=true;
            return true;
        }
        boolean ans2;
        start.isVisited=true;
        start.isBefore=true;
        for (int i = 0; i < start.before.size(); i++) {
            ans2=dfs(start.before.get(i));
            if (!ans2){
                start.isBefore=false;
                return false;
            }
        }
        start.isBefore=false;
        ans.add(start.id);
//        out.print(start.id+" ");
        return true;
    }
}
class node1{
    int cnt=0;
    int id;
    boolean isVisited=false;
    boolean isBefore=false;
    ArrayList<node1> after=new ArrayList<>();
    ArrayList<node1> before=new ArrayList<>();

    public int getId() {
        return id;
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