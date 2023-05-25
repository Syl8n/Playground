package programmers._42746;

import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String str = String.join("", Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .sorted((a, b) -> (b + a).compareTo(a + b))
                .toArray(String[]::new));
        StringBuilder sb = new StringBuilder(str);
        for(int i = 0; i < sb.length(); i++){
            if(i < sb.length() - 1 && sb.charAt(i) == '0'){
                sb.deleteCharAt(i--);
            } else {
                break;
            }
        }
        return sb.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        int[] numbers = {0, 0, 0, 0};
        System.out.println(new Solution().solution(numbers));
    }
}