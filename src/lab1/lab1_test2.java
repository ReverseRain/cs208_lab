package lab1;

import java.util.Scanner;

public class lab1_test2 {
    static int[][] map;
    static int ans = 0;
    static int ans2 = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        map = new int[2][n];
        search(n);
        System.out.println(ans2);
        System.out.println(rolling(n));
        System.out.println(dfs() / mul(n));

    }
    public static int rolling(int n){
        int[] dp=new int[3];
        dp[0]=1;dp[1]=2;
        if (n==1){
            return 1;
        } else if (n==2) {
            return 2;
        }
        for (int i = 0; i <n-2 ; i++) {
            dp[2]=dp[0]+dp[1];
            dp[0]=dp[1];
            dp[1]=dp[2];
        }
        return dp[2];
    }

    public static void search(int n) {
        if (n == 1) {
            ans2++;
            return;
        } else if (n == 2) {
            ans2 += 2;
            return;
        }
        search(n - 1);
        search(n - 2);
    }

    public static int mul(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * mul(n - 1);
    }

    public static int dfs() {
        if (check()) {
            ans++;
            return ans;
        }
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 0) {
                    if (i + 1 < map.length && map[i + 1][j] == 0) {
                        map[i][j] = 1;
                        map[i + 1][j] = 1;
                        dfs();
                        map[i][j] = 0;
                        map[i + 1][j] = 0;
                    }
                    if (j + 1 < map[0].length && map[i][j + 1] == 0) {
                        map[i][j] = 1;
                        map[i][j + 1] = 1;
                        dfs();
                        map[i][j] = 0;
                        map[i][j + 1] = 0;
                    }
                }
            }
        }
        return ans;
    }

    public static boolean check() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
