package backjoon._1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1987

public class Main {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int maxStep = 0;
    static int R;
    static int C;
    static char[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = Arrays.copyOf(br.readLine().toCharArray(), C);
        }
        dfs(0, 0, 0, 0);
        //dfs(0, 0, "");
        System.out.println(maxStep);
    }

    private static void dfs(int x, int y, int route, int count) {
        if(!validate(route, board[x][y])){
            maxStep = Math.max(maxStep, count);
            return;
        }
        route = getNewRoute(route, board[x][y]);
        count++;
        for(int i = 0; i < dx.length; i++){
            int newX = x + dx[i];
            int newY = y + dy[i];
            if(newX < 0 || newY < 0 || newX >= R || newY >= C){
                continue;
            }
            dfs(newX, newY, route, count);
        }
    }

    private static boolean validate(int route, char c){
        return (route & charToBit(c)) == 0;
    }

    private static int getNewRoute(int route, char c){
        return route | charToBit(c);
    }

    private static int charToBit(char c){
        return 1 << (c - 'A');
    }

//    private static void dfs(int x, int y, String route) {
//        if(!validate(route, board[x][y])){
//            maxStep = Math.max(maxStep, route.length());
//            return;
//        }
//        route += board[x][y];
//        for(int i = 0; i < dx.length; i++){
//            int newX = x + dx[i];
//            int newY = y + dy[i];
//            if(newX < 0 || newY < 0 || newX >= R || newY >= C){
//                continue;
//            }
//            dfs(newX, newY, route);
//        }
//    }
//
//    private static boolean validate(String route, char pos){
//        for(int i = 0; i < route.length(); i++){
//            if(route.charAt(i) == pos){
//                return false;
//            }
//        }
//        return true;
//    }

}
