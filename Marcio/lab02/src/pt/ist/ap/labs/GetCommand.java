package pt.ist.ap.labs;

public class GetCommand extends Command {

	private String name;
	
	public GetCommand(String arg) {
		
		super();
		this.name = arg;
	}
	
	@Override
	public void run() {
		
		Object obj = Shell.getObject(name); // get object by name
		Shell.setLastObject(obj); // set as last object
		
		System.out.println("class " + obj.getClass().getName());
	}

}
