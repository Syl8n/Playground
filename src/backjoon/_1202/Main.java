package backjoon._1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

class Jewel{
    int weight;
    int value;

    public Jewel(String weight, String value) {
        this.weight = Integer.parseInt(weight);
        this.value = Integer.parseInt(value);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int jewelNum = Integer.parseInt(s[0]);
        int bagNum = Integer.parseInt(s[1]);
        Jewel[] jewels = new Jewel[jewelNum];
        for(int j = 0; j < jewelNum; j++){
            s = br.readLine().split(" ");
            jewels[j] = new Jewel(s[0], s[1]);
        }
        Arrays.sort(jewels, (x, y) -> x.weight - y.weight);
        int[] bags = new int[bagNum];
        for(int b = 0; b < bagNum; b++){
            bags[b] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags);
        long sum = 0;
        int jewelP = 0;
        PriorityQueue<Jewel> pq = new PriorityQueue<>((x, y) -> y.value - x.value);
        for(int bagWeight : bags){
            while(jewelP < jewels.length && jewels[jewelP].weight <= bagWeight){
                pq.offer(jewels[jewelP++]);
            }
            if(!pq.isEmpty()){
                sum += pq.poll().value;
            }
        }
        System.out.println(sum);
    }
}