import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    /**
     * Given an array of integers and a target, return indices of the two numbers
     * that add up to the target. Uses a HashMap for O(n) lookup.
     *
     * @param nums   input array of integers
     * @param target the target sum
     * @return array of two indices whose values sum to target, or empty array if none found
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (seen.containsKey(complement)) {
                return new int[]{seen.get(complement), i};
            }
            seen.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        TwoSum solution = new TwoSum();

        int[] result1 = solution.twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println("twoSum([2,7,11,15], 9) -> [" + result1[0] + ", " + result1[1] + "]"); // [0, 1]

        int[] result2 = solution.twoSum(new int[]{3, 2, 4}, 6);
        System.out.println("twoSum([3,2,4], 6) -> [" + result2[0] + ", " + result2[1] + "]"); // [1, 2]

        int[] result3 = solution.twoSum(new int[]{3, 3}, 6);
        System.out.println("twoSum([3,3], 6) -> [" + result3[0] + ", " + result3[1] + "]"); // [0, 1]
    }
}
