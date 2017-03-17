package pt.ist.ap.labs;

public class TestSimple {
	
	@Test public static void m1() { System.out.println("m1"); }
	
    @Test public static void m2() { System.out.println("m2"); }
    
    @Test public static void m3() { throw new RuntimeException("Problem"); }
    
    public static void m4() { System.out.println("m4"); }
}
