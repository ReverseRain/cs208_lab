package lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class pA {
    public static void main(String[] args) {
        QReader2 in=new QReader2();
        int n=in.nextInt();
        int k=in.nextInt();
        node[]nodes=new node[n];
        nodes[0]=new node();
        nodes[0].val=in.nextLong();
        for (int i = 1; i < n; i++) {
            nodes[i]=new node();
            nodes[i].val=in.nextLong();
            nodes[i].left=nodes[i-1];
            nodes[i-1].right=nodes[i];
        }
        PriorityQueue<node> queue=new PriorityQueue<>(new Comparator<node>() {
            @Override
            public int compare(node o1, node o2) {
                return Long.compare(o2.val,o1.val);
            }
        });

        for (int i = 0; i < n; i++) {
            queue.add(nodes[i]);
        }
        long res=0;
        while(k>0){
            node tem= queue.poll();
            if (tem.isdead){
                continue;
            }
            if ((tem!=null&&tem.val<0)){
                break;
            }
            res+=tem.val;
            long val=0;
            node reg=new node();
            if (tem.left!=null){
                tem.left.isdead=true;
                val+=tem.left.val;
                if (tem.left.left!=null){
                    tem.left.left.right=reg;
                    reg.left=tem.left.left;
                }

            }
            if (tem.right!=null){
                tem.right.isdead=true;
                val+=tem.right.val;
                if (tem.right.right!=null){
                    tem.right.right.left=reg;
                    reg.right=tem.right.right;
                }

            }

            reg.val=val- tem.val;
            if (reg.val>0){
                queue.add(reg);
            }

            k--;

        }
        System.out.println(res);





    }
}

class node{
    long val=0;
    node left=null;
    node right=null;
    boolean isdead=false;
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