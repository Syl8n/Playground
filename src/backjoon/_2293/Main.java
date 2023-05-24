package backjoon._2293;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] dp = new int[k + 1];
        dp[0] = 1;
        for(int i = 0; i < n; i++){
            int v = sc.nextInt();
            for(int j = 1; j <= k; j++){
                if(j >= v){
                    dp[j] += dp[j - v];
                }
            }
        }
        System.out.println(dp[k]);
    }
}
