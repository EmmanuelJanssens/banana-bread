public class ClimbingStairs {

    /**
     * Counts the number of distinct ways to climb n stairs, where each step
     * you may climb 1 or 2 stairs. This follows the Fibonacci sequence.
     * Uses two variables instead of an array for O(1) space.
     * Time complexity: O(n), Space complexity: O(1).
     *
     * @param n the total number of stairs (n >= 1)
     * @return the number of distinct ways to reach the top
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int prev2 = 1; // ways to reach stair 1
        int prev1 = 2; // ways to reach stair 2
        for (int i = 3; i <= n; i++) {
            int current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }

    public static void main(String[] args) {
        ClimbingStairs solution = new ClimbingStairs();

        System.out.println("climbStairs(1) -> " + solution.climbStairs(1)); // 1
        System.out.println("climbStairs(2) -> " + solution.climbStairs(2)); // 2
        System.out.println("climbStairs(3) -> " + solution.climbStairs(3)); // 3
        System.out.println("climbStairs(4) -> " + solution.climbStairs(4)); // 5
        System.out.println("climbStairs(5) -> " + solution.climbStairs(5)); // 8
        System.out.println("climbStairs(10) -> " + solution.climbStairs(10)); // 89
    }
}
