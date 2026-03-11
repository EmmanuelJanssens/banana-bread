public class Palindrome {

    /**
     * Determines whether an integer is a palindrome without converting it to a string.
     * Negative numbers and numbers ending in 0 (except 0 itself) are not palindromes.
     *
     * @param x the integer to check
     * @return true if x is a palindrome, false otherwise
     */
    public boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        // x == reversed handles even-length numbers;
        // x == reversed / 10 handles odd-length numbers (discard middle digit)
        return x == reversed || x == reversed / 10;
    }

    public static void main(String[] args) {
        Palindrome solution = new Palindrome();

        System.out.println("isPalindrome(121)  -> " + solution.isPalindrome(121));   // true
        System.out.println("isPalindrome(-121) -> " + solution.isPalindrome(-121));  // false
        System.out.println("isPalindrome(10)   -> " + solution.isPalindrome(10));    // false
        System.out.println("isPalindrome(0)    -> " + solution.isPalindrome(0));     // true
        System.out.println("isPalindrome(1221) -> " + solution.isPalindrome(1221));  // true
    }
}
