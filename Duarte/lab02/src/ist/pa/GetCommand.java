package ist.pa;

public class GetCommand extends Command {
	private String argument;
	
	public String getArgument() {
		return argument;
	}

	public void setArgument(String argument) {
		this.argument = argument;
	}

	public GetCommand(String argument) {
		super();
		this.argument = argument;
	}

	
	@Override
	public void run() {
		Object o = Main.objectHashMap.get(argument);
		Main.lastObject = o;
	}
}
