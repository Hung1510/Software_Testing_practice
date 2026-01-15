package basicExample;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AnnotationsExample {

	@BeforeAll  //Denotes that the annotated method should be executed before all test methods
	static void setUpBeforeClass() throws Exception {
		System.out.println("Before all test methods");
	}

	@AfterAll //Denotes that the annotated method should be executed after all test methods
	static void tearDownAfterClass() throws Exception {
		System.out.println("After all test methods");
	}

	@BeforeEach  //Denotes that the annotated method should be executed before each test methods
	void setUp() throws Exception {
		System.out.println("Before each test method");
	}

	@AfterEach  //Denotes that the annotated method should be executed after each test methods
	void tearDown() throws Exception {
		System.out.println("After each test method");
	}

	@DisplayName("Test Method1 with condition")
	@Test  //Denotes the test method, where we check expected output with the actual output
	void test1() {
		assertEquals(4,2+2);
		System.out.println("Now inside test1 method");
	}
	@Test
	@Disabled("Implemented pending")
	void test2()
	{
		assertTrue(5*5==25);
		System.out.println("Now inside test2 method");
	}
	@ParameterizedTest(name="a= {0},b= {1},result= {2}")
	@CsvSource({"23,25,48","30,31,61"})
		void test(int a,int b,int result)
		{
			assertEquals(result,a+b);
		}
	

}
