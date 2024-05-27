import java.util.HashSet;

public class ContainsDuplicate {
    public static void main(String[] args) {
        int[] nums1 = new int[] { 1,2,3,1 };
        assert containsDuplicate(nums1);

        int[] nums2 = new int[] { 1,2,3,4 };
        assert !containsDuplicate(nums2);

        int[] nums3 = new int[] { 1,1,1,3,3,4,3,2,4,2 };
        assert containsDuplicate(nums3);
    }

    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> elements = new HashSet<>();
        for (int num : nums) {
            if (elements.contains(num)) {
                return true;
            }
            elements.add(num);
        }
        return false;
    }
}