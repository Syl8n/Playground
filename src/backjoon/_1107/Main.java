package backjoon._1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1107

public class Main {
    static int minSteps;
    static Set<Integer> set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        set = new HashSet<>();
        if(n > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                set.add(Integer.parseInt(st.nextToken()));
            }
        }
        minSteps = Math.abs(target - 100);
        findNearestChannel(0, target, (int) (Math.log10(target) + 1), 0);
        System.out.println(minSteps);
    }

    private static void findNearestChannel(int ch, int target, int n, int depth) {
        if(depth > 0 && n <= depth + 1){
            refreshMinSteps(ch, target, depth);
        }
        if(depth == n + 1 || depth == 6){
            return;
        }
        for(int i = 0; i < 10; i++){
            if(set.contains(i)){
                continue;
            }
            findNearestChannel(10 * ch + i, target, n, depth + 1);
        }
    }

    private static void refreshMinSteps(int ch, int target, int depth) {
        int step = depth + Math.abs(ch - target);
        if(step < minSteps){
            minSteps = step;
        }
    }
}
