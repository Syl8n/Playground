package programmers._92342;

import java.util.Arrays;
class Solution {
    int[] tmp = new int[11], apeach, result;
    int max = Integer.MIN_VALUE;
    public int[] solution(int n, int[] info) {
        apeach = info;
        dfs(0, n, 0, 0);

        if(result != null){
            return result;
        }
        return new int[]{-1};
    }
    private void dfs(int depth, int remain, int lionScore, int apeachScore){
        if(depth == 11){
            int diff = lionScore - apeachScore;
            System.out.println(Arrays.toString(result));
            System.out.println(Arrays.toString(tmp) + " diff: " + diff);
            System.out.println();
            if(lionScore <= apeachScore || diff < max){
                return;
            }
            tmp[10] += remain;
            result = compare(diff);
            tmp[10] -= remain;
            return;
        }

        int score = 10 - depth;
        int arrows = apeach[depth] + 1;
        if(remain >= arrows){
            tmp[depth] = arrows;
            dfs(depth + 1, remain - arrows, lionScore + score, apeachScore);
            tmp[depth] = 0;
        }

        dfs(depth + 1, remain, lionScore, apeach[depth] > 0 ? apeachScore + score : apeachScore);
    }

    private int[] compare(int diff){
        if(result == null || diff > max){
            max = diff;
            return Arrays.copyOf(tmp, tmp.length);
        }
        int[] arr = null;
        for(int i = 10; i >= 0; i--){
            if(result[i] < tmp[i]){
                arr = Arrays.copyOf(tmp, tmp.length);
                break;
            }else if(result[i] > tmp[i]){
                arr = Arrays.copyOf(result, result.length);
                break;
            }
        }
        return arr;
    }
}

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        int n = 5;
        int[] info = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(Arrays.toString(new Solution().solution(n, info)));

        long finishTime = System.nanoTime();
        System.out.println("\nRunning time: " + (finishTime - startTime));
    }
}
