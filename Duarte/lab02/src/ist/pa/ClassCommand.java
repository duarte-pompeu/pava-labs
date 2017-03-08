package ist.pa;

import java.lang.reflect.Constructor;

public class ClassCommand extends Command{
	private String argument;
	
	public ClassCommand(String argument) {
		super();
		this.argument = argument;
	}

	@Override
	public void run() {
		
		try{
			Class<?> myClass = Class.forName(argument);
			Constructor<?> constructor = myClass.getConstructor();
			Object object = constructor.newInstance();
			
			Main.lastObject = object;
		}
		catch(Exception e){
			
		}
	}
	
	
}
