package backjoon._7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/7576

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] tomatoes = new int[N][M];
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                tomatoes[i][j] = Integer.parseInt(st.nextToken());
                if(tomatoes[i][j] == 1){
                    queue.offer(new int[]{i, j, 0});
                }
            }
        }

        int[] dx = {1, -1, 0 , 0};
        int[] dy = {0, 0, 1, -1};
        int maxDay = 0;

        while(!queue.isEmpty()){
            int[] pos = queue.poll();
            int x = pos[0];
            int y = pos[1];
            int day = pos[2];

            for (int i = 0; i < dx.length; i++){
                int newX = x + dx[i];
                int newY = y + dy[i];
                if(newX < 0 || newX >= N || newY < 0 || newY >= M){
                    // 인덱스 범위 초과
                    continue;
                }
                if(tomatoes[newX][newY] != 0){
                    // 벽 or 이미 익음
                    continue;
                }
                queue.offer(new int[]{newX, newY, day + 1});
                maxDay = Math.max(maxDay, day + 1);
                tomatoes[newX][newY] = 1;
            }
        }

        for(int[] row : tomatoes){
            for(int tomato: row){
                if(tomato == 0){
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(maxDay);
    }
}
