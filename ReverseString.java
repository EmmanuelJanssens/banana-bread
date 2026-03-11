public class ReverseString {

    /**
     * Reverses a character array in-place using two-pointer swap.
     *
     * @param s the character array to reverse
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        ReverseString solution = new ReverseString();

        char[] s1 = {'h', 'e', 'l', 'l', 'o'};
        solution.reverseString(s1);
        System.out.println("reverseString(['h','e','l','l','o']) -> " + new String(s1)); // olleh

        char[] s2 = {'H', 'a', 'n', 'n', 'a', 'h'};
        solution.reverseString(s2);
        System.out.println("reverseString(['H','a','n','n','a','h']) -> " + new String(s2)); // hannaH
    }
}
