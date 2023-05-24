package programmers._181188;

import java.util.Arrays;
import java.util.PriorityQueue;
class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (x, y) -> x[1] - y[1]);

        int count = 1;
        int end = targets[0][1];
        for(int[] target : targets){
            if(target[0] >= end){
                count++;
                end = target[1];
            }
        }

        return count;
    }
}

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        PriorityQueue<Integer> pq = new PriorityQueue<>();


        long finishTime = System.nanoTime();
        System.out.println("\nRunning time: " + (finishTime - startTime));
    }
}
