import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class QuadrilateralTest {
    @Test
    public void testQuadrilateral(){
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 2);
        Point p3 = new Point(2, 2);
        Point p4 = new Point(2, 0);

        Quadrilateral qua1 = new Quadrilateral(p1, p2, p3, p4);
        assertEquals(true, qua1.isRectangle());
        assertEquals(true, qua1.isSquare());

        Point p5 = new Point(0, 0);
        Point p6 = new Point(0, 2);
        Point p7 = new Point(3, 2);
        Point p8 = new Point(3, 0);

        Quadrilateral qua2 = new Quadrilateral(p5, p6, p7, p8);
        assertEquals(true, qua2.isRectangle());
        assertEquals(false, qua2.isSquare());
    }
}
