package ist.pa;

import javassist.*;
import java.io.*;
import java.lang.reflect.*;

public class Main{
	public static void main(String[] args) throws Exception{
		ClassPool pool = ClassPool.getDefault();
		pool.insertClassPath(new ClassClassPath(ist.pa.Main.class));
		CtClass ctClass = pool.get("ist.pa.Main");
		
		CtMethod ctMethod = ctClass.getDeclaredMethod("function");
		String name = ctMethod.getName();
		ctMethod.setName(name + "$original");
		ctMethod = CtNewMethod.copy(ctMethod, name, ctClass, null);
		ctMethod.insertAfter("{System.out.println(\"Goodbye\");}"
			);
		ctClass.addMethod(ctMethod);
		
		ctClass.writeFile();
		//~ Main.class.getMethod("function").invoke(null);
		for(Method m: Main.class.getMethods()){
			if(m.getName().equals("function")){
				m.invoke(null);
			}
		}
		
		function();
	}
	
	public static void function(){
		System.out.println("Hello.");
	}
}
