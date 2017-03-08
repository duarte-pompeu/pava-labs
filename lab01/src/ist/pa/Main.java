package ist.pa;

import java.util.Scanner;
import java.lang.reflect.*;

public class Main{
	public static void main(String[] args){
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter your bff class:");
		String className = reader.next();
		
		try{
			Class<?> myClass = Class.forName(className);
			Constructor<?> constructor = myClass.getConstructor();
			Object object = constructor.newInstance(new Object[] { });
			
			//get method that takes a String as argument
			Method method = myClass.getMethod("say", null);
			method.invoke(object, null);
		}
		
		catch(Exception e){
			System.out.println(":(");
		}
	}
}
