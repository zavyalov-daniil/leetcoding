package two.pointers;

public class ContainerWithMostWater {
    public static void main(String[] args) {
        assert maxArea(new int[] {1,8,6,2,5,4,8,3,7}) == 49;
        assert maxArea(new int[] {1,1}) == 1;
        assert maxArea(new int[] {2,3,10,5,7,8,9}) == 36;//10 и 9 (9*4) --- 0..6
        assert maxArea(new int[] {2,3,4,5,18,17,6}) == 17;
        assert maxArea(new int[] {1,2,3,4,5,25,24,3,4}) == 24;
        assert maxArea(new int[] {88, 70, 2, 1}) == 70;
        assert maxArea(new int[] {1, 1, 1, 1, 1, 1, 2, 1, 3, 1, 88, 70, 2, 1, 8, 10}) == 70;
    }

    public static int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int res = 0;
        while (i < j) {
            int newArea = findArea(height, i, j);
            if (newArea > res) {
                res = newArea;
            }
            if (height[i] > height[j]) {
                --j;
            } else {
                ++i;
            }
        }
        return res;
    }

    private static int findArea(int[] height, int i, int j) {
        return Math.min(height[i], height[j]) * (j - i);
    }
}
