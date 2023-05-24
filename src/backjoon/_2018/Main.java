package backjoon._2018;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] cumsum = new int[n + 1];
        for(int i = 1; i <= n; i++){
            cumsum[i] = cumsum[i - 1] + i;
        }

        int count = 1, left = 1, right = 1;
        if(n > 1) {
            while (right != n) {
                if (cumsum[right] - cumsum[left - 1] == n) {
                    left++;
                    right++;
                    count++;
                    continue;
                }
                if (cumsum[right] - cumsum[left - 1] < n) {
                    right++;
                    continue;
                }
                left++;
            }
        }

        System.out.println(count);
    }
}