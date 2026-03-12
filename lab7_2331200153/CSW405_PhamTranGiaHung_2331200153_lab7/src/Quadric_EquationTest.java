import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class Quadric_EquationTest {

    private Quadric_Equation quadratic;

    @Before
    public void setUp() {
        quadratic = new Quadric_Equation();
    }

    //equivalence partition testcase

    // EC1: Invalid Input (a < 0)
    @Test
    public void testInvalidInput_NegativeA() {
        assertEquals("Invalid input", quadratic.calculateroot(-1, 5, 5));
    }

    // EC1: Invalid Input (b < 0)
    @Test
    public void testInvalidInput_NegativeB() {
        assertEquals("Invalid input", quadratic.calculateroot(5, -1, 5));
    }

    // EC1: Invalid Input (c < 0)
    @Test
    public void testInvalidInput_NegativeC() {
        assertEquals("Invalid input", quadratic.calculateroot(5, 5, -1));
    }

    // EC1: Invalid Input (a > 100)
    @Test
    public void testInvalidInput_AGreaterThan100() {
        assertEquals("Invalid input", quadratic.calculateroot(101, 5, 5));
    }

    // EC1: Invalid Input (b > 100)
    @Test
    public void testInvalidInput_BGreaterThan100() {
        assertEquals("Invalid input", quadratic.calculateroot(5, 101, 5));
    }

    // EC1: Invalid Input (c > 100)
    @Test
    public void testInvalidInput_CGreaterThan100() {
        assertEquals("Invalid input", quadratic.calculateroot(5, 5, 101));
    }

    // EC2: Not a quadratic equation (a == 0)
    @Test
    public void testNotQuadraticEquation() {
        assertEquals("Not a quadratic equation", quadratic.calculateroot(0, 5, 5));
    }

    // EC3: Equal Roots (discriminant == 0)
    //equal roots: b^2 - 4ac = 0
    //a=1, b=2, c=1 -> 4 - 4 = 0
    @Test
    public void testEqualRoots() {
        assertEquals("Roots are equal", quadratic.calculateroot(1, 2, 1));
    }

    //EC4: Imaginary Roots(discriminant < 0)
    //a=1, b=1, c=1 -> 1 - 4 = -3
    @Test
    public void testImaginaryRoots() {
        assertEquals("Imaginary roots", quadratic.calculateroot(1, 1, 1));
    }

    // EC5: Real Roots(discriminant > 0)
    //a=1, b=5, c=1 -> 25 - 4 = 21
    @Test
    public void testRealRoots() {
        assertEquals("Real Roots", quadratic.calculateroot(1, 5, 1));
    }

    //BVA testcase
    // BVA for 'a' parameter
    @Test
    public void testBVA_A_AtLowerBoundary() {
        // a = 0 (lower boundary)
        assertEquals("Not a quadratic equation", quadratic.calculateroot(0, 50, 50));
    }

    @Test
    public void testBVA_A_JustAboveLowerBoundary() {
        // a = 1 (just above lower boundary)
        assertEquals("Real Roots", quadratic.calculateroot(1, 50, 50));
    }

    @Test
    public void testBVA_A_AtUpperBoundary() {
        // a = 100 (upper)
        // discriminant = 50^2 - 4*100*50 = 2500 - 20000 = -17500 <
        assertEquals("Imaginary roots", quadratic.calculateroot(100, 50, 50));
    }

    @Test
    public void testBVA_A_JustAboveUpperBoundary() {
        // a = 101 (just above upper boundary)
        assertEquals("Invalid input", quadratic.calculateroot(101, 50, 50));
    }

    @Test
    public void testBVA_A_BelowLowerBoundary() {
        // a = -1(below lower boundary)
        assertEquals("Invalid input", quadratic.calculateroot(-1, 50, 50));
    }

    // BVA for 'b' parameter
    @Test
    public void testBVA_B_AtLowerBoundary() {
        // b = 0(lower)
        assertEquals("Imaginary roots", quadratic.calculateroot(50, 0, 50));
    }


    @Test
    public void testBVA_B_AtUpperBoundary() {
        // b = 100 (upper)
        // discriminant = 100^2 - 4*50*50 = 10000 - 10000 = 0
        assertEquals("Roots are equal", quadratic.calculateroot(50, 100, 50));
    }

    @Test
    public void testBVA_B_JustAboveUpperBoundary() {
        // b = 101 (just above upper boundary)
        assertEquals("Invalid input", quadratic.calculateroot(50, 101, 50));
    }

    @Test
    public void testBVA_B_BelowLowerBoundary() {
        // b = -1 (below lower boundary)
        assertEquals("Invalid input", quadratic.calculateroot(50, -1, 50));
    }

    // BVA for 'c' parameter
    @Test
    public void testBVA_C_AtLowerBoundary() {
        // c = 0 (lower boundary)
        assertEquals("Real Roots", quadratic.calculateroot(50, 50, 0));
    }

    @Test
    public void testBVA_C_AtUpperBoundary() {
        // c = 100 (upper boundary)
        assertEquals("Imaginary roots", quadratic.calculateroot(50, 50, 100));
    }

    @Test
    public void testBVA_C_JustAboveUpperBoundary() {
        // c = 101 (just above upper boundary)
        assertEquals("Invalid input", quadratic.calculateroot(50, 50, 101));
    }

    @Test
    public void testBVA_C_BelowLowerBoundary() {
        // c = -1 (below lower boundary)
        assertEquals("Invalid input", quadratic.calculateroot(50, 50, -1));
    }
}