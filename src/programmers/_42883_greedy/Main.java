package programmers._42883_greedy;

class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        sb.append(number.charAt(0));

        for(int i = 1; i < number.length(); i++){
            while(k > 0 && sb.length() > 0 && sb.charAt(sb.length() - 1) < number.charAt(i)){
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }
            sb.append(number.charAt(i));
        }

        while(k > 0){
            sb.deleteCharAt(sb.length() - 1);
            k--;
        }

        return sb.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        String n = "1924";
        int k = 2;

        System.out.println(new Solution().solution(n, k));

        long finishTime = System.nanoTime();
        System.out.println("\nRunning time: " + (finishTime - startTime));
    }
}
