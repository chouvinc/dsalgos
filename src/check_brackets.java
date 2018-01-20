import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        int badPos = -1;
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code here
                opening_brackets_stack.push(new Bracket(next, position));
            }

            if (next == ')' || next == ']' || next == '}') {
                // Process closing bracket, write your code here
                // if there is an empty stack but we detect a closing bracket, get the position of the closing bracket
                if (opening_brackets_stack.empty() || !opening_brackets_stack.peek().Match(next)) {
                    badPos = position + 1;
                    break;
                } else {
                    opening_brackets_stack.pop();
                }
            }
        }

        // Printing answer, write your code here
        if (badPos != -1) {
            System.out.println(badPos);
        } else if (!opening_brackets_stack.empty()) {
            System.out.println(opening_brackets_stack.pop().position + 1);
        } else {
            System.out.println("Success");
        }
    }
}
