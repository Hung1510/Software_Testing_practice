public class Fibonacci {
	public int fib(int n) {
		switch (n) {
			case 0: return 0;
			case 1: return 1;
//			default: return (fib(n - 1) + fib(n - 2)) + 1; : error: extra +1
			//standard fibonacci sequence: F(n) = F(n-1) + F(n-2)
			default: return (fib(n - 1) + fib(n - 2));
		}
	}
}
