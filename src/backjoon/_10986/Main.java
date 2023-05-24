package backjoon._10986;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] cumsum = new int[n + 1];
        int[] count = new int[m];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= n; i++){
            cumsum[i] = (cumsum[i - 1] + Integer.parseInt(st.nextToken())) % m;
            count[cumsum[i]]++;
        }

        long ans = count[0];
        for (int c : count) {
            if (c >= 2) {
                ans += (long) c * (c - 1) / 2;
            }
        }
        System.out.println(ans);
    }
}