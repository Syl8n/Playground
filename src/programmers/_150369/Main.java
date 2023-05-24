package programmers._150369;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0L;
        int delBox = 0;
        int pickBox = 0;
        for(int i = n - 1; i >= 0; i--){
            if(deliveries[i] > 0 || pickups[i] > 0){
                delBox -= deliveries[i];
                pickBox -= pickups[i];
                if(delBox < 0 || pickBox < 0) {
                    int count = Math.max((-delBox - 1) / cap + 1, (-pickBox - 1) / cap + 1);
                    answer += count * (i + 1) * 2L;
                    delBox += count * cap;
                    pickBox += count * cap;
                }
            }
        }
        return answer;
    }
}

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        int cap = 4;
        int n = 3;
        int[] del = {1, 3, 10};
        int[] pick = {1, 8, 0};
        System.out.println(new Solution().solution(cap, n, del, pick));

        long finishTime = System.nanoTime();
        System.out.println("\nRunning time: " + (finishTime - startTime));
    }
}