import static org.junit.jupiter.api.Assertions.*;

import org.example.TwoNumbersSum;
import org.junit.jupiter.api.Test;
import java.util.*;


public class TwoNumbersSumTest {
    TwoNumbersSum solver = new TwoNumbersSum();

    private ArrayList<Integer> list(int... nums) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int n : nums) res.add(n);
        return res;
    }
    @Test
    public void testNoCarrySameLength() {
        ArrayList<Integer> a = list(1, 2);
        ArrayList<Integer> b = list(2, 3);

        ArrayList<Integer> result = solver.addTwoNumbers(a, b);

        assertEquals(list(3, 5), result);
    }

    @Test
    public void testCarryInMiddle() {
        ArrayList<Integer> a = list(5, 8);
        ArrayList<Integer> b = list(1, 4);

        ArrayList<Integer> result = solver.addTwoNumbers(a, b);

        assertEquals(list(7, 2), result);
    }

    @Test
    public void testCarryAtEndIncreaseLength() {
        ArrayList<Integer> a = list(9, 9);
        ArrayList<Integer> b = list(1);

        ArrayList<Integer> result = solver.addTwoNumbers(a, b);

        assertEquals(list(1, 0, 0), result);
    }

    @Test
    public void testDifferentLengthNoCarry() {
        ArrayList<Integer> a = list(1, 2, 3);
        ArrayList<Integer> b = list(4, 5);

        ArrayList<Integer> result = solver.addTwoNumbers(a, b);

        assertEquals(list(1, 6, 8), result);
    }

}
