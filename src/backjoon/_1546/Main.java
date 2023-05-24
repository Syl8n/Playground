package backjoon._1546;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        double answer = 0.f;
        double max = 0.f;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0 ; i < n; i++){
            double mark = Double.parseDouble(st.nextToken());
            answer += mark;
            max = Math.max(max, mark);
        }
        answer = answer * 100 / max / n;
        System.out.println(answer);
    }
}