package arrays.hashing;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,4};
        assert Arrays.equals(new int[] { 24,12,8,6 }, productExceptSelf(nums));

        nums = new int[] {-1,1,0,-3,3};
        assert Arrays.equals(new int[] { 0,0,9,0,0 }, productExceptSelf(nums));
    }

    /**
     * Легальное решение, но с подсказкой
     * Runtime 2 ms Beats 87.38%
     * Memory 54.95 MB Beats 89.75%
     */
    public static int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int temp = 1;
        for (int i = 0; i < nums.length; i++) {
            res[i] = temp;
            temp *= nums[i];
        }
        temp = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= temp;
            temp *= nums[i];
        }
        return res;
    }

    /**
     * Ещё не легальное решение. Используется деление.
     * Но теперь при наличии нуля мы не пересчитываем произведение и делим получившиеся в prod нули на num[i],
     * а просто игнорим в произведении 0
     * Runtime 1-2 ms Beats 100%-87.38%
     * Memory 55.18 MB Beats 70.79%
     */
    public static int[] productExceptSelf2(int[] nums) {
        int[] res = new int[nums.length];
        int prod = 1;
        int nullIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (nullIndex == -1) {
                    nullIndex = i;
                    continue;
                } else {
                    prod = 0;
                    break;
                }
            }
            prod *= nums[i];
        }
        if (nullIndex == -1) {
            for (int i = 0; i < nums.length; i++) {
                res[i] = prod/nums[i];
            }
        } else {
            res[nullIndex] = prod;
        }
        return res;
    }

    /**
     * Нелегальное решение. Используется деление.
     * Runtime 1 ms Beats 100.00%
     * Memory 55.21 MB Beats 59.10%
     */
    public static int[] productExceptSelf3(int[] nums) {
        int[] res = new int[nums.length];
        int prod = 1;
        for (int num : nums) {
            prod *= num;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                int localProd = 1;
                for (int j = 0; j < nums.length; j++) {
                    if (j != i) {
                        localProd *= nums[j];
                    }
                }
                res[i] = localProd;
            } else {
                res[i] = prod/nums[i];
            }
        }
        return res;
    }
}
