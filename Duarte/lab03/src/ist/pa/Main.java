package ist.pa;

import java.lang.reflect.*;
import java.lang.annotation.*;

public class Main{
	public static void main(String[] args) throws Exception{
		
		int passed = 0; int failed = 0;
		for (Method m: Class.forName("ist.pa.HelloWorld").getMethods()){
			//~ System.out.println(m);
			
			if(m.isAnnotationPresent(Test.class)){
				try{
					Test a = m.getAnnotation(Class.forName("ist.pa.HelloWorld"));
					runSetup(Class.forName("ist.pa.HelloWorld"), "");
					m.invoke(null);
					passed++;
					System.out.println("PASS: " + m);
				}
				catch (Throwable t){
					System.out.printf("FAIL: %s failed: %s \n", m, t.getCause());
					failed++;
				}
			}			
		}
		System.out.printf("Passed: %d, Failed: %d%n", passed, failed);
	}
	
	public static void runSetup(Class c, String name) throws Exception{
		for (Method m: Class.forName("ist.pa.HelloWorld").getMethods()){
			
			if(! m.isAnnotationPresent(Setup.class)){
				continue;
			}
			
			Annotation[] annotations = m.getDeclaredAnnotations();
			for(Annotation a: annotations){
				if (a.annotationType().equals(Setup.class)){
					if(a.equals(name)){
						m.invoke(null);
					}
				}
			}
		}
	}
}
