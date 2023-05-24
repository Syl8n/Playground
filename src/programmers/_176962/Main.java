package programmers._176962;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
class Solution {
    public String[] solution(String[][] plans) {
        PriorityQueue<Plan> pq = new PriorityQueue<>();
        for(String[] plan : plans){
            pq.offer(new Plan(plan));
        }
        Stack<Plan> stack = new Stack<>();
        int idx = 0;
        String[] answer = new String[plans.length];

        int curTime = pq.peek().start;
        while(!pq.isEmpty() || !stack.empty()){
            if(stack.empty()){
                stack.push(pq.poll());
                curTime = stack.peek().start;
            }
            if(!pq.isEmpty() && curTime + stack.peek().playtime > pq.peek().start){
                stack.peek().playtime -= pq.peek().start - curTime;
                stack.push(pq.poll());
                curTime = stack.peek().start;
            } else {
                curTime += stack.peek().playtime;
                answer[idx++] = stack.pop().subject;
            }
        }

        return answer;
    }

    private static class Plan implements Comparable<Plan>{
        String subject;
        int start;
        int playtime;

        public Plan(String[] s){
            String[] time = s[1].split(":");
            this.subject = s[0];
            this.start = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            this.playtime = Integer.parseInt(s[2]);
        }

        @Override
        public int compareTo(Plan p){
            return this.start - p.start;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

//        String[][] plans = {{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}};
//        String[][] plans = {{"aaa", "12:00", "20"}, {"bbb", "12:10", "30"}, {"ccc", "12:40", "10"}};
//        String[][] plans = {{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}};
        String[][] plans = {{"1", "00:00", "30"}, {"2", "00:10", "10"}, {"3", "00:30", "10"}, {"4", "00:50", "10"}};

        System.out.println(Arrays.toString(new Solution().solution(plans)));

        long finishTime = System.nanoTime();
        System.out.println("\nRunning time: " + (finishTime - startTime));
    }
}
