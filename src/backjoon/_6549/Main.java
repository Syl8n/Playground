package backjoon._6549;

// https://www.acmicpc.net/problem/6549

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        while(n > 0){
            int[] hist = new int[n];
            for(int i = 0; i < n; i++){
                hist[i] = Integer.parseInt(st.nextToken());
            }
            System.out.println(solution(hist, n));

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
        }
    }

    private static long solution(int[] hist, int n) {
        Stack<Integer> stack = new Stack<>();

        long maxArea = 0;

        for(int i = 0; i < n; i++){
            while(!stack.isEmpty() && hist[i] <= hist[stack.peek()]) {
                long height = hist[stack.pop()];
                long width = stack.isEmpty() ? i : i - 1 - stack.peek();
                maxArea = Math.max(maxArea, width * height);
            }
            stack.push(i);
        }

        while(!stack.isEmpty()) {
            long height = hist[stack.pop()];
            long width = stack.isEmpty() ? n : n - 1 - stack.peek();
            maxArea = Math.max(maxArea, width * height);
        }

        return maxArea;
    }
}
