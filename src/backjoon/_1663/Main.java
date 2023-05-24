package backjoon._1663;

// https://www.acmicpc.net/problem/1663

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String type = br.readLine();
        int n = Integer.parseInt(br.readLine());
        StringBuilder[] sb = new StringBuilder[Math.min(n, 3)];
        sb[0] = new StringBuilder("X");
        if(n >= 2) {
            sb[1] = new StringBuilder("YZ");
        }
        if(n >= 3) {
            sb[2] = new StringBuilder("ZX");
        }
        for(int i = 3; i < n; i++){
            int mod = i % 3;
            sb[mod] = sb[mod].append(sb[(i + 1) % 3]);
        }

        switch (type){
            case "1":
                System.out.println(sb[(n - 1) % 3].toString().length());
                break;
            case "2":
                int k = Integer.parseInt(br.readLine());
                System.out.println(sb[(n - 1) % 3].charAt(k - 1));
                break;
            case "3":
                char c = br.readLine().charAt(0);
                int count = 0;
                for(int i = 0; i < sb[(n - 1) % 3].length(); i++){
                    if(sb[(n - 1) % 3].charAt(i) == c){
                        count++;
                    }
                }
                System.out.println(count);
        }
    }
}