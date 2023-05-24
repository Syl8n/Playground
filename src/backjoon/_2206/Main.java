package backjoon._2206;

// https://www.acmicpc.net/problem/2206

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] st = br.readLine().split(" ");
        int N = Integer.parseInt(st[0]);
        int M = Integer.parseInt(st[1]);
        int[][] map = new int[N][M];
        int[][][] visited = new int[N][M][2];
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for(int i = 0; i < N; i++){
            st = br.readLine().split("");
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st[j]);
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1});
        while (!queue.isEmpty()){
            int[] pos = queue.poll();
            int x = pos[0];
            int y = pos[1];
            int z = pos[2];

            if(x == N - 1 && y == M - 1){
                System.out.println(visited[x][y][z] + 1);
                return;
            }

            for(int i = 0; i < dx.length; i++){
                int newX = x + dx[i];
                int newY = y + dy[i];
                if(newX == 0 && newY == 0){
                    continue;
                }
                if(newX < 0 || newY < 0 || newX >= N || newY >= M){
                    continue;
                }
                if(visited[newX][newY][z] > 0){
                    continue;
                }
                if(map[newX][newY] == 1 && z == 1){
                    queue.offer(new int[]{newX, newY, z - 1});
                    visited[newX][newY][z - 1] = visited[x][y][z] + 1;
                }
                if(map[newX][newY] == 0){
                    queue.offer(new int[]{newX, newY, z});
                    visited[newX][newY][z] = visited[x][y][z] + 1;
                }
            }
        }

        System.out.println(-1);
    }
}
