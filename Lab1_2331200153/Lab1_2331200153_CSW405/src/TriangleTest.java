import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TriangleTest {
    @Test
    public void testTriangle() {
       Triangle tri = new Triangle();
       assertEquals(true, tri.isTriangle(3, 4, 5));
       assertEquals(false, tri.isTriangle(1, 3, 5));
       assertEquals(false, tri.isTriangle(6, 3, 2));
       assertEquals(false, tri.isTriangle(2, 10, 4));
       assertEquals(false, tri.isTriangle(1, 4, 5));
       assertEquals(false, tri.isTriangle(6, 3, 3));
       assertEquals(false, tri.isTriangle(6, 10, 4));
    }
}
