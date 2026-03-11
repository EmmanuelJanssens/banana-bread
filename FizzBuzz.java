import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {

    /**
     * Returns a list of strings for numbers 1 through n applying the FizzBuzz rules:
     * - "FizzBuzz" for multiples of both 3 and 5
     * - "Fizz" for multiples of 3
     * - "Buzz" for multiples of 5
     * - The number itself (as a string) otherwise
     *
     * @param n the upper bound (inclusive)
     * @return list of FizzBuzz strings
     */
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                result.add("FizzBuzz");
            } else if (i % 3 == 0) {
                result.add("Fizz");
            } else if (i % 5 == 0) {
                result.add("Buzz");
            } else {
                result.add(String.valueOf(i));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FizzBuzz solution = new FizzBuzz();

        System.out.println("fizzBuzz(15) -> " + solution.fizzBuzz(15));
        // [1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz]
    }
}
