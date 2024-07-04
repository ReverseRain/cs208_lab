package lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class elevator {
    public static void main(String[] args) {
//        Scanner sc=new Scanner(System.in);
        QReaderB sc=new QReaderB();
        long n=sc.nextLong(),s=sc.nextLong(),m=sc.nextLong();
        HashMap<Long,Long> upMap=new HashMap<>();
        HashMap<Long,Long> downMap=new HashMap<>();
        long upMax=1,downMax=1;
        for (int i = 0; i < n; i++) {
            long l=sc.nextLong(),p=sc.nextLong();
            if (l<p){
                for (long j = l; j <p ; j++) {
                    if (upMap.containsKey(j)){
                        upMap.put(j,upMap.get(j)+1);
                        if (upMax<upMap.get(j)){
                            upMax=upMap.get(j);
                        }
                    }else {
                        upMap.put(j,(long)1);
                    }

                }
            }else {

                for (long j = l; j > p ; j--) {
                    if (downMap.containsKey(j)){
                        downMap.put(j,downMap.get(j)+1);
                        if (downMax<downMap.get(j)){
                            downMax=downMap.get(j);
                        }
                    }else {
                        downMap.put(j,(long)1);
                    }
                }
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

        long times=(long) Math.ceil((double)Math.max(upMax,downMax)/(double)s);
        long t=0;
        long start1=upList.size()-1,start2=downList.size()-1;
        long ans=0;


        while (times>0){
            while (start1>=0&&upList.get((int)start1).getValue()<=t){
                start1--;
            }
            while (start2>=0&&downList.get((int)start2).getValue()<=t){
                start2--;
            }
            long upStep=0,downStep=0;
            if (start1>=0){
                upStep=upList.get((int)(start1)).getKey();
            }
            if (start2>=0){
                downStep=downList.get((int)start2).getKey();
            }
            ans+=((Math.max(upStep+1,downStep)-1)*2);
            t+=s;
            times--;
        }
        System.out.println(ans);
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
