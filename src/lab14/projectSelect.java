package lab14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class projectSelect {
    public static void main(String[] args) {
        QReaderB sc=new QReaderB();
//        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt();
        nodeB[]num=new nodeB[n+1];
        nodeB source=new nodeB();
        nodeB sink=new nodeB();
        int c=0;
        for (int i = 0; i < n; i++) {
            int value=sc.nextInt();
            num[i+1]=new nodeB();
            if (value>=0){
                source.children.add(num[i+1]);
                source.value.add(value);
//                c+=value;
            }else {
                num[i+1].children.add(sink);
                num[i+1].value.add(-value);
            }
        }
        for (int i = 0; i < m; i++) {
            int x=sc.nextInt(),y=sc.nextInt();
            num[y].children.add(num[x]);
            num[y].value.add((int)1E14);
        }
        nodeB[]queue=new nodeB[n+2];

        while (true) {
            queue[0] = source;
            source.isVisited = true;
            ArrayList<nodeB> path = new ArrayList<>();
            if (bfs(queue, path, sink,1)) {
                nodeB tem = sink;
                int minCa = (int) 1E12;
                while (tem != source) {
                    path.add(tem);
                    minCa = Math.min(tem.parentValue, minCa);
                    tem = tem.parent;
                }
                for (int i = 0; i < path.size(); i++) {
                    nodeB tem2 = path.get(i);
                    tem2.parent.value.set(tem2.parentID,tem2.parent.value.get(tem2.parentID)-minCa);
                    tem2.children.add(tem2.parent);
                    tem2.value.add(minCa);
                }
            }else {
                break;
            }
        }
        for (int i = 0; i < source.children.size(); i++) {
            c+=source.value.get(i);
        }
        System.out.println(c);
    }
    public static boolean bfs(nodeB[]queue,ArrayList<nodeB>path,nodeB sink,int end){
        int start=0;
        while (start<end){
            nodeB tem=queue[start++];
            for (int i = 0; i < tem.children.size(); i++) {
                nodeB tem2=tem.children.get(i);
                if (!tem2.isVisited&&
                        tem.value.get(i)>0){
                    tem2.isVisited=true;
                    queue[end++]=tem2;
                    tem2.parent=tem;
                    tem2.parentValue=tem.value.get(i);
                    tem2.parentID=i;
                    if (tem2==sink){
                        start=0;
                        while (start<end){
                            queue[start++].isVisited=false;
                        }
                        return true;
                    }
                }

            }
        }
        start=0;
        while (start<end){
            queue[start++].isVisited=false;
        }

        return false;

    }
}
class nodeB{
    ArrayList<Integer> value=new ArrayList<>();
    ArrayList<nodeB> children=new ArrayList<>();
    nodeB parent;
    int parentValue;
    int parentID;
    boolean isVisited=false;
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