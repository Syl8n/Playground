package backjoon._9663;

import java.util.Scanner;

public class Main {
    static int[] board;
    static int count = 0;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        board = new int[N];
        solution(N, 0);
        System.out.println(count);
    }

    private static void solution(int N, int depth) {
        if(depth == N){
            count++;
            return;
        }
        for(int i = 0; i < N; i++){
            if(!validate(i, depth)){
                continue;
            }
            board[depth] = i;
            solution(N, depth + 1);
        }
        return;
    }

    private static boolean validate(int idx, int depth) {
        for(int i = depth - 1; i >= 0; i--){
            if(board[i] == idx){
                return false;
            }
            if (board[i] == idx - depth + i || board[i] == idx + depth - i){
                return false;
            }
        }
        return true;
    }
}
