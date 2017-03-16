package pt.ist.ap.labs;

import java.lang.reflect.Constructor;


public class ClassCommand extends Command {

	private String className;
	
	public ClassCommand(String arg) {
		
		super();
		this.className = arg;
	}

	@Override
	public void run() {
		
		// use reflection to create instance of the desired class
		try {
			Class<?> cls = Class.forName(className);
			
			Constructor<?> cons = cls.getConstructor(); // get default constructor
			Object obj = cons.newInstance();
			
			Shell.setLastObject(obj); // save as last object
			
			System.out.println("class " + className);
			
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found!");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

}
