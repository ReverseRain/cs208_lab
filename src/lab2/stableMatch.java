package lab2;

import java.util.HashMap;
import java.util.Scanner;

public class stableMatch {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=Integer.parseInt(sc.nextLine());
        String[] boys=new String[t];
        String[] girls=new String[t];
        for (int i = 0; i < t; i++) {
            boys[i]=sc.next();
        }
        for (int i = 0; i < t; i++) {
            girls[i]=sc.next();
        }
        HashMap<String,Integer> boyMap=new HashMap<>();
        for (int i = 0; i < boys.length; i++) {
            boyMap.put(boys[i],i);
        }
        HashMap<String,Integer>girlMap=new HashMap<>();
        for (int i = 0; i < girls.length; i++) {
            girlMap.put(girls[i],i);
        }
        int[][] boyList=new int[t][t];
        String[] isPair=new String[t];
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < t; j++) {
                boyList[i][j]=girlMap.get(sc.next());
            }
        }
        int[][]girlListIn=new int[t][t];
        for (int i = 0; i <t ; i++) {
            for (int j = 0; j <t; j++) {
                girlListIn[i][boyMap.get(sc.next())]=j;
            }
        }
        int[]points=new int[t];
        int start=t;
        while (start>0){
            String man=boys[--start];
            int manNum=boyMap.get(man);
            int womanNum=boyList[manNum][points[manNum]];
            if (isPair[womanNum]==null){
                isPair[womanNum]=man;
            } else if (girlListIn[womanNum][manNum]<girlListIn[womanNum][boyMap.get(isPair[womanNum])]) {
                boys[start++]=isPair[womanNum];
                points[boyMap.get(isPair[womanNum])]++;
                isPair[womanNum]=man;
            }else {
                start++;
                points[manNum]++;
            }
        }
        for (int i = 0; i < t; i++) {
            System.out.println(isPair[i]+" "+girls[i]);
        }
    }
}
