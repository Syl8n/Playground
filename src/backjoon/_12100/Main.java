package backjoon._12100;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static Block[][] board;
    static int sum = 0, answer = 0;
    static int[] dx = {-1, 0, 0, 1}, dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new Block[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 0) {
                    continue;
                }
                board[i][j] = new Block(num);
                sum += num;
                answer = Math.max(answer, num);
            }
        }

        for (int i = 0; i < dx.length; i++) {
            dfs(board, dx[i], dy[i], 0, 0);
        }

        System.out.println(answer);
    }

    private static void dfs(Block[][] board, int x, int y, int value, int depth) {
        if (depth == 5) {
            answer = Math.max(answer, value);
            return;
        }
        if (value == sum) {
            answer = value;
            return;
        }
        Block[][] newBoard = deepCopy(board);
        value = move(newBoard, x, y);

        for (int i = 0; i < dx.length; i++) {
            dfs(newBoard, dx[i], dy[i], value, depth + 1);
        }
    }

    private static int move(Block[][] newBoard, int x, int y) {
        int max = 0;

        int iStart = Math.max(x * (newBoard.length - 1), 0);
        int jStart = Math.max(y * (newBoard.length - 1), 0);
        int iEnd = (newBoard.length - 1) - iStart;
        int jEnd = (newBoard.length - 1) - jStart;

        if (y == 0) {
            // 상하로 이동
            for (int i = iStart; i != iEnd - x; i -= x) {
                for (int j = 0; j < newBoard.length; j++) {
                    if (newBoard[i][j] == null) {
                        continue;
                    }
                    int newI = i + x;
                    while (newI >= 0 && newI < newBoard.length && newBoard[newI][j] == null) {
                        newI = newI + x;
                    }
                    if (newI < 0 || newI >= newBoard.length || newBoard[newI][j].merged) {
                        if (newI - x != i) {
                            newBoard[newI - x][j] = newBoard[i][j];
                            newBoard[i][j] = null;
                        }
                        continue;
                    }
                    if (newBoard[newI][j].value == newBoard[i][j].value) {
                        newBoard[newI][j].value *= 2;
                        newBoard[newI][j].merged = true;
                        newBoard[i][j] = null;
                        max = Math.max(max, newBoard[newI][j].value);
                    }
                }
            }
        } else {
            // 좌우로 이동
            for (int j = jStart; j != jEnd - y; j -= y) {
                for (int i = 0; i < newBoard.length; i++) {
                    if (newBoard[i][j] == null) {
                        continue;
                    }
                    int newJ = j + y;
                    while (newJ >= 0 && newJ < newBoard.length && newBoard[i][newJ] == null) {
                        newJ = newJ + y;
                    }
                    if (newJ < 0 || newJ >= newBoard.length || newBoard[i][newJ].merged) {
                        if (newJ - y != j) {
                            newBoard[i][newJ - y] = newBoard[i][j];
                            newBoard[i][j] = null;
                        }
                        continue;
                    }
                    if (newBoard[i][newJ].value == newBoard[i][j].value) {
                        newBoard[i][newJ].value *= 2;
                        newBoard[i][newJ].merged = true;
                        newBoard[i][j] = null;
                        max = Math.max(max, newBoard[i][newJ].value);
                    }
                }
            }
        }

        return max;
    }

    private static Block[][] deepCopy(Block[][] board) {
        Block[][] newBoard = new Block[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != null) {
                    newBoard[i][j] = new Block(board[i][j].value);
                }
            }
        }
        return newBoard;
    }

    private static class Block {

        int value;
        boolean merged;

        public Block(int value) {
            this.value = value;
            this.merged = false;
        }
    }
}