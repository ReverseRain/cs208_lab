package lab1;

import java.util.ArrayList;
import java.util.Scanner;

public class lab1_test4 {
    /*
    1.wai
     */
    static char[]list;
    static int len;
    static StringBuilder sb=new StringBuilder();
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        len=s.length();
        list=s.toCharArray();
        String per="";
        System.out.println(per.length());
        charsGroup(0,"",list);
    }
    public static void charsGroup(int i, String per, char[] str) {
        if (i == str.length) {
            if (!per.isEmpty()) {
                System.out.println(per);
            }
        } else {
            charsGroup(i + 1, per, str);
            charsGroup(i + 1, per + str[i], str);
        }
    }
}
