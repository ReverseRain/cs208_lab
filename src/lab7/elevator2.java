package lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class elevator2 {
    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
        QReaderC sc=new QReaderC();
        long n=sc.nextLong(),s=sc.nextLong(),m=sc.nextLong();
        long[] array=new long[(int)(2*n)];
        HashMap<Long,Long> upMap=new HashMap<>();
        HashMap<Long,Long> downMap=new HashMap<>();
        long upMax=1,downMax=1;
        for (int i = 0; i < 2*n; i+=2) {
            long l=sc.nextLong(),p=sc.nextLong();
            array[i]=l;array[i+1]=p;
            if (l<p){
                upMap.putIfAbsent(l,(long)0);
                upMap.putIfAbsent(p,(long)0);
            }else {
                downMap.putIfAbsent(l,(long)0);
                downMap.putIfAbsent(p,(long)0);
            }
        }
        List<Map.Entry<Long, Long>> upList = new ArrayList<>(upMap.entrySet());
        List<Map.Entry<Long, Long>> downList = new ArrayList<>(downMap.entrySet());

        upList.sort(new Comparator<Map.Entry<Long, Long>>() {
            @Override
            public int compare(Map.Entry<Long, Long> o1, Map.Entry<Long, Long> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        downList.sort(new Comparator<Map.Entry<Long, Long>>() {
            @Override
            public int compare(Map.Entry<Long, Long> o1, Map.Entry<Long, Long> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        for (int i = 0; i < upList.size(); i++) {
            upList.get(i).setValue((long)i);
        }
        for (int i = 0; i < downList.size(); i++) {
            downList.get(i).setValue((long)i);
        }

        long[]upCnt=new long[upMap.size()];
        long[]downCnt=new long[downMap.size()];
        for (int i = 0; i < 2*n; i+=2) {
            if (array[i]<array[i+1]){
                long l=upMap.get(array[i]),p=upMap.get(array[i+1]);
                upCnt[(int)l]++;
                upCnt[(int)p]--;
            }else {
                long l=downMap.get(array[i]),p=downMap.get(array[i+1]);
                if (p+1<downCnt.length){
                    downCnt[(int)p+1]++;
                }
                if (l+1<downCnt.length){
                    downCnt[(int)l+1]--;
                }
            }
        }

        for (int i = 1; i < upCnt.length; i++) {
            upCnt[i]=upCnt[i]+upCnt[i-1];
        }
        for (int i = 0; i < upCnt.length; i++) {
            if (upMax<upCnt[i]){
                upMax=upCnt[i];
            }
        }
        for (int i = 1; i < downCnt.length; i++) {
            downCnt[i]=downCnt[i]+downCnt[i-1];
        }
        for (int i = 0; i < downCnt.length; i++) {
            if (downMax<downCnt[i]){
                downMax=downCnt[i];
            }
        }
        long times=(long) Math.ceil((double)Math.max(upMax,downMax)/(double)s);
        long t=0;
        long start1= upCnt.length-1,start2= downCnt.length-1;
        long ans=0;


        while (times>0){
            while (start1>=0&&upCnt[(int)start1]<=t){
                start1--;
            }
            while (start2>=0&&downCnt[(int)start2]<=t){
                start2--;
            }
            long upStep=0,downStep=0;
            if (start1>=0&&start1+1<upList.size()){
                upStep=upList.get((int)start1+1).getKey();
            }
            if (start2>=0){
                downStep=downList.get((int)start2).getKey();
            }
            ans+=((Math.max(upStep,downStep)-1)*2);
            t+=s;
            times--;
        }
        System.out.println(ans);
//        System.out.println("cost time "+(System.currentTimeMillis()-start));
    }
}
class QReaderC {
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
