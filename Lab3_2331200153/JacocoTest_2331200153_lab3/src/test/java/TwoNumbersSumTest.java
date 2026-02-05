import static org.junit.Assert.assertEquals;
import org.example.TwoNumbersSum;
import org.junit.Test;
import java.util.*;

public class TwoNumbersSumTest {

    private ArrayList<Integer> createList(int... numbers) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : numbers) list.add(num);
        return list;
    }

    @Test
    public void testSimpleAddition() {
        TwoNumbersSum sum = new TwoNumbersSum();
        ArrayList<Integer> first = createList(3, 4);
        ArrayList<Integer> second = createList(5, 6);
        assertEquals(createList(9, 0), sum.addTwoNumbers(first, second));
    }

    @Test
    public void testAdditionWithCarry() {
        TwoNumbersSum sum = new TwoNumbersSum();
        ArrayList<Integer> first = createList(7, 9);
        ArrayList<Integer> second = createList(2, 3);
        assertEquals(createList(1, 0, 2), sum.addTwoNumbers(first, second));
    }

    @Test
    public void testCarryCreatesNewDigit() {
        TwoNumbersSum sum = new TwoNumbersSum();
        ArrayList<Integer> first = createList(9, 9, 9);
        ArrayList<Integer> second = createList(1);
        assertEquals(createList(1, 0, 0, 0), sum.addTwoNumbers(first, second));
    }

    @Test
    public void testFirstLonger() {
        TwoNumbersSum sum = new TwoNumbersSum();
        ArrayList<Integer> first = createList(5, 6, 7);
        ArrayList<Integer> second = createList(1, 2);
        assertEquals(createList(5, 7, 9), sum.addTwoNumbers(first, second));
    }

    @Test
    public void testSecondLonger() {
        TwoNumbersSum sum = new TwoNumbersSum();
        ArrayList<Integer> first = createList(5);
        ArrayList<Integer> second = createList(1, 2, 3);
        assertEquals(createList(1, 2, 8), sum.addTwoNumbers(first, second));
    }
}
