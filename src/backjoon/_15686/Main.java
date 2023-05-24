package backjoon._15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int map[][];
    static int n, m;
    static final int dx[] = {0, 0, 1, -1};
    static final int dy[] = {1, -1, 0, 0};
    static List<int[]> houses = new ArrayList<>();
    static List<int[]> chickens = new ArrayList<>();
    static List<int[]> chosen = new ArrayList<>();
    static int minDistSum = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    chickens.add(new int[]{i, j});
                } else if (map[i][j] == 1){
                    houses.add(new int[]{i, j});
                }
            }
        }
        for (int i = 1; i <= m; i++) {
            dfs(i, 0, 0);
        }
        System.out.println(minDistSum);
    }

    private static void dfs(int numChosen, int start, int depth) {
        if (depth == numChosen) {
            minDistSum = Math.min(minDistSum, calcMinDistSum(0));
            return;
        }
        for (int i = start; i < chickens.size(); i++) {
            int[] chicken = chickens.get(i);
            chosen.add(chicken);
            dfs(numChosen, i + 1, depth + 1);
            chosen.remove(chicken);
        }
    }

    private static int calcMinDistSum(int distSum) {
        for(int[] house : houses){
            int minDist = Integer.MAX_VALUE;
            for(int[] chicken : chosen){
                int dist = getMahattenDist(house, chicken);
                if(minDist > dist){
                    minDist = dist;
                }
            }
            distSum += minDist;
        }
        return distSum;
    }

    private static int getMahattenDist(int[] house, int[] chicken) {
        return Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]);
    }
}
