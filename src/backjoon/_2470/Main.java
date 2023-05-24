package backjoon._2470;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Integer[] sols = new Integer[n];
        StringTokenizer st = new StringTokenizer(br.readLine().toString());
        for(int i = 0; st.hasMoreTokens(); i++){
            sols[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sols, (x,y) -> Math.abs(x) - Math.abs(y));
        int minVal = Integer.MAX_VALUE;
        int[] minArr = {};
        for(int i = 0; i < sols.length - 1; i++){
            int curVal = Math.abs(sols[i] + sols[i+1]);
            if(minVal > curVal){
                minVal = curVal;
                minArr = new int[]{sols[i], sols[i+1]};
            }
        }
        Arrays.sort(minArr);

        System.out.printf("%d %d", minArr[0], minArr[1]);
    }
}
