package pt.ist.ap.labs;

import java.lang.reflect.*;


public class RunTests {

	public static void main(String[] args) throws Exception {
		
		int passed = 0, failed = 0;
		Class<?> testClass = null;
		Method[] methods = null;
		
		try {
			testClass = Class.forName(args[0]);
			methods = testClass.getMethods();
			
		} catch (ClassNotFoundException ex) {
			System.out.println("Class not found! Don't forget to provide FQN.");
			
		} catch (Exception ex) {
			System.out.println("Ooops!");
		}
		
		for(Method m : methods) {
			
			if(m.isAnnotationPresent(Test.class)) {
				
				try {
					Test test = m.getAnnotation(Test.class);
					runSetup(test, methods);
					
					// run test method
					m.invoke(null);
					System.out.printf("Test %s OK! %n", m);
					passed++;
					
		        } catch (Throwable ex) {
		        	
		        	System.out.printf("Test %s failed: %s %n", m, ex.getCause());
		        	failed++;
		        }
			}
		}
		
		System.out.printf("Passed: %d, Failed %d%n", passed, failed);
	}
	
	private static void runSetup(Test test, Method[] methods) throws Throwable {
		
		Boolean runAll = false;
		String[] values = test.value();
		
		if(values[0].equals("*"))
			runAll = true;
		
		for(String sm : values)
			// look for methods with the Setup annotation
			for(int i = 0; i < methods.length; i++) {
				
				if(methods[i].isAnnotationPresent(Setup.class))
					if(runAll == true) {
						System.out.println("runAll = true " + methods[i]);
						methods[i].invoke(null); // run and continue searching
					}
					else {
						String name = methods[i].getAnnotation(Setup.class).value();
						
						if(name.equals(sm)) {
							methods[i].invoke(null); // run and stop searching (go to next value)
							break;
						}
					}
			}
	}
}

