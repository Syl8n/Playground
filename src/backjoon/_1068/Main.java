package backjoon._1068;

import java.io.*;
import java.util.*;

public class Main {
    static int n, r, root;
    static List<List<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }
        String[] s = br.readLine().split(" ");
        r = Integer.parseInt(br.readLine());

        for(int i = 0; i < s.length; i++){
            if(s[i].equals("-1")){
                root = i;
            }
            else if(i != r){
                int parent = Integer.parseInt(s[i]);
                graph.get(parent).add(i);
            }
        }

        System.out.println(countLeaf(root));
    }

    private static int countLeaf(int idx) {
        if(idx == r){
            return 0;
        }
        if(graph.get(idx).isEmpty()){
            return 1;
        }

        int count = 0;
        for(int i : graph.get(idx)){
            count += countLeaf(i);
        }

        return count;
    }
}