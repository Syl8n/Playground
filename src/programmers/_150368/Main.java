package programmers._150368;

import java.util.Arrays;

class Solution {
    int[] ans = new int[2];
    int[] discount = {10, 20, 30, 40};
    int[][] U;
    int[] E, dc;
    public int[] solution(int[][] users, int[] emoticons) {
        U = users;
        E = emoticons;
        dc = new int[E.length];
        dfs(0);
        return ans;
    }
    
    private void dfs(int depth){
        if(depth == dc.length){
            int[] tmp = calc();
            if(tmp[0] > ans[0]){
                ans[0] = tmp[0];
                ans[1] = tmp[1];
            } else if (tmp[0] == ans[0]){
                ans[1] = Math.max(ans[1], tmp[1]);
            }
            return;
        }
        for(int i = 0; i < discount.length; i++){
            dc[depth] = discount[i];
            dfs(depth + 1);
        }
    }
    
    private int[] calc(){
        int[] tmp = new int[2];
        for(int[] u : U){
            int sum = 0;
            boolean flag = true;
            for(int i = 0; i < E.length; i++){
                if(dc[i] >= u[0]){
                    sum += E[i] * (100 - dc[i]) / 100;
                    if(sum >= u[1]){
                        flag = false;
                        tmp[0]++;
                        break;
                    }
                }
            }
            if(flag){
                tmp[1] += sum;
            }
        }
        return tmp;
    }
}

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        int[][] users = {{40, 10000}, {25, 10000}};
        int[] e = {7000, 9000};

        System.out.println(Arrays.toString(new Solution().solution(users, e)));

        long finishTime = System.nanoTime();
        System.out.println("\nRunning time: " + (finishTime - startTime));
    }
}