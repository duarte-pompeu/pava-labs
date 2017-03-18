package pt.ist.ap.labs;




public class Fib {

	public static void main(String[] args) {
		
		if(args.length != 1) {
			System.out.println("Usage: java Fib <arg>");
			System.exit(1);
		}
		else {
			Long result = fib(Long.parseLong(args[0]));
			System.out.println(result);
		}
	}
	
	
	public static Long fib(Long n) {
		
		if(n < 2)
			return n;
		else
			return fib(n-1) + fib(n-2);
	}
}
