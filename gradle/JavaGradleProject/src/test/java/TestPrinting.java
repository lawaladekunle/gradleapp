package expr;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestPrinting {
    
    private Environment env;
    private Expr n1, n2, n3, n4;
    private Expr v1, v2, v3, v4;
    
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
        assertEquals("0.0+1.0", e.toString());
        
        e = new Add(e, e);
        assertEquals("0.0+1.0+0.0+1.0", e.toString());
        
        e = new Add(e, n3);
        assertEquals("0.0+1.0+0.0+1.0+5.0", e.toString());
    }
        
    @Test
    public void testSub() {
        Expr e = new Sub(n4, n3);
        assertEquals("11.0-5.0", e.toString());
        
        e = new Sub(e, e);
        assertEquals("11.0-5.0-(11.0-5.0)", e.toString());
    }
        
    @Test
    public void testMul() {
        Expr e = new Mul(n2, n3);
        assertEquals("1.0*5.0", e.toString());
        
        e = new Mul(e, e);
        assertEquals("1.0*5.0*1.0*5.0", e.toString());
        
        e = new Mul(e, n1);
        assertEquals("1.0*5.0*1.0*5.0*0.0", e.toString());
    }
    
    @Test
    public void testDiv() {
        Expr e = new Div(n4, n3);
        assertEquals("11.0/5.0", e.toString());
        
        e = new Div(e, e);
        assertEquals("11.0/5.0/(11.0/5.0)", e.toString());
    }

//------------------------------------------------------------------------------
//    Uncomment as part of 4.4.1
//------------------------------------------------------------------------------
//    @Test
//    public void testMod() {
//    	Expr e = new Mod(n4, n3);
//    	assertEquals("11.0%5.0", e.toString());
//    	
//    	e = new Mod(e, e);
//    	assertEquals("11.0%5.0%(11.0%5.0)", e.toString());
//    }
    
    @Test
    public void testNum() {
        assertEquals("0.0", n1.toString());
        assertEquals("1.0", n2.toString());
        assertEquals("5.0", n3.toString());
        assertEquals("11.0", n4.toString());
    }
    
    @Test
    public void testVariable() {
        assertEquals("foo", v1.toString());
        assertEquals("bar", v2.toString());
        assertEquals("chuck", v3.toString());
        assertEquals("magic", v4.toString());
    }  
    
    
}