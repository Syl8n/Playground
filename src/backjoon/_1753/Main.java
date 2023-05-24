package backjoon._1753;

// https://www.acmicpc.net/problem/1753

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int INF = 1000000000;
    static class Node implements Comparable<Node>{
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight = o.weight;
        }
    }
    public static void dijkstra(int N, List<List<Node>> graph,int[] dist, int start){
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            int i = pq.poll().to;

            if (visited[i]){
                continue;
            }
            visited[i] = true;

            for(Node node : graph.get(i)){
                if(dist[node.to] > dist[i] + node.weight){
                    dist[node.to] = dist[i] + node.weight;
                    pq.add(new Node(node.to, dist[node.to]));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        List<List<Node>> graph = new ArrayList<>();
        for(int i = 0; i <= V; i++){
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            graph.get(Integer.parseInt(st.nextToken())).add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int[] dist = new int[V + 1];
        Arrays.fill(dist, INF);

        dijkstra(V, graph, dist, start);

        for(int i = 1; i < dist.length; i++){
            System.out.println(dist[i] != INF? dist[i] : "INF");
        }
    }
}
