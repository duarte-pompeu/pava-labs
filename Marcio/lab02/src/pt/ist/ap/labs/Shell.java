package pt.ist.ap.labs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.lang.reflect.*;


public class Shell {
	
	public static Map<String, Object> map = new HashMap<String, Object>();
	public static Object lastObject = null;
	
	protected static Map<String, Class<?>> primitiveTypes = new HashMap<String, Class<?>>();
	
	
	public static void main(String[] args) throws IOException {
		
		if(args.length > 0) {
			System.out.println("No arguments required!");
			System.exit(1);
		}
		else {
			System.out.println("Welcome!");
			
			// final String prompt = "Command:> ";
			Scanner reader = new Scanner(System.in);
			String input = "";
			String[] tokens;
			String command = "";
			
			setPrimitiveTypes();
			
			while(true) {
				// System.out.print(prompt);
				input = reader.nextLine();
					
				if(input == null) {
					System.out.println("Please, insert a command...");
					continue;
				}
				
				tokens = input.split(" "); 
				command = tokens[0];
				
				if(command.equalsIgnoreCase("exit")) {
					System.out.println("Goodbye!");
					break;
				}
					
				String commandFull = "pt.ist.ap.labs." + command + "Command";
				
				// use reflection to create instance of a command
				try {
					Class<?> cls = Class.forName(commandFull);
					
					Constructor<?> cons = cls.getConstructor(String.class);
					String argument = tokens[1];
					Object obj = cons.newInstance(new Object[] { argument });
					
					Method method = cls.getMethod("run");
					method.invoke(obj);
					
				} catch (ClassNotFoundException e) {
					tryGenericCommand(input);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			}
			
			reader.close();
		}
	}

	
	private static void tryGenericCommand(String input) {
		
		System.out.println("Trying generic command: " + input);
		
		Object obj = Shell.lastObject;
		Class<?> cls = obj.getClass();
		
		// get method name
		String[] tokens = input.split(" ");
		String command = tokens[0];
	
		// get type of parameters (odd indexes)
		List<Class<?>> typeList = new ArrayList<Class<?>>();
		
		for(int i = 1; i < tokens.length; i += 2) {
			
			try {
				if(Shell.primitiveTypes.containsKey(tokens[i]))
					typeList.add(Shell.primitiveTypes.get(tokens[i]));
				else
					typeList.add(Class.forName(tokens[i]));
				
			} catch (ClassNotFoundException e) {
				
				System.out.println("Invalid type!");
				return;
			}
		}
		
		// TODO erase
		for(Class<?> c : typeList)
			System.out.println("Class: " + c);
		
		// search for method with the desired signature
		Method method = null;
		Class<?>[] types = new Class<?>[typeList.size()];
		typeList.toArray(types);
		
		try {
			
			method = cls.getMethod(command, (typeList.size() == 0) ? null : types);
			System.out.println("Method: " + method);
			
		} catch (NoSuchMethodException e) {
			
			System.out.println("Please, insert a valid command...");
			return;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		
		// get parameters (even indexes)
		List<String> paramList = new ArrayList<String>();
		
		for(int i=2; i < tokens.length; i+=2) {
			paramList.add(tokens[i]);
		}
		
		Object[] params = new Object[paramList.size()];
		paramList.toArray(params);
		
		// match type with argument - ONLY FOR ONE int ARGUMENT
		if(typeList.size() > 0 && types[0] == int.class)
			params[0] = (Object) Integer.parseInt(paramList.get(0));
		
		//invoke with parameters
//		try {
//			method.invoke(obj, (paramList.size() == 0) ? null : params);
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	
	private static void setPrimitiveTypes() {
		
		Map<String, Class<?>> map = Shell.primitiveTypes;
		
		map.put("int", int.class);
		map.put("double", double.class);
		map.put("float", float.class);
		map.put("boolean", boolean.class);
		map.put("String", String.class);
	}
	
	
	static Map<String, Object> getMap() {
		
		return map;
	}

	
	static void addObject(String name, Object obj) {
		
		map.put(name, obj);
	}
	
	
	static Object getObject(String name) {
		
		return map.get(name);
	}

	
	static Object getLastObject() {
		
		return lastObject;
	}

	
	static void setLastObject(Object obj) {
		
		lastObject = obj;
	}
}





