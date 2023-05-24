package backjoon._5430;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

// https://www.acmicpc.net/problem/5430

public class Main {
    private static String solution(String comm, int n, String[] nums){
        Deque<String> dq = new ArrayDeque<>();
        if(nums != null) {
            for (String s : nums) {
                dq.offer(s);
            }
        }
        boolean r = false;
        for(int i = 0; i < comm.length(); i++){
            if(dq.isEmpty() && comm.charAt(i) == 'D'){
                return "error";
            }
            if(comm.charAt(i) == 'R'){
                r = !r;
                continue;
            }
            if(r){
                dq.pollLast();
            } else {
                dq.pollFirst();
            }
        }
        StringBuilder sb = new StringBuilder("[");
        while (!dq.isEmpty()){
            if(r){
                sb.append(dq.pollLast());
            } else {
                sb.append(dq.poll());
            }
            if(!dq.isEmpty()){
                sb.append(",");
            }
        }
        sb.append("]");

        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            String comm = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String tmp = br.readLine();
            String input = tmp.substring(1, tmp.length() - 1);
            String[] nums = null;
            if(input != null && input.length() > 0) {
                nums = input.split(",");
            }
            System.out.println(solution(comm, n, nums));
        }
    }
}