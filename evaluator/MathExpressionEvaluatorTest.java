package evaluator;

import org.junit.Test;

import static evaluator.MathExpressionEvaluator.evaluateExpression;
import static junit.framework.Assert.assertEquals;

public class MathExpressionEvaluatorTest
{
  @Test
  public void testEvaluateExpression() throws Exception
  {
    assertEquals(3.0, evaluateExpression("-4 + 3 + (2 * 2)"));
  }
}
