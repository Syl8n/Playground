package programmers._181187;

class Solution {
    public long solution(int r1, int r2) {
        long answer = (r2 - r1 + 1) * 4;

        int[] dp = new int[r2 + 1];
        for(int i = r1 - 1; i > 0; i--){
            int j = dp[i+1];
            while(dist(i, j) <= r1){
                j++;
            }
            dp[i] = j - 2;
        }

        for(int i = r2 - 1; i > 0; i--){
            int j = dp[i];
            while(dist(i, j) <= r2){
                j++;
            }
            dp[i] = j - 2 - dp[i];
        }

        long tmp = 0L;
        for(int i : dp){
            tmp += i;
        }

        return answer + tmp * 4;
    }

    private int dist(int i, int j){
        return (int) Math.sqrt(i * i + j * j);
    }
}

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        int r1 = 2;
        int r2 = 3;
        System.out.println(new Solution().solution(r1, r2));

        long finishTime = System.nanoTime();
        System.out.println("\nRunning time: " + (finishTime - startTime));
    }
}
