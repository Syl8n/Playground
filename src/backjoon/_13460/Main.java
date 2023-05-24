package backjoon._13460;

// https://www.acmicpc.net/problem/13460

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] board;
    static boolean[][][][] visit;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Ball {
        int[] red;
        int[] blue;
        int move;

        public Ball() {
            this.move = 0;
        }

        public Ball(Ball now) {
            this.red = now.red.clone();
            this.blue = now.blue.clone();
            this.move = now.move;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        Ball b = new Ball();
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'R') {
                    b.red = new int[]{i, j};
                    board[i][j] = '.';
                } else if (board[i][j] == 'B') {
                    b.blue = new int[]{i, j};
                    board[i][j] = '.';
                }
            }
        }

        visit = new boolean[N][M][N][M];
        Queue<Ball> q = new LinkedList<>();
        q.offer(b);
        visit[b.red[0]][b.red[1]][b.blue[0]][b.blue[1]] = true;
        while (!q.isEmpty()) {
            Ball now = q.poll();

            if (board[now.blue[0]][now.blue[1]] == 'O') {
                continue;
            } else if (now.move > 10) {
                continue;
            }

            if (board[now.red[0]][now.red[1]] == 'O') {
                System.out.println(now.move);
                System.exit(0);
            }

            for (int dir = 0; dir < 4; dir++) {
                Ball next = move(dir, now);
                if (visit[next.red[0]][next.red[1]][next.blue[0]][next.blue[1]])
                    continue;
                visit[next.red[0]][next.red[1]][next.blue[0]][next.blue[1]] = true;
                q.offer(next);
            }
        }

        System.out.println(-1);
    }

    private static Ball move(int dir, Ball now) {
        Ball next = new Ball(now);
        int nr = 0, nc = 0, flag = 0;
        switch (dir) {
            case 0:
                if (next.red[0] > next.blue[0])
                    flag = 0;
                else
                    flag = 1;

                break;
            case 1:
                if (next.red[0] > next.blue[0])
                    flag = 1;
                else
                    flag = 0;

                break;
            case 2:
                if (next.red[1] > next.blue[1])
                    flag = 0;
                else
                    flag = 1;
                break;
            case 3:
                if (next.red[1] > next.blue[1])
                    flag = 1;
                else
                    flag = 0;

                break;
        }

        if (flag == 0) {
            nr = next.blue[0] + dx[dir];
            nc = next.blue[1] + dy[dir];
            while (board[nr][nc] == '.') {
                nr += dx[dir];
                nc += dy[dir];
            }
            if (board[nr][nc] == 'O') {
                next.blue = new int[]{nr, nc};
            } else
                next.blue = new int[]{nr - dx[dir], nc - dy[dir]};

            nr = next.red[0] + dx[dir];
            nc = next.red[1] + dy[dir];
            while (board[nr][nc] == '.') {
                if (nr == next.blue[0] && nc == next.blue[1])
                    break;
                nr += dx[dir];
                nc += dy[dir];
            }
            if (board[nr][nc] == 'O') {
                next.red = new int[]{nr, nc};
            } else
                next.red = new int[]{nr - dx[dir], nc - dy[dir]};
        } else if (flag == 1) {
            nr = next.red[0] + dx[dir];
            nc = next.red[1] + dy[dir];
            while (board[nr][nc] == '.') {
                nr += dx[dir];
                nc += dy[dir];
            }
            if (board[nr][nc] == 'O') {
                next.red = new int[]{nr, nc};
            } else
                next.red = new int[]{nr - dx[dir], nc - dy[dir]};

            nr = next.blue[0] + dx[dir];
            nc = next.blue[1] + dy[dir];
            while (board[nr][nc] == '.') {
                if (nr == next.red[0] && nc == next.red[1])
                    break;
                nr += dx[dir];
                nc += dy[dir];
            }
            if (board[nr][nc] == 'O') {
                next.blue = new int[]{nr, nc};
            } else
                next.blue = new int[]{nr - dx[dir], nc - dy[dir]};
        }

        next.move++;
        return next;
    }
}
