package ist.pa;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main{
	public static HashMap<String, Object> objectHashMap = new HashMap<String, Object>();
	public static Object lastObject = "Undercover string";
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
						
						ArrayList<Method> matchedMethods = getMatchingMethods(c, commands[0]);
						
						if( matchedMethods.size() == 0){
							throw new RuntimeException("Error: no method found!");
						}
						else{
							String[] stringParams = Arrays.copyOfRange(commands, 1, commands.length);
							Object[] params = castParams(stringParams);
							
							for(Method m: matchedMethods){
								try{
									Object result = m.invoke(lastObject, params);
									
									System.out.println(result);
									break;
								}
								catch (Exception e) {}
							}
							throw new RuntimeException("Matched methods couldn't execute process those parameters.\nFIXME: this error is always thrown");
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

	static public Object tryCast(String stringObject) throws RuntimeException {
		
		Class[] classes = { Double.class, Integer.class, Character.class, String.class };
		for(int i = 0; i < classes.length; i++){
			try{
				Class aClass = classes[i];
				Object o = aClass.cast(stringObject);
				System.out.println("".format("%s is a %s", o, aClass.getName()));
				return o;
			}
			catch (Exception e){
			}
		}
		throw new RuntimeException("Couldn't cast that!");
	}
	
	static public ArrayList<Method> getMatchingMethods(Class c, String methodName){
		Method [] methods = c.getDeclaredMethods();
		ArrayList<Method> matchedMethods = new ArrayList<Method>();
		for(Method m: methods){
			if(m.getName().equals(methodName)){
				matchedMethods.add(m);
				//~ System.out.println(m);
			}
		}
		return matchedMethods;
	}
	
	static public Object [] castParams(String[] params){
		Object [] castedObjects = new Object[params.length];
		for(int i = 0; i < params.length; i++){
			String s = params[i];
			
			Object o = tryCast(s);
			castedObjects[i] = o;
		}
		return castedObjects;
	}
}
