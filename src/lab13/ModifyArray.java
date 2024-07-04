package lab13;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ModifyArray {
    public static void main(String[] args) {
        QReader2 sc=new QReader2();
//        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] numA=new int[n+1];
        for (int i = 1; i <= n; i++) {
            numA[i]=sc.nextInt();
        }
        int[] numB=new int[n+1];
        ArrayList<Integer>diff=new ArrayList<>();
        for (int i = 1; i <=n ; i++) {
            numB[i]=numA[i]-i;
            if (!diff.contains(numB[i])){
                diff.add(numB[i]);
            }
        }
        diff.sort(null);
        int[]operNum=new int[diff.size()];
        int[]DiffSum=new int[diff.size()];
        for (int j = 0; j <diff.size() ; j++) {
            if (numB[1]!=diff.get(j)){
                operNum[j]++;
                DiffSum[j]=Math.abs((diff.get(j)-numB[1]));
            }
        }
        for (int i = 2; i <=n ; i++) {
            long minNum= Long.MAX_VALUE;
            int diffSum=0;
            for (int j = 0; j < diff.size(); j++) {
                if ((minNum>operNum[j])||(minNum==operNum[j]&&DiffSum[j]<diffSum)){
                    minNum=operNum[j];
                    diffSum=DiffSum[j];
                }
                operNum[j]=(int)minNum;
                DiffSum[j]=diffSum;
                if (diff.get(j)!=numB[i]){
                    operNum[j]++;
                    DiffSum[j]+=Math.abs((numB[i]-diff.get(j)));
                }
            }
        }
        int minOper=operNum[0]; int minDiff=DiffSum[0];
        for (int i = 1; i <diff.size() ; i++) {
            if (minOper>operNum[i]||(minOper==operNum[i]&&DiffSum[i]<minDiff)){
                minOper=operNum[i];
                minDiff=DiffSum[i];
            }
        }
        System.out.println(minOper);
        System.out.println(minDiff);
    }
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