public class MaxSubarray {

    /**
     * Finds the contiguous subarray with the largest sum using Kadane's algorithm.
     * Time complexity: O(n), Space complexity: O(1).
     *
     * @param nums input array of integers (at least one element)
     * @return the maximum subarray sum
     */
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // Either extend the current subarray or start a new one from nums[i]
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        MaxSubarray solution = new MaxSubarray();

        System.out.println("maxSubArray([-2,1,-3,4,-1,2,1,-5,4]) -> "
                + solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); // 6

        System.out.println("maxSubArray([1]) -> "
                + solution.maxSubArray(new int[]{1})); // 1

        System.out.println("maxSubArray([5,4,-1,7,8]) -> "
                + solution.maxSubArray(new int[]{5, 4, -1, 7, 8})); // 23
    }
}
