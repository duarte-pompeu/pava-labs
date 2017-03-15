package ist.pa;

public class HelloWorld{
	
	@Test
	public static void m1() {}
	@Test("1")
	public static void m2() {}
	@Test("1,2")
	public static void m3() {}
	@Test("1")
	public static void m4() {}
	@Test
	public static void m5() {}
	@Test
	public static void m6() { throw new RuntimeException(); }
	
	@Setup("1")
	public static void s1() {System.out.println("s1");}
	@Setup("2")
	public static void s2() {System.out.println("s2");}
	@Setup("3")
	public static void s3() {System.out.println("s3");}
}
