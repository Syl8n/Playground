package programmers._42883_stack;

import java.util.Stack;
class Solution {
    public String solution(String number, int k) {
        Stack<Integer> stack = new Stack<>();

        stack.push(number.charAt(0) - '0');
        for(int i = 1; i < number.length(); i++){
            int n = number.charAt(i) - '0';
            while(k > 0 && !stack.empty() && stack.peek() < n){
                stack.pop();
                k--;
            }
            stack.push(n);
        }

        while(k > 0){
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.empty()){
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
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
