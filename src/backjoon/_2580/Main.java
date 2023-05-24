package backjoon._2580;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<int []> zeros = new ArrayList<>();
        int[][] board = new int[9][9];
        for(int i = 0; i < board.length; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < board.length; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 0){
                    zeros.add(new int[]{i, j});
                }
            }
        }

        fillBoard(board, zeros, 0, true);

        StringBuilder sb = new StringBuilder();
        for(int[] row: board){
            for(int e : row){
                sb.append(e).append(' ');
            }
            sb.append(System.lineSeparator());
        }

        System.out.println(sb);
    }

    private static boolean fillBoard(int[][] board, List<int[]> zeros, int depth, boolean keepFinding) {
        if(depth == zeros.size()){
            return false;
        }
        if(!keepFinding){
            return false;
        }
        for(int i = 0; i < zeros.size(); i++){
            int[] pos = zeros.get(i);
            if(board[pos[0]][pos[1]] != 0){
                continue;
            }
            for(int n = 1; n <= 9; n++){
                if(validateNum(board, pos, n)){
                    board[pos[0]][pos[1]] = n;
                    keepFinding = fillBoard(board, zeros, depth + 1, keepFinding);
                    if(keepFinding){
                        board[pos[0]][pos[1]] = 0;
                    }
                    if(!keepFinding){
                        return keepFinding;
                    }
                }
            }
            if(board[pos[0]][pos[1]] == 0){
                break;
            }
        }
        return keepFinding;
    }

    private static boolean validateNum(int[][] board, int[] pos, int n) {
        for(int i = 0; i < board.length; i++){
            if(board[i][pos[1]] == n){
               return false;
            }
        }
        for(int i = 0; i < board.length; i++){
            if(board[pos[0]][i] == n){
                return false;
            }
        }
        int newX = pos[0] / 3 * 3;
        int newY = pos[1] / 3 * 3;
        for(int i = newX; i < newX + 3; i++){
            for(int j = newY; j < newY + 3; j++){
                if(board[i][j] == n){
                    return false;
                }
            }
        }
        return true;
    }

}
