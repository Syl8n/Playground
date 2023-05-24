package programmers._150365;

import java.util.PriorityQueue;
class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String imp = "impossible";
        int dist = Math.abs(x - r) + Math.abs(y - c);
        if(dist % 2 != k % 2){
            return imp;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(x, y, k, new StringBuilder()));
        int[] dx = {1, 0, 0, -1};
        int[] dy = {0, -1, 1, 0};
        char[] dc = {'d', 'l', 'r', 'u'};
        while(!pq.isEmpty()){
            Node node = pq.poll();

            if(node.k == 0 && node.x == r && node.y == c){
                return node.sb.toString();
            }

            for(int i = 0; i < 4; i++){
                int newX = node.x + dx[i];
                int newY = node.y + dy[i];
                if(newX <= 0 || newX > n || newY <= 0 || newY > m){
                    continue;
                }
                if(Math.abs(newX - r) + Math.abs(newY - c) > node.k - 1){
                    continue;
                }
                pq.offer(new Node(newX, newY, node.k - 1, new StringBuilder(node.sb).append(dc[i])));
            }
        }

        return imp;
    }

    private static class Node implements Comparable<Node>{
        int x;
        int y;
        int k;
        StringBuilder sb;

        public Node(int x, int y, int k, StringBuilder sb){
            this.x = x;
            this.y = y;
            this.k = k;
            this.sb = sb;
        }

        public int compareTo(Node n){
            return this.sb.compareTo(n.sb);
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
