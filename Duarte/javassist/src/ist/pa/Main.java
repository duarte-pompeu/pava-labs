package ist.pa;

import javassist.*;
import java.io.*;
import java.lang.reflect.*;

public class Main{
	
	
	public static void main(String[] args) throws Exception{

	}
	
	public static void javassistTest() throws Exception{
		Class c = Test.class;
		String funcName = "function";	
		
		
		ClassPool pool = ClassPool.getDefault();
		pool.insertClassPath(new ClassClassPath(c));
		
		String className = c.getCanonicalName();
		System.out.println(className);
		CtClass ctClass = pool.get(className);
		
		CtMethod ctMethod = ctClass.getDeclaredMethod(funcName);
		String name = ctMethod.getName();
		ctMethod.setName(name + "$original");
		ctMethod = CtNewMethod.copy(ctMethod, name, ctClass, null);
		ctMethod.setBody("{System.out.println(\"Goodbye\");}"
			);
		ctClass.addMethod(ctMethod);
		ctClass.writeFile();
		
		c.getMethod("function").invoke(null);
		for(Method m: Main.class.getMethods()){
			if(m.getName().equals("function")){
				m.invoke(null);
			}
		}
		Test.function();
	}
}
