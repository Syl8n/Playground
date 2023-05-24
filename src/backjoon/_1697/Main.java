package backjoon._1697;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

class Solution1{
    public int solution(int n, int k){
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        queue.offer(n);
        for(int t = 0; ; t++){
            if(queue.contains(k)){
                return t;
            }
            LinkedList<Integer> newQ = new LinkedList<>();
            queue.forEach(x -> {
                if(!visited[x]){
                    visited[x] = true;
                }
                if(x - 1 >= 0 && !visited[x - 1]) {
                    newQ.offer(x - 1);
                }
                if(x + 1 <= 100000 && !visited[x + 1]) {
                    newQ.offer(x + 1);
                }
                if(x * 2 <= 100000 && !visited[x * 2]) {
                    newQ.offer(x * 2);
                }
            });
            queue = newQ;
        }
    }
}

class Solution2{
    public int solution(int n, int k){
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> histSet = new HashSet<>();
        set.add(n);
        for(int t = 0; t < 10000; t++){
            if(set.contains(k)){
                return t;
            }
            histSet.addAll(set);
            HashSet<Integer> newSet = new HashSet<>();
            set.forEach(x -> {
                if(x - 1 >= 0) {
                    newSet.add(x - 1);
                }
                if(x + 1 <= 100000) {
                    newSet.add(x + 1);
                }
                if(x * 2 <= 100000) {
                    newSet.add(x * 2);
                }
            });
            newSet.removeAll(histSet);
            set = newSet;
        }
        return -1;
    }
}

class Solution3{
    static int result;

    public void solution(int n, int k, int cnt) {
        if(n >= k){
            cnt += n - k;
            if(cnt < result){
                result = cnt;
            }
            return;
        }

        if(n == 0){
            n = 1;
            cnt++;
        }

        if(k % 2 == 0){
            solution(n, k / 2, cnt + 1);
        }else{
            solution(n, k - 1, cnt + 1);
            solution(n, k + 1, cnt + 1);
        }
    }

    public int solution(int n, int k) {
        result = Math.abs(n - k);
        solution(n, k, 0);
        return result;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int n = Integer.parseInt(st.nextToken());
//        int k = Integer.parseInt(st.nextToken());
//        br.close();


//        new Solution2().solution(n, k);

        for(int n = 0; n <= 100000; n++){
            System.out.println(n);
            for(int k = 0; k <= 100000; k++){
                if(new Solution1().solution(n, k) != new Solution3().solution(n, k)){
                    System.out.println(n + " " + k);
                    break;
                };
            }
        }
    }
}