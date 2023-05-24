package programmers._72412;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    Map<Dev, List<Integer>> map = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        int[] ans = new int[query.length];

        for(String s : info){
            input(s.split(" "), 0);
        }

        for(List<Integer> list : map.values()){
            list.sort(Integer::compareTo);
        }

        for(int i = 0; i < query.length; i++){
            String s = query[i].replace("and ", "");
            ans[i] = search(s.split(" "));
        }

        return ans;
    }

    private int search(String[] s){
        List<Integer> list = map.getOrDefault(new Dev(s), null);
        if(list == null){
            return 0;
        }
        int threshold = Integer.parseInt(s[s.length - 1]);
        int left = 0;
        int right = list.size();
        while(left < right){
            int mid = left + (right - left) / 2;
            if(list.get(mid) < threshold){
                left = mid + 1;
                continue;
            }
            if(list.get(mid) >= threshold){
                right = mid;
            }
        }
        return list.size() - left;
    }

    private void input(String[] s, int depth) {
        if(depth == s.length - 1){
            Dev dev = new Dev(s);
            List<Integer> list = map.getOrDefault(dev, new ArrayList<>());
            list.add(Integer.parseInt(s[s.length - 1]));
            map.put(dev, list);
            return;
        }
        input(s, depth + 1);
        String tmp = s[depth];
        s[depth] = "-";
        input(s, depth + 1);
        s[depth] = tmp;
    }

    private static class Dev {

        String lang;
        String depart;
        String career;
        String food;

        public Dev(String[] s) {
            this.lang = s[0];
            this.depart = s[1];
            this.career = s[2];
            this.food = s[3];
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash = 31 * hash + lang.hashCode();
            hash = 31 * hash + depart.hashCode();
            hash = 31 * hash + career.hashCode();
            hash = 31 * hash + food.hashCode();
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            Dev dev = (Dev) obj;
            return this.lang.equals(dev.lang)
                && this.depart.equals(dev.depart)
                && this.career.equals(dev.career)
                && this.food.equals(dev.food);
        }
    }
}

public class Main {

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        String[] s = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] q = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};

        System.out.println(Arrays.toString(new Solution().solution(s, q)));

        long finishTime = System.nanoTime();
        System.out.println("\nRunning time: " + (finishTime - startTime));
    }
}