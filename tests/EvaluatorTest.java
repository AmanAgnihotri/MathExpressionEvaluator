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
        assertEquals(resVal, expVal, 0.0);
    }
}
