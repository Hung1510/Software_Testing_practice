package basicExample;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AssertionsExample {

@Test
void testAssertEqual() {
	 assertEquals("ABC", "ABC");// Fails when expected value does not equal actual value. First argument is expected value, second argument is actual value.
	 assertEquals(20, 20, "optional assertion message"); // Fails when expected value does not equal actual value. First argument is expected value, second argument is actual value, third argument is message that will be displayed at the time of failure.
	 assertEquals(2 + 2, 4);
}

@Test
void testAssertFalse() {
	 assertFalse("FirstName".length() == 10); //Fails when expression is true. The argument is expression.
	 assertFalse(10 > 20, "assertion message");//First argument is expression, second argument is message that will be displayed at the time of failure.
}

@Test
void testAssertNull() {
     String str1 = null;
	 String str2 = "abc";
	 assertNull(str1);//Fails when the argument(the actual value) is not null
	 assertNotNull(str2); //Fails when the argument (the actual value) is null	
}

@Test
void testAssertAll() {
	 String str1 = "abc";
	 String str2 = "pqr";
	 String str3 = "xyz";
	 
	 assertAll("result",
			 ()-> assertEquals(str1,"abc"),
		  ()-> assertEquals(str2,"xyz"),
		  ()-> assertEquals(str3,"abc","xyz is the exepected output"));
	 //Group many assertions and every assertion is executed even if one or more of them fails
	
     assertAll("numbers",
		  () -> assertEquals(str1,"abc"),
		  () -> assertEquals(str2,"pqr1"),
		  () -> assertEquals(str3,"xyz1")
	 );
}

@Test
void testAssertTrue() {
	 assertTrue("FirstName".startsWith("F")); //Fails if expression is false
	 
}
@Test
void testAssertArray() {
	 int[] A=new int[] {32,45,67};
	 int[] B=new int[] {32,45,67};
	 int[] C=new int[] {23,45,67};
	 assertArrayEquals(A,B); //passes when the arguments are equals
	assertArrayEquals(A,C);// fails when the arguments are not equals
	
	 
}
@Test
void testException() {
	assertThrows(NullPointerException.class,() -> {throw new NullPointerException();});
	//handle exceptions
	 
}
@Test
void testException2() {
	assertThrows(ArithmeticException.class,() -> {int a=25/0;});
	
	 
}

}
