package lab7;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class buyGame {
    public static long day=0;
    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
        QReaderA sc=new QReaderA();
        long n=sc.nextLong(),l=sc.nextLong(),m=sc.nextLong(),k=sc.nextLong();
        long[] array=new long[(int)m];
        long total=0;
        for (int i = 0; i < m; i++) {
            array[i]=sc.nextLong();
            total+=array[i];
        }
        Arrays.sort(array);
        int start=0;
        while (true){
            if (k==0){
                day=(long)Math.ceil((double) (total-n)/(double)l);
//                day=(long)((total-n)/(double)l+0.5);
                break;
            }
            day++;
            if ((n+day*l)>=total){
                break;
            }
            while (start<m&&array[start]<=k*(day)){
                start++;
            }
            if (start==m){
                long tem=(long)Math.ceil((double) (total-(n+day*l))/(double)l);
//                long tem=(long)((total-(n+day*l))/(double)l+0.5);
                day+=tem;
                break;
            }
            total-=(k*(m-start));
        }
        System.out.print(day+" ");
        if (n+l*(day-1)<total){
            System.out.println("evening");
        }else {
            System.out.println("morning");
        }
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

