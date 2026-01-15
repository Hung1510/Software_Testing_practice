import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorBugTest {

    @Test
    void testAdd() {
        // Arrange
        CalculatorBug calculator = new CalculatorBug();
        int a = 3;
        int b = 5;
        int expectedResult = 8;

        // Act
        int actualResult = calculator.add(a, b);

        // Assert
        assertEquals(
                expectedResult,
                actualResult,
                "BUG: add(3, 5) phải trả về 8"
        );

    }

    @Test
    void testAddCase2() {
        // Arrange
        CalculatorBug calculator = new CalculatorBug();
        assertEquals(8,calculator.add(4, 4),"Kết quả mong muốn của 4+4 là 8");
    }

}
