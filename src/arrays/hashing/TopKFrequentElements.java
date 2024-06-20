package arrays.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements {
    public static void main(String[] args) {
        int[] res = topKFrequent(new int[] {1,1,1,2,2,3}, 2);
        assert Arrays.equals(new int[] {1,2}, res);

        res = topKFrequent(new int[] {1}, 1);
        assert Arrays.equals(new int[] {1}, res);

        res = topKFrequent(new int[] {1, 2}, 2);
        assert Arrays.equals(new int[] {1, 2}, res);
    }

    //O(N) solution
    //Runtime 11 ms, Beats 90.33%
    //Memory 48.00 MB, Beats 79.86%
    //Идею подсмотрел
    public int[] topKFrequent4(int[] nums, int k) {
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] buckets = new List[nums.length + 1];
        for (int key : count.keySet()) {
            if (buckets[count.get(key)] == null) {
                buckets[count.get(key)] = new LinkedList<>();
            }
            buckets[count.get(key)].add(key);
        }

        int[] res = new int[k];
        for (int i = buckets.length - 1; i > 0 && k > 0; i--) {
            if (buckets[i] != null) {
                for (int element : buckets[i]) {
                    --k;
                    res[k] = element;
                }
            }
        }
        return res;
    }

    //Чуть хуже по времени по сравнению с третьим. Но по памяти вроде +- нормально. O(NlogN)
    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        return count.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(k)
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }

    //O(NlogN)
    public static int[] topKFrequent1(int[] nums, int k) {
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(count.entrySet());
        entries.sort(Map.Entry.<Integer, Integer>comparingByValue().reversed());
        int[] mostFrequentNums = new int[k];
        for (int i = 0; i < k && i < entries.size(); i++) {
            mostFrequentNums[i] = entries.get(i).getKey();
        }
        return mostFrequentNums;
    }
}