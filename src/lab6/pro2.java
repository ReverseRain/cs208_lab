package lab6;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class pro2 {
    public static void main(String[] args) {
        QReaderB sc=new QReaderB();
        QWriterB out=new QWriterB();
//        Scanner sc=new Scanner(System.in);
        long t=sc.nextLong();
        while (t>0){
            long n=sc.nextLong();
            long[]num=new long[(int)n];
            for (int i = 0; i < n; i++) {
                num[i]=sc.nextLong();
            }
            long[]diff=new long[(int)n];
            diff[0]=num[0];
            for (int i = 1; i <n ; i++) {
                diff[i]=num[i]-num[i-1];
            }
            long p=0,q=0;
            for (int i = 1; i < n; i++) {
                if (diff[i]>0){
                    p+=diff[i];
                }else {
                    q-=diff[i];
                }
            }
//            System.\
            out.println(Math.max(p,q));
            t--;
        }
        out.close();
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

