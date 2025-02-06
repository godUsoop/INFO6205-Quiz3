import java.util.Stack;


/*
This class implements a two-stack algorithm for evaluating arithmetic expressions.

Key components:
1. Two stacks: 
   - ops: for storing operators
   - vals: for storing numeric values

2. The evaluate method:
   - Takes a string input representing an arithmetic expression
   - Returns the result as a double

Algorithm steps:
1. Split the input string into tokens (numbers, operators, parentheses)
2. Iterate through each token:
   - If it's an opening parenthesis '(', ignore it
   - If it's an operator (+, -, *, /), push onto ops stack
   - If it's a closing parenthesis ')', perform the calculation:
     a. Pop the top operator from ops
     b. Pop the required number of values from vals
     c. Perform the operation
     d. Push the result back onto vals
   - If it's a number, parse it to double and push onto vals stack

3. After processing all tokens, the final result will be the only item left on vals stack

Important considerations:
- Use .equals() for string comparisons, not ==
- Be careful with the order of operands when popping for binary operations
- Consider adding checks for empty stacks to prevent errors

This implementation provides O(n) time complexity and effectively handles 
operator precedence through the use of parentheses in the input expression.
*/

public class TwoStack {
    Stack<String> ops  = new Stack<String>();
    Stack<Double> vals = new Stack<Double>();
    public double evaluate(String s){
        String[] tokens = s.split(" ");
        for (String str: tokens) {
            if (str.equals("(")) {
                continue;
            } else if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
                ops.push(str);
            } else if (str.equals(")")) {
                String operator = ops.pop();
                Double val2 = vals.pop();
                Double val1 = vals.pop();
                Double sum = 0.0;
                if (operator.equals("+")) {
                    sum = val1 + val2;
                } else if (operator.equals("-")) {
                    sum = val1 - val2;
                } else if (operator.equals("*")) {
                    sum = val1 * val2;
                } else {
                    sum = val1 / val2;
                }
                vals.push(sum);

            } else if (Integer.parseInt(str) >= 0 || Integer.parseInt(str) <= 9) {
                vals.push((double)Integer.parseInt(str));
            }
        }

        return vals.pop();
    }
}