import org.junit.Test;
import static org.junit.Assert.*;

public class TriangleClassifyTest {
    private TriangleClassify test = new TriangleClassify();
    private String classify(int s1, int s2, int s3) {
        test.setSide(s1, s2, s3);
        return test.classify();
    }

    //all side minimum boundary
    @Test
    public void testAllMin() {
        assertEquals("EQUILATERAL", classify(2, 2, 2));
    }

    //all side maximum boundary
    @Test
    public void testAllMax() {
        assertEquals("EQUILATERAL", classify(10, 10, 10));
    }

    //all side nominal value
    @Test
    public void testAllNominal() {
        assertEquals("EQUILATERAL", classify(6, 6, 6));
    }

    //s1 min, s2 s3 nominal -> s3 = s2 -> isoleces
    @Test
    public void testS1Min() {
        assertEquals("ISOSCELES", classify(2, 6, 6));
    }

    //s1 min+1,s2 s3 nominal -> s3 = s2 -> isoleces
    @Test
    public void testS1MinPlus1() {
        assertEquals("ISOSCELES", classify(3, 6, 6));
    }

    //s1 max,s2 s3 nominal -> s3 = s2 -> isoleces
    @Test
    public void testS1Max() {
        assertEquals("ISOSCELES", classify(10, 6, 6));
    }

    //s1 max-1,s2 s3 nominal -> s3 = s2 -> isoleces
    @Test
    public void testS1MaxMinus1() {
        assertEquals("ISOSCELES", classify(9, 6, 6));
    }

    //s2 min,s1 s3 nominal -> s1 = s3 -> isoleces
    @Test
    public void testS2Min() {
        assertEquals("ISOSCELES", classify(6, 2, 6));
    }

    //s3 min,s2 s3 nominal -> s2 = s1 -> isoleces
    @Test
    public void testS3Min() {
        assertEquals("ISOSCELES", classify(6, 6, 2));
    }

    //s2 max,s1 s3 nominal -> s1 = s3 -> isoleces
    @Test
    public void testS2Max() {
        assertEquals("ISOSCELES", classify(6, 10, 6));
    }

    //s3 max,s1 s2 nominal -> s1 = s2 -> isoleces
    @Test
    public void testS3Max() {
        assertEquals("ISOSCELES", classify(6, 6, 10));
    }

    //equilateral at nom
    @Test
    public void testEquilateral() {
        assertEquals("EQUILATERAL", classify(5, 5, 5));
    }

    //iso
    @Test
    public void testIsosceles() {
        assertEquals("ISOSCELES", classify(5, 5, 6));
    }

    //scalene
    @Test
    public void testScalene() {
        assertEquals("SCALENE", classify(3, 4, 5));
    }

    //not triangle
    @Test
    public void testNotATriangle() {
        assertEquals("NOT_A_TRIANGLE", classify(2, 2, 10));
    }

    //out of range
    @Test
    public void testS1OutOfRangeLow() {
        assertEquals("OUT_OF_RANGE", classify(1, 6, 6));
    }
}