package ist.pa;

public class SimpleTest{
	
	@Test()
	public static void m1() {}
	@Test
	public static void m2() {}
	@Test
	public static void m3() { throw new RuntimeException(); }
}
