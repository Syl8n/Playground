package programmers._1844;

import java.util.LinkedList;
import java.util.Queue;
class Solution {
    public int solution(int[][] maps) {
        int n = maps.length - 1;
        int m = maps[0].length - 1;
        boolean[][] visited = new boolean[n+1][m+1];
        visited[0][0] = true;
        Queue<Location> q = new LinkedList<>();
        q.offer(new Location(0, 0, 1));

        int[] dx = {1, 0, 0, -1};
        int[] dy = {0, 1, -1, 0};

        while(!q.isEmpty()){
            Location loc = q.poll();

            if(loc.r == n && loc.c == m){
                return loc.step;
            }

            for(int i = 0; i < dx.length; i++){
                int newR = loc.r + dx[i];
                int newC = loc.c + dy[i];

                if(newR < 0 || newR > n || newC < 0 || newC > m){
                    continue;
                }

                if(visited[newR][newC] || maps[newR][newC] == 0){
                    continue;
                }
                visited[newR][newC] = true;

                q.offer(new Location(newR, newC, loc.step + 1));
            }
        }

        return -1;
    }
    private static class Location{
        int r;
        int c;
        int step;

        public Location(int r, int c, int step){
            this.r = r;
            this.c = c;
            this.step = step;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        

        long finishTime = System.nanoTime();
        System.out.println("\nRunning time: " + (finishTime - startTime));
    }
}