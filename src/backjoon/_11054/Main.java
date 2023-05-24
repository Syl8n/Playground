package backjoon._11054;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static void LIS(int[] arr, int[] dp, int n){
        int a = 1;
        for(int i = n - 1; i >= 0; i--){
            if(arr[i] < arr[n] && a <= dp[i]){
                a = dp[i] + 1;
            }
        }
        dp[n] = a;
    }
    static void LDS(int[] arr, int[] dp, int n){
        int a = 1;
        for(int i = n + 1; i < arr.length; i++){
            if(arr[i] < arr[n] && a <= dp[i]){
                a = dp[i] + 1;
            }
        }
        dp[n] = a;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(s[i]);
        }

        int[] i_dp = new int[n];
        i_dp[0] = 1;
        for(int i = 1; i < n; i++){
            LIS(arr, i_dp, i);
        }
        int[] d_dp = new int[n];
        d_dp[n - 1] = 1;
        for(int i = n - 1; i >= 0; i--){
            LDS(arr, d_dp, i);
        }
        int maxLen = 0;
        for(int i = 0; i < n; i++){
            maxLen = Math.max(maxLen, i_dp[i] + d_dp[i]);
        }
        System.out.println(maxLen - 1);
    }
}
