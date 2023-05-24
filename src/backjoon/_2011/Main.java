package backjoon._2011;

import java.io.*;
import java.util.*;

public class Main {
//    private static int count = 0;
//    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String str = br.readLine();
//
//        dfs(0, str);
//        System.out.println(count);
//    }
//    private static void dfs(int idx, String str){
//        if(idx == str.length()){
//            count = (count + 1) % 1000000;
//            return;
//        }
//        int n = str.charAt(idx) - '0';
//        if(n >= 1 && n <= 9){
//            dfs(idx + 1, str);
//        }
//        if(n == 0 || idx + 1 >= str.length()){
//            return;
//        }
//        int next = str.charAt(idx + 1) - '0';
//        n = n * 10 + next;
//        if(n <= 26){
//            dfs(idx + 2, str);
//        }
//    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String encrypted = br.readLine();

        int[] dp = new int[encrypted.length() + 1];
        dp[0] = 1;

        for(int i = 0; i < encrypted.length(); i++){
            int n = encrypted.charAt(i) - '0';
            if(n != 0){
                dp[i + 1] += dp[i];
                dp[i + 1] %= 1000000;
            }

            if(i - 1 < 0){
                continue;
            }

            int prev = encrypted.charAt(i - 1) - '0';

            if(prev == 0){
                continue;
            }

            prev = prev * 10 + n;

            if(prev <= 26){
                dp[i + 1] += dp[i - 1];
                dp[i + 1] %= 1000000;
            }
        }

        System.out.println(dp[dp.length - 1]);
    }
}