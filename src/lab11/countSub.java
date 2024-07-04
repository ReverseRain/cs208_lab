package lab11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class countSub {
    public static int cnt=0;
    public static void main(String[] args) {
        QReaderB sc=new QReaderB();
        int n=sc.nextInt();
        int[]num=new int[n+1];
        for (int i = 1; i <=n; i++) {
            num[i]=sc.nextInt();
        }
        int[] Min=new int[n+1];
        int[] Max=new int[n+1];
        merge(1,n,num,Max,Min);
        System.out.println(cnt);
    }
    public static void merge(int l,int r,int[]num,int[]max,int[]min){
        if (l==r){
            cnt++;
            return;
        }
        int mid=(l+r)/2;
        merge(l,mid,num,max,min);
        merge(mid+1,r,num,max,min);
        //get min and max in the [l,r]
        min[mid]=num[mid];min[mid+1]=num[mid+1];
        max[mid]=num[mid];max[mid+1]=num[mid+1];
        for (int i = mid-1; i >= l; i--) {
            if (num[i]<min[i+1]){
                min[i]=num[i];
            }else {
                min[i]=min[i+1];
            }
            if (num[i]>max[i+1]){
                max[i]=num[i];
            }else{
                max[i]=max[i+1];
            }
        }
        for (int i = mid+2; i <=r ; i++) {
            if (num[i]<min[i-1]){
                min[i]=num[i];
            }else {
                min[i]=min[i-1];
            }
            if (num[i]>max[i-1]){
                max[i]=num[i];
            }else {
                max[i]=max[i-1];
            }
        }

        //case 1
        for (int i = l; i <=mid ; i++) {
            if (max[i]<max[mid+1]||min[i]>min[mid+1]){
                break;
            }
            int pos=i+max[i]-min[i];
            if (pos>mid&&pos<=r){
                if (min[pos]>min[i]&&max[i]>max[pos]){
                    cnt++;
                }
            }
        }


        //case 2
        for (int j = r; j >=mid+1 ; j--) {
            if (max[j]<max[mid]||min[j]>min[mid]){
                break;
            }
            int pos=j-max[j]+min[j];
            if (pos<=mid&&pos>=l){
                if (min[j]<min[pos]&&max[j]>max[pos]){
                    cnt++;
                }
            }
        }

        //case 3
        {
            int j1=mid+1,j2=mid+1;
            HashMap<Integer,Integer> count1=new HashMap<>();
            HashMap<Integer,Integer> count2=new HashMap<>();

            int i=mid;
            while (i>=l&&j2<=r){
                while (j1<=r&&min[i]<min[j1]){
                  int R=max[j1]-j1;
                  if (count1.containsKey(R)){
                      count1.put(R,count1.get(R)+1);
                  }else{
                      count1.put(R,1);
                      count2.put(R,0);
                  }
                  j1++;
                }
                if (min[i]<min[j2]){
                    if (max[i]<=max[j2]){
                        int L=min[i]-i;
                        if (count1.containsKey(L)){
                            cnt+=(count1.get(L)-count2.get(L));
                        }
                        i--;
                    }else {
                        int R=max[j2]-j2;
                        count2.put(R,count2.get(R)+1);

                        j2++;
                    }
                }else {
                    i--;
                }
            }

        }
        //case 4
        {
            int j1=mid,j2=mid;
            HashMap<Integer,Integer> count1=new HashMap<>();
            HashMap<Integer,Integer> count2=new HashMap<>();
            int i=mid+1;
            while (i<=r&&j2>=l){
                while (j1>=l&&min[i]<min[j1]){
                  int R=max[j1]+j1;
                  if (count1.containsKey(R)){
                      count1.put(R,count1.get(R)+1);
                  }else{
                      count1.put(R,1);
                      count2.put(R,0);
                  }
                  j1--;
                }
                if (min[i]<min[j2]){
                    if (max[i]<=max[j2]){
                        int L=min[i]+i;

                        if (count1.containsKey(L)){
                            cnt+=(count1.get(L)-count2.get(L));

                        }
                        i++;
                    }else {
                        int R=max[j2]+j2;
                        count2.put(R,count2.get(R)+1);
                        j2--;
                    }
                }else {
                    i++;
                }
            }

        }
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