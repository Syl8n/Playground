package backjoon._1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1011

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            System.out.println(findRoute(from, to));
        }

    }

    private static int findRoute(int from, int to) {
        int left = from;
        int right = to;
        int interval = 1;
        int count = 0;
        while(left < right){
            left += interval;
            count++;
            if(left < right) {
                right -= interval;
                count++;
            }
            interval++;
        }
        return count;
    }
}
