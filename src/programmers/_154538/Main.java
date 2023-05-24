package programmers._154538;

import java.util.HashSet;
import java.util.Set;
class Solution {
    public int solution(int x, int y, int n) {
        Set<Integer> set = new HashSet<>();
        set.add(x);
        int count = 0;
        while(!set.isEmpty()){
            if(set.contains(y)){
                return count;
            }
            Set<Integer> tmpSet = new HashSet<>();
            for(int base : set){
                int xn = base + n;
                int x2 = base * 2;
                int x3 = base * 3;
                if(xn <= y){
                    tmpSet.add(xn);
                }
                if(x2 <= y){
                    tmpSet.add(x2);
                }
                if(x3 <= y){
                    tmpSet.add(x3);
                }
            }
            set = tmpSet;
            count++;
        }

        return -1;
    }
}

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();



        long finishTime = System.nanoTime();
        System.out.println("\nRunning time: " + (finishTime - startTime));
    }
}
