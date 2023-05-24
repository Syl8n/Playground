package backjoon._9252;

import java.util.Scanner;

public class Main {
    public static String traceBack(int[][] dp, int len, char[] s1, char[] s2){
        int i = dp.length - 1;
        int j = dp[0].length - 1;
        StringBuilder sb = new StringBuilder();
        while(len > 0){
            while(s1[i - 1] != s2[j - 1]){
                if(dp[i - 1][j] > dp[i][j - 1]){
                    i--;
                } else{
                    j--;
                }
            }
            sb.append(s1[i - 1]);
            i--;
            j--;
            len--;
        }
        return sb.reverse().toString();
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        char[] s1 = sc.nextLine().toCharArray();
        char[] s2 = sc.nextLine().toCharArray();
        int[][] dp = new int[s1.length + 1][s2.length + 1];
        for(int i = 1; i <= s1.length; i++){
            for(int j = 1; j <= s2.length; j++){
                if(s1[i-1] == s2[j-1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        int len = dp[s1.length][s2.length];
        System.out.println(len);
        if(len > 0){
            System.out.println(traceBack(dp, len, s1, s2));
        }
    }
}
