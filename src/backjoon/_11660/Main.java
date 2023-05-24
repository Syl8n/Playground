package backjoon._11660;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= n; j++){
                map[i][j] = map[i - 1][j] + map[i][j - 1] - map[i - 1][j - 1] + Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int[] from = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            int[] to = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

            int ans = map[to[0]][to[1]] - map[to[0]][from[1] - 1] - map[from[0] - 1][to[1]] + map[from[0] - 1][from[1] - 1];
            System.out.println(ans);
        }
    }
}