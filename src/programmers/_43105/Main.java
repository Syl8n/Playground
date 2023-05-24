package programmers._43105;

import java.util.Arrays;
class Solution {
    public int solution(int[][] triangle) {
        for(int i = 1; i < triangle.length; i++){
            for(int j = 0; j < triangle[i].length; j++){
                int left = 0, right = 0;
                if(j != 0){
                    left = triangle[i-1][j-1];
                }
                if(j != triangle[i].length - 1){
                    right = triangle[i-1][j];
                }
                triangle[i][j] += Math.max(left, right);
            }
        }
        return Arrays.stream(triangle[triangle.length - 1]).max().getAsInt();
    }
}

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();



        long finishTime = System.nanoTime();
        System.out.println("\nRunning time: " + (finishTime - startTime));
    }
}
