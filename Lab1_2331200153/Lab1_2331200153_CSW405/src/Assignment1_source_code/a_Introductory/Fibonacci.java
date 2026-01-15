package Assignment1_source_code.a_Introductory;

public class Fibonacci {
	public int fib(int n) {
		switch (n) {
			case 0: return 0;
			case 1: return 1;
//			default: return (fib(n - 1) + fib(n - 2)) + 1;

			// fibonacci F(n) = F(n-1) + F(n-2)
			// +1 not part of Fibonacci
			default: return (fib(n - 1) + fib(n - 2));
		}
	}
}
