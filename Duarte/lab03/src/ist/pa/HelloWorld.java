package ist.pa;

public class HelloWorld{
	
	@Test
	public static void m1() {}
	@Test("a")
	public static void m2() {}
	@Test
	public static void m3() {}
	@Test
	public static void m4() { throw new RuntimeException(); }
	
	@Setup("a")
	public static void s2() {System.out.println("s2");}
	@Setup
	public static void s3() {System.out.println("s2");}
}
