package lab1;

import java.util.ArrayList;
import java.util.Scanner;

public class lab1_test3 {
    static StringBuilder sb=new StringBuilder();
    static ArrayList<String> ans=new ArrayList<>();
    static char[]list;
    static int len;
    static boolean[]trueList;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        list=s.toCharArray();
        len= list.length;
        trueList=new boolean[list.length];
        for (int i = 0; i < list.length; i++) {
            trueList[i]=false;
        }
        dfs();
        for (int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i));
        }
    }
    public static void dfs(){
        if (sb.length()==len){
            ans.add(sb.toString());
            return;
        }
        for (int i = 0; i < list.length; i++) {
            if (trueList[i]==false){
                trueList[i]=true;
                sb.append(list[i]);
                dfs();
                trueList[i]=false;
                sb.replace(sb.length()-1,sb.length(),"");
            }
        }
    }
}
