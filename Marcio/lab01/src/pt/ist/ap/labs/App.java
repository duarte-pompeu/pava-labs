package pt.ist.ap.labs;

import java.io.IOException;
import java.lang.reflect.*;

public class App {
	
	public static void main(String[] args) throws IOException {
		
		if(args.length != 1) {
			System.out.println("Usage: java App <name_of_class>");
			System.exit(1);
		}
		else {
			String className = "pt.ist.ap.labs." + args[0];
			
			try {
				Class<?> c = Class.forName(className);
				
				Constructor<?> cons = c.getConstructor();
				Object obj = cons.newInstance();
				
				Method m = c.getDeclaredMethod("say");
				m.invoke(obj);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
