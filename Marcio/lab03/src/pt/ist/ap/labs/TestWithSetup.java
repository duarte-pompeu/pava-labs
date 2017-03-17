package pt.ist.ap.labs;

public class TestWithSetup {

	@Setup("s1")
	public static void s1() { System.out.println("s1"); }
	
	@Setup("s2")
	public static void s2() { System.out.println("s2"); }
	
	@Setup("s3")
	public static void s3() { throw new RuntimeException("Fail"); }
	
	
	@Test
	public static void m5() { System.out.println("m5"); }
	
	@Test({"s2", "s1"})
	public static void m6() { System.out.println("m6"); }
	
	@Test({"s1", "s2"})
	public static void m7() { throw new RuntimeException("problem"); }
	
	@Test("s1")
	public static void m8() { System.out.println("m8"); }
	
	public static void m9() { System.out.println("m9"); }
}

