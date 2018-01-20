import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by chouvinc on 11/20/2017.
 */
public class Stack {
    public boolean isValid(String s) {
        if (s.length() == 0) {
            return true;
        }

        // stack implementation
        Deque<Character> stack = new ArrayDeque<>();

        stack.add(s.charAt(0));
        for (int i=1; i<s.length(); i++) {
            char bracket = s.charAt(i);

            if (bracket == ')' && stack.getLast() == ')') {
                stack.pop();
            } else if (bracket == '}' && stack.getLast() == '}') {
                stack.pop();
            } else if (bracket == ']' && stack.getLast() == ']') {
                stack.pop();
            }

            stack.add(s.charAt(i));
        }

        if (stack.size() > 0) {
            return false;
        } else {
            return true;
        }
    }
}
