package lab7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class pH {
    public static void main(String[] args) {
        long startkkk=System.currentTimeMillis();
        QReader2 in=new QReader2();
        int n=in.nextInt();
        int m=in.nextInt();
        int k=in.nextInt();
        long[] arr=new long[n*2];
        long[] temarr=new long[n*2];
        for (int i = 0; i < 2*n; i+=2) {
            arr[i]=in.nextLong();
            arr[i+1]=in.nextLong();
            if (arr[i]<arr[i+1])arr[i+1]--;
        }
        for (int i = 0; i < 2*n; i++) {
            temarr[i]=arr[i];
        }
        Arrays.sort(temarr);

        ArrayList<Long> list=new ArrayList<>();
        for (int i = 0; i < temarr.length; i++) {
            if (list.size()!=0&&temarr[i]==list.get(list.size()-1)){
                continue;
            }else{
                list.add(temarr[i]);
            }
        }
        HashMap<Long,Integer> map=new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i),i);
        }


        long[] upPeopleCnt=new long[list.size()];
        long[] downPeopleCnt=new long[list.size()];
        for (int i = 0; i < 2*n; i+=2) {
            if (arr[i]<arr[i+1]){
                int start=map.get(arr[i]);
                int end=map.get(arr[i+1]);
                for (int j = start; j <= end; j++) {
                    upPeopleCnt[j]++;
                }

            }else{
                int start=map.get(arr[i]);
                int end=map.get(arr[i+1]);
                for (int j = end+1; j <= start; j++) {
                    downPeopleCnt[j]++;
                }
            }
        }
        long upmax=0;
        for (int i = 0; i < upPeopleCnt.length; i++) {
            if (upPeopleCnt[i]>upmax)upmax=upPeopleCnt[i];
        }
        long downmax=0;
        for (int i = 0; i < downPeopleCnt.length; i++) {
            if (downPeopleCnt[i]>downmax)downmax=downPeopleCnt[i];
        }
        int upcnt=(int)Math.ceil((double) upmax/m);
        int downcnt=(int)Math.ceil((double) downmax/m);
        int uppoint=upPeopleCnt.length-1;
        int downpoint=uppoint;
        long temup=0;
        long temdown=0;
        long res=0;
        if (upcnt<downcnt){
            long uplevel=0;
            long downlevel=0;
            for (int i = 0; i < downcnt; i++) {
                if (i<upcnt){
                    while (uppoint>=0){
                        if (upPeopleCnt[uppoint]>temup){

                            uplevel=list.get(uppoint);
                            temup+=m;
                            break;
                        }else {
                            uppoint--;
                        }
                    }

                }else {
                    uplevel=0;
                }
                while (downpoint>=0){
                    if (downPeopleCnt[downpoint]>temdown){

                        downlevel=list.get(downpoint)-1;
                        temdown+=m;
                        break;
                    }else {
                        downpoint--;
                    }
                }

                long level=Math.max(uplevel,downlevel);
                res+=level*2;


            }
        }else {

            long uplevel=0;
            long downlevel=0;
            for (int i = 0; i < upcnt; i++) {
                if (i<downcnt){
                    while (downpoint>=0){
                        if (downPeopleCnt[downpoint]>temdown){
                            downlevel=list.get(downpoint)-1;
                            temdown+=m;
                            break;
                        }else {
                            downpoint--;
                        }
                    }
                }else {
                    downlevel=0;
                }
                while (uppoint>=0){

                    if (upPeopleCnt[uppoint]>temup){
                        uplevel=list.get(uppoint);
                        temup+=m;
                        break;
                    }else {
                        uppoint--;
                    }
                }
//                System.out.println("up"+uplevel);
//                System.out.println("down"+downlevel);

                long level=Math.max(uplevel,downlevel);
                res+=level*2;


            }

        }
        System.out.println(res);
        System.out.println("cost time "+(System.currentTimeMillis()-startkkk));

//2 1 100
//41 99
//97 33



    }



}
class node{
    int key=0;
    long val=0;
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