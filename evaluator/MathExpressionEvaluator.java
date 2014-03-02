package evaluator;

import java.util.Stack;

public class MathExpressionEvaluator
{
  public static double evaluateExpression(String expression)
  {
    Stack<Double> operandStack = new Stack<Double>();
    Stack<Character> operatorStack = new Stack<Character>();
    
    expression = insertBlanks(expression);
    
    String tokens[] = expression.split(" ");
    
    for (String token : tokens)
    {
      if (token.length() == 0)
        continue;
      else if (token.charAt(0) == '+' || token.charAt(0) == '-')
      {
        while (!operatorStack.isEmpty()
            && isValidOperator(operatorStack.peek()))
        {
          processAnOperator(operandStack, operatorStack);
        }
        
        operatorStack.push(token.charAt(0));
      }
      else if (token.charAt(0) == '*' || token.charAt(0) == '/')
      {
        while (!operatorStack.isEmpty()
            && (operatorStack.peek() == '*' || operatorStack.peek() == '/'))
        {
          processAnOperator(operandStack, operatorStack);
        }
        operatorStack.push(token.charAt(0));
      }
      else if (token.trim().charAt(0) == '(')
      {
        operatorStack.push('(');
      }
      else if (token.trim().charAt(0) == ')')
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
    
    while (!operatorStack.isEmpty())
    {
      processAnOperator(operandStack, operatorStack);
    }
    return operandStack.pop();
  }
  
  private static boolean isValidOperator(Character operator)
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
        operandStack.push(-operatorA);
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
    String result = "";
    for (int i = 0; i < s.length(); i++)
    {
      char c = s.charAt(i);
      
      if (c == '(' || c == ')' || c == '+' || c == '-' || c == '*' || c == '/')
        result += " " + s.charAt(i) + " ";
      else
        result += s.charAt(i);
    }
    return result;
  }
}