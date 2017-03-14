package ist.pa;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class Main{
	public static HashMap<String, Object> objectHashMap = new HashMap<String, Object>();
	public static Object lastObject = null;
	public static void main(String[] args){
		
		Scanner reader = new Scanner(System.in);
		String userInput = "";
		

		String[] commands = null;
		while(true){
			String commandStr = "";
			try {
				userInput = reader.nextLine();
				
				commands = userInput.split(" ");
				commandStr = commands[0];
				Command c;
				
				if(commandStr.equalsIgnoreCase("Exit")){
					break;
				}
				
				// get Command object
				Class<?> myClass;
			
				myClass = Class.forName("ist.pa." + commandStr + "Command");
				Constructor<?> constructor = myClass.getConstructor(String.class);
				String argument = commands[1];
				Object object = constructor.newInstance(new Object[] { argument });
				
				Method method = myClass.getMethod("run", null);
				method.invoke(object, null);
				System.out.println(lastObject);
			}
			catch (ClassNotFoundException e1) {
				try{
					Class c = lastObject.getClass();
					
					if(commands.length == 1){
						// has no args
					
						String invokeCommand = commandStr;
						Method method = c.getMethod(commandStr, null);
						Object result = method.invoke(lastObject, null);
						
						if(result != null){
							System.out.println(result);
						}
					}
					
					else if(commands.length > 1){
						// has args
						Method[] methods = c.getDeclaredMethods();
						ArrayList<Method> matchedMethods = new ArrayList<Method>();
						for(Method m: methods){
							if(m.getName().equals(commandStr)){
								matchedMethods.add(m);
								System.out.println(m);
							}
						}
						
						Class[] classes = { Double.class, Integer.class, String.class };
						int a = Integer.valueOf(commands[1]);
						
						
						if( matchedMethods.size() == 0){
							System.out.println("Error: no method found!");
						}
					}
				}
				catch (Exception e2){
					System.out.println("Error: " + e2 + ": " + e2.getCause());
				}
				
			}
			catch (Exception e){
				System.out.println("Error: " + e + ": " + e.getCause());
			}
		}
	}
}
