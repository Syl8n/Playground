package backjoon._14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int dx[] = {0, 0, 1, -1};
    static final int dy[] = {1, -1, 0, 0};
    static int originalMap[][];
    static int n, m;
    static int maxSafeZone = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        originalMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                originalMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

        System.out.println(maxSafeZone);
    }

    static void dfs(int depth) {
        if (depth == 3) {
            bfs();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (originalMap[i][j] == 0) {
                    originalMap[i][j] = 1;
                    dfs(depth + 1);
                    originalMap[i][j] = 0;
                }
            }
        }
    }

    static void bfs() {
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (originalMap[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        int copyMap[][] = new int[n][m];

        for (int i = 0; i < n; i++) {
            copyMap[i] = originalMap[i].clone();
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int k = 0; k < dx.length; k++) {
                int newX = x + dx[k];
                int newY = y + dy[k];

                if (0 <= newX && newX < n && 0 <= newY && newY < m) {
                    if (copyMap[newX][newY] == 0) {
                        queue.add(new int[]{newX, newY});
                        copyMap[newX][newY] = 2;
                    }
                }
            }
        }
        countSafeZone(copyMap);
    }

    private static void countSafeZone(int[][] copyMap) {
        int safeZone = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyMap[i][j] == 0) {
                    safeZone++;
                }
            }
        }
        if (maxSafeZone < safeZone) {
            maxSafeZone = safeZone;
        }
    }
}
