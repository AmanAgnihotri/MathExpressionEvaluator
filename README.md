## Math Expression Evaluator

A simple Math expression evaluator which can evaluate expressions which include parenthesis and simple arithmetic operators.

### Usage

To use the Evaluator, simply use the class in your project and call the static method `evaluateExpression` and pass it the expression.
It returns a `double` value which is the value of the evaluated expression.

```java
// Evaluate ((4 * 3) / (2 * 4))
double value = MathExpressionEvaluator.evaluateExpression("((4 * 3) / (2 * 4))");

// Print the value
System.out.println(value);
```