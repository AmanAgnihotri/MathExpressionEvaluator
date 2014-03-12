package evaluator;

import java.util.LinkedList;

public class MathExpressionEvaluator
{
  public static double evaluateExpression(String expression)
  {
    LinkedList<Double> operands = new LinkedList<Double>();
    LinkedList<Character> operators = new LinkedList<Character>();

    expression = insertBlanks(expression);

    String tokens[] = expression.split("\\s+");

    for (String token : tokens)
    {
      if (token.length() == 0)
      {
        continue;
      }

      char ch = token.charAt(0);
      if (ch == '+' || ch == '-')
      {
        while (!operators.isEmpty()
          && isValidOperator(operators.peek()))
        {
          processAnOperator(operands, operators);
        }

        operators.push(ch);
      }
      else if (ch == '*' || ch == '/')
      {
        while (!operators.isEmpty()
          && (operators.peek() == '*' || operators.peek() == '/'))
        {
          processAnOperator(operands, operators);
        }
        operators.push(ch);
      }
      else if (ch == '(')
      {
        operators.push('(');
      }
      else if (ch == ')')
      {
        while (operators.peek() != '(')
        {
          processAnOperator(operands, operators);
        }
        operators.pop();
      }
      else
      {
        operands.push(new Double(token));
      }
    }

    while (!operators.isEmpty())
    {
      processAnOperator(operands, operators);
    }
    return operands.pop();
  }

  private static boolean isValidOperator(char operator)
  {
    return operator == '+' || operator == '-' || operator == '*'
      || operator == '/';
  }

  private static void processAnOperator(LinkedList<Double> operandStack,
                                        LinkedList<Character> operatorStack)
  {
    char operand = operatorStack.pop();
    double operatorA = operandStack.pop();

    if (operandStack.isEmpty())
    {
      if (operand == '-')
      {
        operandStack.push(-operatorA);
      }
      return;
    }

    double operatorB = operandStack.pop();

    switch (operand)
    {
      case '+':
        operandStack.push(operatorB + operatorA);
        break;
      case '-':
        operandStack.push(operatorB - operatorA);
        break;
      case '*':
        operandStack.push(operatorB * operatorA);
        break;
      case '/':
        operandStack.push(operatorB / operatorA);
        break;
    }
  }

  private static String insertBlanks(String s)
  {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < s.length(); i++)
    {
      char c = s.charAt(i);

      if (c == '(' || c == ')' || c == '+' || c == '-' || c == '*' || c == '/')
      {
        result.append(" ").append(c).append(" ");
      }
      else
      {
        result.append(c);
      }
    }
    return result.toString();
  }
}
