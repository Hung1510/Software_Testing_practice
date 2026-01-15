import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testAdd_Bug() {
        // Arrange: chuẩn bị dữ liệu & đối tượng test
        Calculator calculator = new Calculator();
        int a = 3; // dữ liệu test chuẩn bị
        int b = 5; // dữ liệu test chuẩn bị
        int expectedResult = 8; // kết quả mong đợi

        // Act: gọi hàm cần test
        int actualResult = calculator.add(a, b); // kết quả thực tế khi gọi hàm

        // Assert: so sánh kết quả mong đợi và kết quả thực tế
        assertEquals(
                expectedResult,
                actualResult,
                "add(3, 5) phải trả về 8"
        );
    }

 // Khi đã rành thì mình viết code ngắn gọn hơn cho hàm test như sau.
    @Test
    void testAdd() {
        Calculator calculator = new Calculator();
        assertEquals(8, calculator.add(3, 5));
    }


    @Test
    void testAddWithNegativeNumber() {
        // Arrange
        Calculator calculator = new Calculator();
        int a = 5;
        int b = -3;
        int expectedResult = 2;

        // Act
        int actualResult = calculator.add(a, b);

        // Assert
        assertEquals(
                expectedResult,
                actualResult,
                "add(5, -3) phải trả về 2"
        );
    }

    @Test
    void testDivide() {
        // Arrange
        Calculator calculator = new Calculator();
        int a = 8;
        int b = 2;
        int expectedResult = 4;

        // Act
        int actualResult = calculator.divide(a, b);

        // Assert
        assertEquals(
                expectedResult,
                actualResult,
                "divide(8, 2) phải trả về 4"
        );
    }

    @Test
    void testDivideByZero() {
        // Arrange
        Calculator calculator = new Calculator();
        int a = 10;
        int b = 0;

        // Act & Assert: kiểm tra có ném đúng exception hay không
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.divide(a, b),
                "Chia cho 0 phải ném IllegalArgumentException"
        );

        // Assert thêm message của exception (tường minh hơn)
        assertEquals(
                "Cannot divide by zero",
                exception.getMessage(),
                "Thông báo lỗi không đúng"
        );
    }
}
