package Class;

public class FizzBuzz {

    public String fizz_buzz(int i) {
        // wrong upper bound check, off by 1, should be <=
        //if (i < 35 && i >= 0) {
        if (i <= 35 && i >= 0) {

            if (i % 3 == 0 && i % 5 == 0) {
                return "Fizz!Buzz!";
            } else if (i % 3 == 0) {
                return "Fizz";
            } else if (i % 5 == 0) {
                return "Buzz";
            } else {
                return Integer.toString(i);
            }
        } else
            return "Wrong Input";

    }

}
 