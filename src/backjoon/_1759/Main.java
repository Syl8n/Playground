package backjoon._1759;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static char[] letters;
    static char[] chars;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        letters = br.readLine().replace(" ", "").toCharArray();
        Arrays.sort(letters);
        visited = new boolean[p];
        chars = new char[r];

        solution(0);
    }

    private static void solution(int depth) {
        if (depth == chars.length){
            System.out.println(new String(chars));
            return;
        }
        for(int i = depth; i < letters.length; i++){
            if(visited[i]){
                continue;
            }
            if(!validate(letters[i], depth)){
                continue;
            }
            visited[i] = true;
            chars[depth] = letters[i];
            solution(depth + 1);
            visited[i] = false;
        }
    }

    private static boolean validate(char letter, int depth) {
        if(depth > 0 && chars[depth - 1] >= letter){
            return false;
        }
        if(depth == chars.length - 1){
            int cnt = countVowels(depth);
            if(isVowel(letter) && cnt > chars.length - 3){
                return false;
            }
            if(!isVowel(letter) && (cnt < 1 || cnt > chars.length - 2)){
                return false;
            }
        }
        return true;
    }

    private static int countVowels(int depth) {
        int cnt = 0;
        for(int i = 0; i < depth; i++){
            if(isVowel(chars[i])){
                cnt++;
            }
        }
        return cnt;
    }

    private static boolean isVowel(char letter) {
        return letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u';
    }
}
