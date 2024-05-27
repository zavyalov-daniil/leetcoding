package arrays.hashing;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {
    public static void main(String[] args) {
        int[] nums1 = new int[] { 2,7,11,15 };
        assert Arrays.equals(new int[] { 0, 1 }, twoSum(nums1, 9));

        int[] nums2 = new int[] { 3,2,4 };
        assert Arrays.equals(new int[] { 1, 2 }, twoSum(nums2, 6));

        int[] nums3 = new int[] { 3,3 };
        assert Arrays.equals(new int[] { 0, 1 }, twoSum(nums3, 6));
    }

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> reversedArray = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            Integer firstIndex = reversedArray.get(target - nums[i]);
            if (firstIndex != null) {
                return new int[] { firstIndex, i };
            }
            reversedArray.putIfAbsent(nums[i], i);
        }
        return new int[] {};
    }
}