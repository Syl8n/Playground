package backjoon._5052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tests = Integer.parseInt(br.readLine());
        for(int t = 0; t < tests; t++){
            int N = Integer.parseInt(br.readLine());
            String[] s = new String[N];
            for(int n = 0; n < N; n++){
                s[n] = br.readLine();
            }

            Arrays.sort(s);
            boolean flag = false;
            for(int i = 0; i < s.length - 1; i++){
                if(s[i + 1].startsWith(s[i])){
                    flag = true;
                    break;
                }
            }
            if(flag) {
                System.out.println("NO");
            }else {
                System.out.println("YES");
            }
        }
    }
}