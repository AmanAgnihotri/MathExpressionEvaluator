import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class EvaluatorTest {
    public EvaluatorTest() {
    }

    @Test
    public void testExpressions() {
        double expVal = 3.0;
        double resVal = MathExpressionEvaluator.evaluateExpression("-4 + 7");
        assertEquals(expVal, resVal, 0.0);

        expVal = -8.0;
        resVal = MathExpressionEvaluator.evaluateExpression("-1 + 3 -(4 * 3 - 3 + (3 / 3))");
        assertEquals(expVal, resVal, 0.0);

        expVal = 0.0;
        resVal = MathExpressionEvaluator.evaluateExpression("-");
        System.out.format("%d", resVal);
    }
}
