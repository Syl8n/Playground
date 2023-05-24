package programmers._84512;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String word) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('A', 1);
        map.put('E', 2);
        map.put('I', 3);
        map.put('O', 4);
        map.put('U', 5);

        int[] dp = new int[6];
        dp[0] = 1;
        for(int i = 0; i < word.length(); i++){
            dp[i + 1] = (dp[i] - 1) * 5 + map.get(word.charAt(i));
        }
        dp[word.length()] -= 1;
        for(int i = word.length(); i < 5; i++){
            dp[i + 1] = dp[i] * 5;
        }

        return Arrays.stream(dp).sum();
    }
}

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        String word = "EIO";
        System.out.println(new Solution().solution(word));

        long finishTime = System.nanoTime();
        System.out.println("\nRunning time: " + (finishTime - startTime));
    }
}