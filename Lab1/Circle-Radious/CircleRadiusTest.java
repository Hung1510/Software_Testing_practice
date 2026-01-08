import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CircleRadiusTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testMain() {
		String input="1\n";
		ByteArrayInputStream in=new
				ByteArrayInputStream(input.getBytes());
				System.setIn(in);
		ByteArrayOutputStream out=new ByteArrayOutputStream();
				System.setOut(new PrintStream(out));
				String[] args={};
				CircleRadius.main(args);
				
				String consoleOutput="Enter the radius "+System.getProperty("line.separator");
						consoleOutput+="For a circle with radius 1.0,"+System.getProperty("line.separator");
						consoleOutput+="The circumference is 6.283185307179586"+System.getProperty("line.separator");
						consoleOutput+="and the area is 3.141592653589793."+System.getProperty("line.separator");
				
						assertEquals(consoleOutput,out.toString());
				
	}

}
