package lab6;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class proA {
    public static void main(String[] args) {
        QReaderA sc=new QReaderA();
        QWriterA out=new QWriterA();
        long n=sc.nextLong(),m=sc.nextLong();
        long[]num=new long[(int)n+1];
        for (int i = 1; i <=n ; i++) {
            num[i]=sc.nextLong();
        }
        long[]f1=new long[(int)m+1],f2=new long[(int)m+1];
        out.println(find2(f1,f2,n,m,num));
        out.close();
    }
    public static long find2(long[]f1,long[]f2,long n,long m,long[]num){
        f1[0]=0;f2[0]=0;
        for (int i = 1; i <=m ; i++) {
            f1[i]=num[1];
            f2[i]=0;
        }
        long tem1=0,tem2=0;
        for (int i = 2; i <=n ; i++) {
            tem1=0;
            for (int j = 1; j <=m ; j++) {
                tem2=f1[j];
                f1[j]=Math.max(f1[j],f2[j-1]+num[i]);
                f2[j-1]=tem1;
                tem1=tem2;
            }
        }
        return f1[(int)m];
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
