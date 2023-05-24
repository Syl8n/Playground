package programmers._1835;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
class Solution {
    int count = 0;
    char[] names = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    Map<Character, Integer> pos = new HashMap<>();
    Map<Character, List<Rule>> map = new HashMap<>();
    public int solution(int n, String[] data) {
        for(String d : data){
            char from = d.charAt(0);
            char to = d.charAt(2);
            char sign = d.charAt(3);
            int dist = d.charAt(4) - '0';
            if(!map.containsKey(from)){
                map.put(from, new ArrayList<>());
            }
            map.get(from).add(new Rule(to, sign, dist));
            if(!map.containsKey(to)){
                map.put(to, new ArrayList<>());
            }
            map.get(to).add(new Rule(from, sign, dist));
        }

        dfs(0);

        return count;
    }

    private void dfs(int depth){
        if(depth == names.length){
            count++;
            return;
        }
        for(int i = 0; i < names.length; i++){
            char c = names[i];
            if(pos.containsKey(c)){
                continue;
            }
            if(validate(c, depth)){
                pos.put(c, depth);
                dfs(depth + 1);
                pos.remove(c);
            }
        }
    }

    private boolean validate(char from, int fromIdx){
        if(!map.containsKey(from)){
            return true;
        }
        for(Rule rule : map.get(from)){
            if(pos.containsKey(rule.to)){
                int toIdx = pos.get(rule.to);
                int diff = Math.abs(fromIdx - toIdx) - 1;
                switch(rule.sign){
                    case '=':
                        if(diff != rule.dist){
                            return false;
                        }
                        break;
                    case '>':
                        if(diff <= rule.dist){
                            return false;
                        }
                        break;
                    case '<':
                        if(diff >= rule.dist){
                            return false;
                        }
                }
            }
        }

        return true;
    }

    private static class Rule {
        char to;
        char sign;
        int dist;

        public Rule(char to, char sign, int dist){
            this.to = to;
            this.sign = sign;
            this.dist = dist;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        int n = 2;
        String[] data = {"N~F=0", "R~T>2"};
        System.out.println(new Solution().solution(n, data));

        long finishTime = System.nanoTime();
        System.out.println("\nRunning time: " + (finishTime - startTime));
    }
}
