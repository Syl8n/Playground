package backjoon._12865;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int bagSize = sc.nextInt();
        int[] table = new int[bagSize + 1];
        int maxValue = 0;
        for(int i = 0; i < N; i++){
            int size = sc.nextInt();
            int value = sc.nextInt();
            for(int j = bagSize - size; j >= 0; j--){
                table[size + j] = Math.max(table[size + j], table[j] + value);
                maxValue = Math.max(maxValue, table[j] + value);
            }
        }
        System.out.println(maxValue);
    }
}
