public class BinarySearch {

    /**
     * Performs an iterative binary search on a sorted array.
     *
     * @param nums   a sorted array of integers
     * @param target the value to search for
     * @return the index of target in nums, or -1 if not found
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // avoids potential overflow
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch solution = new BinarySearch();

        System.out.println("search([-1,0,3,5,9,12], 9) -> "
                + solution.search(new int[]{-1, 0, 3, 5, 9, 12}, 9));  // 4

        System.out.println("search([-1,0,3,5,9,12], 2) -> "
                + solution.search(new int[]{-1, 0, 3, 5, 9, 12}, 2));  // -1

        System.out.println("search([5], 5) -> "
                + solution.search(new int[]{5}, 5));  // 0
    }
}
