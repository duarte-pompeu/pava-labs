package ist.pa;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Scanner;

public class Main{
	public static HashMap<String, Object> objectHashMap = new HashMap<String, Object>();
	public static Object lastObject = null;
	public static void main(String[] args){
		
		Scanner reader = new Scanner(System.in);
		String userInput = "";
		

		String[] commands = null;
		try{
			while(true){
				userInput = reader.nextLine();
				
				commands = userInput.split(" ");
				String commandStr = commands[0];
				Command c;
				
				if(commandStr.equalsIgnoreCase("Exit")){
					break;
				}
				
				// get Command object
				Class<?> myClass;
				try {
					myClass = Class.forName("ist.pa." + commandStr + "Command");
					Constructor<?> constructor = myClass.getConstructor(String.class);
					String argument = commands[1];
					Object object = constructor.newInstance(new Object[] { argument });
					
					Method method = myClass.getMethod("run", null);
					method.invoke(object, null);
					System.out.println(lastObject);
				} 
				catch (ClassNotFoundException e) {
					System.out.println("Class not found.");
					String invokeCommand = commandStr;
					Method method = lastObject.getClass().getMethod(commandStr, null);
					Object result = method.invoke(lastObject, null);
					
					if(result != null){
						System.out.println(result);
					}
				}
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
