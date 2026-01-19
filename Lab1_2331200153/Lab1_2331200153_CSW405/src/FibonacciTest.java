import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class FibonacciTest {
    @Test
    public void testFibonacci() {
        Fibonacci fi = new Fibonacci();
        assertEquals(2, fi.fib(3));
        assertEquals(8, fi.fib(6));
        assertEquals(34, fi.fib(9));
    }
}
