import java.util.Arrays;

public class MergeSortedArrays {

    /**
     * Merges nums2 into nums1 in-place. nums1 has a length of m + n, where the
     * last n elements are set to 0 as placeholders. Iterates from the end of
     * both arrays to avoid overwriting unprocessed elements.
     *
     * @param nums1 first sorted array with extra capacity for nums2 elements
     * @param m     number of valid elements in nums1
     * @param nums2 second sorted array
     * @param n     number of elements in nums2
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;       // pointer for nums1 valid elements
        int j = n - 1;       // pointer for nums2
        int k = m + n - 1;   // pointer for the merged position in nums1

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
        // Copy any remaining elements from nums2 (nums1 elements are already in place)
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    public static void main(String[] args) {
        MergeSortedArrays solution = new MergeSortedArrays();

        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        solution.merge(nums1, 3, nums2, 3);
        System.out.println("merge([1,2,3,0,0,0], 3, [2,5,6], 3) -> " + Arrays.toString(nums1));
        // [1, 2, 2, 3, 5, 6]

        int[] nums3 = {1};
        int[] nums4 = {};
        solution.merge(nums3, 1, nums4, 0);
        System.out.println("merge([1], 1, [], 0) -> " + Arrays.toString(nums3));
        // [1]

        int[] nums5 = {0};
        int[] nums6 = {1};
        solution.merge(nums5, 0, nums6, 1);
        System.out.println("merge([0], 0, [1], 1) -> " + Arrays.toString(nums5));
        // [1]
    }
}
