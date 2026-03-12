import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class IntersectionTest {

    private Intersection intersection;

    @Before
    public void setUp() {
        intersection = new Intersection();
    }

    //equivalence partition testcase

    // EC1: Parallel Lines (m1 == m2, c1 != c2)
    @Test
    public void testParallelLines() {
        String result = intersection.findIntersection(2, 2, 3, 5);
        assertEquals("These are parallel lines. No Intersection between the lines.", result);
    }

    @Test
    public void testParallelLinesNegativeSlope() {
        String result = intersection.findIntersection(-3, -3, 1, 4);
        assertEquals("These are parallel lines. No Intersection between the lines.", result);
    }

    // EC2: Coincidental Lines (m1 == m2, c1 == c2)
    @Test
    public void testCoincidentalLines() {
        String result = intersection.findIntersection(3, 3, 4, 4);
        assertEquals("These are coincidental lines.No Intersection between the lines\n", result);
    }

    @Test
    public void testCoincidentalLinesZeroSlope() {
        String result = intersection.findIntersection(0, 0, 5, 5);
        assertEquals("These are coincidental lines.No Intersection between the lines\n", result);
    }

    // EC3: Intersecting Lines (m1 != m2)
    @Test
    public void testIntersectingLines() {
        String result = intersection.findIntersection(2, 3, 1, 2);
        assertTrue(result.startsWith("Intersecting Point:"));
    }

    @Test
    public void testIntersectingLinesPositiveNegativeSlope() {
        String result = intersection.findIntersection(1, -1, 0, 4);
        assertTrue(result.startsWith("Intersecting Point:"));
    }

    @Test
    public void testIntersectingLinesWithZeroSlope() {
        String result = intersection.findIntersection(0, 1, 5, 2);
        assertTrue(result.startsWith("Intersecting Point:"));
    }

    @Test
    public void testIntersectingLinesDecimalValues() {
        String result = intersection.findIntersection(1.5f, 2.5f, 3, 1);
        assertTrue(result.startsWith("Intersecting Point:"));
    }

    @Test
    public void testIntersectingLinesNegativeIntercepts() {
        String result = intersection.findIntersection(2, 4, -3, -5);
        assertTrue(result.startsWith("Intersecting Point:"));
    }
}