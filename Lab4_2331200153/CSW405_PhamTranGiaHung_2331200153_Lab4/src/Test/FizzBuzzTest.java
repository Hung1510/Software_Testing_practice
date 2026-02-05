package Test;

import org.junit.Test;
import static org.junit.Assert.*;
import Class.FizzBuzz;

public class FizzBuzzTest {
    @Test
    public void testFizz() {
        FizzBuzz fb = new FizzBuzz();
        assertEquals("Fizz", fb.fizz_buzz(33));
    }

    @Test
    public void testFizzBuzz() {
        FizzBuzz fb = new FizzBuzz();
        assertEquals("Fizz!Buzz!", fb.fizz_buzz(15));
    }

    @Test
    public void testBuzz() {
        FizzBuzz fb = new FizzBuzz();
        assertEquals("Buzz", fb.fizz_buzz(35));
    }

    @Test
    public void testZero() {
        FizzBuzz fb = new FizzBuzz();
        assertEquals("Fizz!Buzz!", fb.fizz_buzz(0));
    }

    @Test
    public void testNormalNumber() {
        FizzBuzz fb = new FizzBuzz();
        assertEquals("7", fb.fizz_buzz(7));
    }

    @Test
    public void testWrongInput_Negative() {
        FizzBuzz fb = new FizzBuzz();
        assertEquals("Wrong Input", fb.fizz_buzz(-1));
    }

    @Test
    public void testWrongInput_TooLarge() {
        FizzBuzz fb = new FizzBuzz();
        assertEquals("Wrong Input", fb.fizz_buzz(36));
    }

    @Test
    public void testWrongInput_VeryLarge() {
        FizzBuzz fb = new FizzBuzz();
        assertEquals("Wrong Input", fb.fizz_buzz(100));
    }

    @Test
    public void testWrongInput_VeryNegative() {
        FizzBuzz fb = new FizzBuzz();
        assertEquals("Wrong Input", fb.fizz_buzz(-100));
    }
}
