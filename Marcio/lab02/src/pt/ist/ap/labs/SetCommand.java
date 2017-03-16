package pt.ist.ap.labs;

public class SetCommand extends Command {

	private String name;
	
	public SetCommand(String arg) {
		
		super();
		this.name = arg;
	}
	
	@Override
	public void run() {
		
		// save last object with the provided name
		Object obj = Shell.getLastObject();
		Shell.addObject(name, obj);
		
		System.out.println("Saved name for object of type: class " + obj.getClass().getName());
		System.out.println("class " + obj.getClass().getName());
	}

}
