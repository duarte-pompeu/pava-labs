package pt.ist.ap.labs;

import java.lang.reflect.Array;

public class IndexCommand extends Command {

	private int index;
	
	public IndexCommand(String arg) {
		
		super();
		this.index = Integer.parseInt(arg);
	}
	
	@Override
	public void run() {
		
		Object obj = Shell.getLastObject();
		Class<?> cls = obj.getClass();
		
		// check if it is an array type
		if(cls.isArray()) {
			
			Object newObj = Array.get(obj, index);
			Shell.setLastObject(newObj); // set as last object
			
			System.out.println(obj);
		}
		else
			System.out.println("Last object is not an array!");
	}

}
