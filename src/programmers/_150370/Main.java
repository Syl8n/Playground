package programmers._150370;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int todayStamp = toTimeStamp(Arrays.stream(today.split("\\.")).mapToInt(Integer::parseInt).toArray());
        Map<String, Integer> termsMap = Arrays.stream(terms).map(e -> e.split(" "))
            .collect(Collectors.toMap(e -> e[0], e -> Integer.parseInt(e[1]) * 28));

        return IntStream.range(0, privacies.length)
            .filter(i -> {
              String[] s = privacies[i].split(" ");
              return toTimeStamp(Arrays.stream(s[0].split("\\.")).mapToInt(Integer::parseInt).toArray())
                  + termsMap.get(s[1]) <= todayStamp;
            })
            .map(i -> i + 1)
            .toArray();
    }

    private static int toTimeStamp(int[] todays) {
        return (todays[0] * 12 + todays[1]) * 28 + todays[2];
    }
}

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        String today = "2022.05.19";
        String[] terms = {"A 6", "B 12", "C 3"};
        String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
        System.out.println(Arrays.toString(new Solution().solution(today, terms, privacies)));

        long finishTime = System.nanoTime();
        System.out.println("\nRunning time: " + (finishTime - startTime));
    }
}
