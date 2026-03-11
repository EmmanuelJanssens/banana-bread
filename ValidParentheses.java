import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParentheses {

    /**
     * Determines if the input string containing only '(', ')', '{', '}', '[', ']'
     * is valid. A string is valid if every opening bracket is closed by the
     * corresponding closing bracket in the correct order.
     *
     * @param s the input string of bracket characters
     * @return true if the brackets are valid, false otherwise
     */
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses solution = new ValidParentheses();

        System.out.println("isValid(\"()\")     -> " + solution.isValid("()"));      // true
        System.out.println("isValid(\"()[]{}\") -> " + solution.isValid("()[]{}")); // true
        System.out.println("isValid(\"(]\")     -> " + solution.isValid("(]"));      // false
        System.out.println("isValid(\"{[]}\")   -> " + solution.isValid("{[]}")); // true
        System.out.println("isValid(\"]\" )     -> " + solution.isValid("]"));       // false
    }
}
