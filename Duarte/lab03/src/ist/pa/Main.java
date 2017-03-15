package ist.pa;

import java.lang.reflect.*;
import java.lang.annotation.*;
import java.util.*;

public class Main{
	public static void main(String[] args) throws Exception{
		
		int passed = 0; int failed = 0;
		for (Method m: Class.forName("ist.pa.HelloWorld").getMethods()){
			//~ System.out.println(m);
			
			if(m.isAnnotationPresent(Test.class)){
				try{
					String name = m.getAnnotation(Test.class).value();
					runSetup(Class.forName("ist.pa.HelloWorld"), name);
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
			
			// continue if not setup
			Annotation a = m.getAnnotation(Setup.class);
			if(a == null){
				continue;
			}
			
			
			String[] values = name.split(",");
			for(String v: values){
				if(v.equals("*")){
					m.invoke(null);
				}
				else if (m.getAnnotation(Setup.class).value().equals(v)){
					m.invoke(null);
				}
			}
		}
	}
}
