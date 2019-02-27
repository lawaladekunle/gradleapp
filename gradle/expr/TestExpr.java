package expr;

import java.io.IOException;
import org.junit.*;
import static org.junit.Assert.*;

public class TestExpr {
    
    private Environment env;
    
    @Before
	public void setUp() {
        this.env = new Environment() {
            public double value(String name) {
                if (name.equals("A3"))
                    return 1;
                if (name.equals("A2"))
                    return 2;
                if (name.equals("A1"))
                    return 3;
                System.out.println(name + " is undefined");
                return 0;
            }
        };
    }
    
    @Test
    public void testExpressions() {
        ExprParser parser = new ExprParser();
        try {
            Expr expr = parser.build("1 + (2 * 3)");
            assertEquals("1.0+2.0*3.0", expr.toString());
            assertEquals(7.0, expr.value(null), 0.0);
            
            expr = parser.build("(A3+A2) * A1");
            assertEquals("(A3+A2)*A1", expr.toString());
            assertEquals(9.0, expr.value(env), 0.0);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
}