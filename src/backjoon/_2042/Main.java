package backjoon._2042;

// https://www.acmicpc.net/problem/2042
// 세그먼트 트리, 펜윅 트리

import java.io.*;
import java.util.*;

public class Main {
    static class Tree {
        long[] nums;
        long[] tree;

        public Tree(int size) {
            nums = new long[size + 1];
            tree = new long[size + 1];
        }

        public void update(int idx, long value) {
            long diff = value - nums[idx];
            nums[idx] = value;
            while (idx < tree.length){
                tree[idx] += diff;
                idx += idx & -idx;
            }
        }

        public void getIntervalSum(int left, int right) {
            System.out.println(getSum(right) - getSum(left - 1));
        }

        public long getSum(int idx){
            long sum = 0;
            while (idx > 0){
                sum += tree[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Tree tree = new Tree(N);

        for (int i = 1; i <= N; i++) {
            tree.update(i, Long.parseLong(br.readLine()));
        }

        long[][] instructions = new long[M + K][3];
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            instructions[i] = new long[]{Long.parseLong(st.nextToken()),
                    Long.parseLong(st.nextToken()),
                    Long.parseLong(st.nextToken())};
        }

        readInstructions(instructions, tree);

    }

    private static void readInstructions(long[][] instructions, Tree tree) {
        for (int i = 0; i < instructions.length; i++) {
            if (instructions[i][0] == 1) {
                tree.update((int) instructions[i][1], instructions[i][2]);
            } else {
                tree.getIntervalSum((int) instructions[i][1], (int) instructions[i][2]);
            }
        }
    }
}

//public class Main {
//    static class Tree {
//        long[] tree;
//        int[] indices;
//
//        public Tree(int N) {
//            int size = 1;
//            while (size < N) {
//                size = size << 1;
//            }
//            tree = new long[size << 1];
//            indices = new int[N];
//        }
//
//        public long init(int idx, int start, int end, long[] nums) {
//            if (start == end) {
//                indices[start] = idx;
//                return tree[idx] = nums[start];
//            }
//            int mid = (start + end) / 2;
//            tree[idx] = init(idx * 2, start, mid, nums) + init(idx * 2 + 1, mid + 1, end, nums);
//            return tree[idx];
//        }
//
//        public void update(int idx, long value) {
//            int i = indices[idx];
//            long diff = value - tree[i];
//            while (i > 0) {
//                tree[i] += diff;
//                i = i >> 1;
//            }
//        }
//        public void getIntervalSum(int left, int right) {
//            System.out.println(getIntervalSum(1, left, right, 0, indices.length - 1));
//        }
//
//        public long getIntervalSum(int idx, int left, int right, int start, int end) {
//            if (start > right || end < left) {
//                return 0;
//            }
//            if (start >= left && end <= right) {
//                return tree[idx];
//            }
//            int mid = (start + end) / 2;
//            return getIntervalSum(idx * 2, left, right, start, mid) +
//                    getIntervalSum(idx * 2 + 1, left, right, mid + 1, end);
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//        int K = Integer.parseInt(st.nextToken());
//
//        Tree tree = new Tree(N);
//
//        long[] nums = new long[N];
//        for (int i = 0; i < N; i++) {
//            nums[i] = Long.parseLong(br.readLine());
//        }
//
//        tree.init(1, 0, N - 1, nums);
//
//        long[][] instructions = new long[M + K][3];
//        for (int i = 0; i < M + K; i++) {
//            st = new StringTokenizer(br.readLine());
//            instructions[i] = new long[]{Long.parseLong(st.nextToken()),
//                    Long.parseLong(st.nextToken()) - 1,
//                    Long.parseLong(st.nextToken())};
//        }
//
//        readInstructions(instructions, tree);
//
//    }
//
//    private static void readInstructions(long[][] instructions, Tree tree) {
//        for (int i = 0; i < instructions.length; i++) {
//            if (instructions[i][0] == 1) {
//                tree.update((int) instructions[i][1], instructions[i][2]);
//            } else {
//                tree.getIntervalSum((int) instructions[i][1], (int) instructions[i][2] - 1);
//            }
//        }
//    }

//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//        int K = Integer.parseInt(st.nextToken());
//
//        long[] nums = new long[N];
//        long[] cumSum = new long[N];
//        for(int i = 0 ; i < N; i++){
//            nums[i] = Long.parseLong(br.readLine());
//            if(i == 0){
//                cumSum[i] = nums[i];
//            } else {
//                cumSum[i] = nums[i] + cumSum[i - 1];
//            }
//        }
//
//        long[][] instructions = new long[M + K][3];
//        for(int i = 0; i < M + K; i++){
//            st = new StringTokenizer(br.readLine());
//            instructions[i] = new long[]{Long.parseLong(st.nextToken()),
//                    Long.parseLong(st.nextToken()) - 1,
//                    Long.parseLong(st.nextToken())};
//        }
//
//        readInstructions(instructions, cumSum, nums);
//
//    }
//
//    private static void readInstructions(long[][] instructions, long[] cumSum, long[] nums) {
//        for(int i = 0; i < instructions.length; i++){
//            if(instructions[i][0] == 1){
//                changeNumber((int) instructions[i][1], instructions[i][2], cumSum, nums);
//            } else {
//                printIntervalSum(instructions[i][1], instructions[i][2], cumSum);
//            }
//        }
//    }
//
//    private static void printIntervalSum(long start, long end, long[] cumSum) {
//        System.out.println(cumSum[(int) (end - 1)] - (start - 1 >= 0 ? cumSum[(int) (start - 1)] : 0));
//    }
//
//    private static void changeNumber(int idx, long num, long[] cumSum, long[] nums) {
//        long diff = num - nums[idx];
//        nums[idx] = num;
//        for(int i = idx; i < cumSum.length; i++){
//            cumSum[i] += diff;
//        }
//    }
//}
