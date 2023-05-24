package programmers._43164;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
class Solution {
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    public String[] solution(String[][] tickets) {
        for(String[] ticket : tickets){
            String key = ticket[0];
            if(!graph.containsKey(key)){
                graph.put(key, new PriorityQueue<>());
            }
            graph.get(key).offer(ticket[1]);
        }

        return dfs("ICN").toArray(new String[0]);
    }

    private Deque<String> dfs(String key){
        if(!graph.containsKey(key) || graph.get(key).isEmpty()){
            return new LinkedList<>(List.of(key));
        }

        Deque<String> right = dfs(graph.get(key).poll());
        if(!graph.get(key).isEmpty()){
            Deque<String> left = dfs(graph.get(key).poll());
            if(left.size() > right.size()){
                for(String s : right){
                    left.addLast(s);
                }
                right = left;
            } else {
                while(!left.isEmpty()){
                    right.addFirst(left.pollLast());
                }
            }
        }
        right.addFirst(key);
        return right;
    }
}

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

//        String[][] t = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
//        String[][] t = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
//        String[][] t = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ZPL"}, {"ZPL", "ICN"}, {"ATL", "FFO"}};
//        String[][] t = {{"ICN", "JFK"}, {"ICN", "AAD"}, {"JFK", "ICN"}};
        String[][] t = {{"ICN", "BOO"}, {"ICN", "COO"}, {"BOO", "DOO"}, {"DOO", "EOO"}, {"COO", "ICN"}, {"EOO", "FOO"}};
//        String[][] t = {{"ICN", "BOO"}, {"BOO", "COO"}, {"BOO", "DOO"}, {"DOO", "BOO"}};

        System.out.println(Arrays.toString(new Solution().solution(t)));

        long finishTime = System.nanoTime();
        System.out.println("\nRunning time: " + (finishTime - startTime));
    }
}
