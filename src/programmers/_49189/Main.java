package programmers._49189;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
class Solution {
    public int solution(int n, int[][] edge) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < n + 1; i++){
            graph.add(new ArrayList<>());
        }
        for(int[] e : edge){
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        int[] visited = new int[n+1];
        visited[1] = 1;
        int max = 1;
        int count = 0;

        Queue<Integer> q = new LinkedList<>();
        q.offer(1);

        while(!q.isEmpty()){
            int curr = q.poll();
            Queue<Integer> newQ = new LinkedList<>();

            for(int i : graph.get(curr)){
                if(visited[i] > 0){
                    continue;
                }
                visited[i] = visited[curr] + 1;
                newQ.offer(i);
            }
            if(newQ.isEmpty()){
                if(visited[curr] > max){
                    max = visited[curr];
                    count = 1;
                }
                else if(visited[curr] == max){
                    count++;
                }
            }else{
                q.addAll(newQ);
            }
        }

        return count;
    }
}

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println(new Solution().solution(n, edge));

        long finishTime = System.nanoTime();
        System.out.println("\nRunning time: " + (finishTime - startTime));
    }
}
