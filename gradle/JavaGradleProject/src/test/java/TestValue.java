package expr;

import org.junit.*;
import static org.junit.Assert.*;

public class TestValue {
    
    private Environment env;
    private Expr n1, n2, n3, n4;
    private Expr v1, v2, v3, v4;
    
    @Before
	public void setUp() {
        this.env = new Environment() {
            public double value(String name) {
                if (name.equals("foo"))
                    return 3;
                if (name.equals("bar"))
                    return 2;
                if (name.equals("chuck"))
                    return 19;
                if (name.equals("magic"))
                    return 42;
                throw new IllegalArgumentException("'" + name + "' is undefined.");
            }
        };
        this.n1 = new Num(0);
        this.n2 = new Num(1);
        this.n3 = new Num(5);
        this.n4 = new Num(11);
        
        this.v1 = new Variable("foo");
        this.v2 = new Variable("bar");
        this.v3 = new Variable("chuck");
        this.v4 = new Variable("magic");
    }
    
    @Test
    public void testAdd() {
        Expr e = new Add(n1, n2);
        assertEquals(1.0, e.value(null), 0.0);
        
        e = new Add(e, e);
        assertEquals(2.0, e.value(null), 0.0);
        
        e = new Add(e, n3);
        assertEquals(7.0, e.value(null), 0.0);
    }
    
    @Test
    public void testSub() {
        Expr e = new Sub(n3, n2);
        assertEquals(4.0, e.value(null), 0.0);
        
        e = new Sub(e, e);
        assertEquals(0.0, e.value(null), 0.0);
    }
    
    @Test
    public void testMul() {
        Expr e = new Mul(n2, n3);
        assertEquals(5.0, e.value(null), 0.0);
        
        e = new Mul(e, e);
        assertEquals(25.0, e.value(null), 0.0);
        
        e = new Mul(e, n1);
        assertEquals(0.0, e.value(null), 0.0);
    }
    
    @Test
    public void testDiv() {
        Expr e = new Div(n4, n3);
        assertEquals(2.2, e.value(null), 0.000001);
        
        e = new Div(e, e);
        assertEquals(1.0, e.value(null), 0.000001);
    }

//------------------------------------------------------------------------------
//    Uncomment as part of 4.3
//------------------------------------------------------------------------------
//    @Test(expected=IllegalArgumentException.class)
//    public void testDivByZero() {
//        Expr e = new Div(n2, n1);
//        e.value(null);
//        fail("Division by zero did not throw exception.");
//    }



//------------------------------------------------------------------------------
//    Uncomment as part of 4.4.1
//------------------------------------------------------------------------------
//    @Test
//    public void testMod() {
//        Expr e = new Mod(n4, n3);
//        assertEquals(1.0, e.value(null), 0.000001);
//        
//        e = new Mod(e, e);
//        assertEquals(0.0, e.value(null), 0.000001);
//    }
//    
//    @Test(expected=IllegalArgumentException.class)
//    public void testModByZero() {
//        Expr e = new Mod(n2, n1);
//        e.value(null);
//        fail("Division by zero did not throw exception.");
//    }
    
    @Test
    public void testNum() {
        assertEquals(0.0, n1.value(null), 0.0);
        assertEquals(1.0, n2.value(null), 0.0);
        assertEquals(5.0, n3.value(null), 0.0);
        assertEquals(11.0, n4.value(null), 0.0);
    }
    
    @Test
    public void testVariable() {
        assertEquals(3.0, v1.value(env), 0.0);
        assertEquals(2.0, v2.value(env), 0.0);
        assertEquals(19.0, v3.value(env), 0.0);
        assertEquals(42.0, v4.value(env), 0.0);
    }    
}