package lab3;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class pra1 {
    public static int count=0;
    public static StringBuilder tem=new StringBuilder();
    public static StringBuilder sb=new StringBuilder();
    public static void main(String[] args) {
        QReader sc=new QReader();
        int n=sc.nextInt();
        int m=sc.nextInt();
        node start=new node(".");
        start.type="d";
        for (int i = 0; i < n; i++) {
            String b=sc.nextLine();
            String[]list=b.split(" ");
            String[]path;
            switch (list[0]){
                case "echo":
                    path=list[list.length-1].split("/");
                    node file=find(path,start,true);
                    if (list.length-3>0){
                        file.content=list[list.length-3].trim();
                    }else {
                        file.content="";
                    }
                    file.type="f";
                    break;
                case"mkdir":
                    path=list[1].split("/");
                    node dir=find(path,start,true);
                    dir.type="d";
                    break;
                case "rm":
                    path=list[list.length-1].split("/");
                    node target=find(path,start,false);
                    target.father.children.remove(target);
                    target.father=null;
                    break;
                case "mv":
                    path=list[1].split("/");
                    node src=find(path,start,false);
                    path=list[2].split("/");
                    node tar=find(path,start,false);
                    src.father.children.remove(src);
                    src.father=tar;
                    tar.children.add(src);
                    break;
            }
        }
        for (int i = 0; i < m; i++) {
            String d=sc.nextLine();
            String[] list=d.split(" ");
            String[] path;
            switch (list[0]){
                case "cat":
                    path=list[1].split("/");
                    node file=find(path,start,false);
                    System.out.println(file.content);
                    break;
                case "find":
                    String pathString=null,name=null,type=null;
                    for (int j = 0; j <list.length ; j++) {
                        if (list[j].equals("-name")){
                            name=list[j+1];
                        } else if (list[j].equals("-type")) {
                            type=list[j+1];
                        } else if (j==1) {
                            pathString=list[j];
                        }
                    }
                    node start2;
                    if (pathString==null){
                        tem.append(".");
                        start2=start;
                    }else {
                        path=pathString.split("/");
                        tem.append(path[0]);
                        for (int j = 1; j < path.length; j++) {
                            tem.append("/"+path[j]);
                        }
                        start2=find(path,start,false);
                    }
                    if (name==null){
                        if (start2==null){
                            break;
                        }
                        if (type==null||type.equals("d")){
                            count++;
                            sb.append(tem.toString()+"\n");
                        }
                        for (int j = 0; j < start2.children.size(); j++) {
                            deepFind(type,null,start2.children.get(j));
                        }
                    } else {
                        for (int j = 0; j <start2.children.size() ; j++) {
                            deepFind(type,name,start2.children.get(j));
                        }
                    }
                    System.out.println(count);
                    if (count!=0){
                        System.out.print(sb.toString());
                    }

                    sb=new StringBuilder();
                    tem=new StringBuilder();
                    count=0;
                    break;
            }
        }

    }
    public static void deepFind(String type,String target,node start){
        StringBuilder tem2=new StringBuilder();
        tem2.append(tem.toString());
        tem.append("/"+start.name);
        if ((target==null&&type==null)||
                (type==null&&start.name.equals(target))||
                (start.type.equals(type)&&start.name.equals(target))||
                (start.type.equals(type)&&target==null)){
            sb.append(tem.toString()+"\n");
            count++;
        }
        for (int i = 0; i < start.children.size(); i++) {
            deepFind(type, target, start.children.get(i));
        }
        tem=tem2;
    }
    public static node dfs(String[]path,node start,int top){
        if (top==path.length-1&&start.name.equals(path[top])){
            return start;
        } else if (top==path.length-1) {
            node t=new node(path[top]);
            t.father=start;
            start.children.add(t);
            return t;
        } else if (path[top].equals(".")&&top!=0) {
            return dfs(path,start,top+1);
        } else if (path[top].equals("..")&&top!=0) {
            return dfs(path,start.father,top+1);
        } else if (!start.equals(path[top])) {
            return null;
        }else {
            for (int i = 0; i < start.children.size(); i++) {
                node tem=dfs(path,start.children.get(i),top+1);
                if (tem!=null){
                    return tem;
                }
            }
            node t=new node(path[top]);
            t.father=start;
            start.children.add(t);
            return t;
        }
    }

    public static node find(String[]path,node start,boolean create){
        for (int i = 0; i < path.length; i++) {
            boolean isFind=false;
            if (path[i].equals(".")){
                continue;
            } else if (path[i].equals("..")) {
                start=start.father;
                continue;
            }
            for (int j = 0; j < start.children.size(); j++) {
                node tem= start.children.get(j);
                if (tem.name.equals(path[i])){
                    start=tem;
                    isFind=true;
                    break;
                }
            }
            if (!isFind&&create){
                node t=new node(path[i]);
                t.father=start;
                start.children.add(t);
                return t;
            } else if (!isFind) {
                return null;
            }
        }
        return start;
    }
    public static node find2(String[]path,node start,int top){
        for (int i = top; i < path.length; i++) {
            boolean isFind=false;
            if (path[i].equals(".")){
                continue;
            } else if (path[i].equals("..")) {
                start=start.father;
                continue;
            }
            for (int j = 0; j < start.children.size(); j++) {
                node tem= start.children.get(j);
                if (tem.name.equals(path[i])){
                    find2(path,tem,i+1);
                }
            }
            if (!isFind){
                node t=new node(path[i]);
                t.father=start;
                start.children.add(t);
                return t;
            }
        }
        return start;
    }
}
class node{
    node father;
    ArrayList<node> children=new ArrayList<>();
    String name;
    String content;
    String type;

    public node(String name) {
        this.name = name;
    }
}
class QReader {
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
class QWriter implements Closeable {
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
