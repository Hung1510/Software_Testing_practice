import java.util.Scanner;

public class CircleRadius {
    public static void main(String args[]) {
        double r, area, circum;
        Scanner reader = new Scanner(System.in);
        try {
            System.out.println("Enter the radius ");
            r = reader.nextDouble();
            if (r >= 0) {
                circum = 2 * Math.PI * r;
                area = Math.PI * Math.pow(r, 2);
                System.out.println("For a circle with radius " + r + ",");
                System.out.println("The circumference is " + circum);
                System.out.println("and the area is " + area + ".");
                reader.close();
            } else
                System.out.println("You have entered wrong value.");
            // bug fix: add exception handling for input like "abc"
            // the code didnt have input reader
        } catch (Exception e) {
            System.out.println("You have entered wrong value.");
        }
    }
}
