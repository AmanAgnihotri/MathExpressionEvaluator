package evaluator;

import java.util.Stack;

public class MathExpressionEvaluator
{
  public static double evaluateExpression(String expression)
  {
    Stack<Double> operandStack = new Stack<Double>();
    Stack<Character> operatorStack = new Stack<Character>();

    expression = insertBlanks(expression);

    String tokens[] = expression.split("\\s+");

    for (String token : tokens)
    {
      if (token.length() == 0)
      {
        continue;
      }
      else
      {
        double ch = token.charAt(0);
        if (ch == '+' || ch == '-')
        {
          while (!operatorStack.isEmpty()
            && isValidOperator(operatorStack.peek()))
          {
            processAnOperator(operandStack, operatorStack);
          }

          operatorStack.push(ch);
        }
        else if (ch == '*' || ch == '/')
        {
          while (!operatorStack.isEmpty()
            && (operatorStack.peek() == '*' || operatorStack.peek() == '/'))
          {
            processAnOperator(operandStack, operatorStack);
          }
          operatorStack.push(ch);
        }
        else if (ch == '(')
        {
          operatorStack.push('(');
        }
        else if (ch == ')')
        {
          while (operatorStack.peek() != '(')
          {
            processAnOperator(operandStack, operatorStack);
          }
          operatorStack.pop();
        }
        else
        {
          operandStack.push(new Double(token));
        }
      }
    }

    while (!operatorStack.isEmpty())
    {
      processAnOperator(operandStack, operatorStack);
    }
    return operandStack.pop();
  }

  private static boolean isValidOperator(char operator)
  {
    return operator == '+' || operator == '-' || operator == '*'
      || operator == '/';
  }

  private static void processAnOperator(Stack<Double> operandStack,
                                        Stack<Character> operatorStack)
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