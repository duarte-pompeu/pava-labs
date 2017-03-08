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
			Constructor<?> constructor = myClass.getConstructor(String.class);
			Object object = constructor.newInstance(new Object[] { argument });
			
			Main.lastObject = object;
		}
		catch(Exception e){
			
		}
	}
	
	
}
