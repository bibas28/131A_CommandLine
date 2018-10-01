package cs131.pa1.filter.sequential;

public class PwdFilter extends SequentialFilter{

	public PwdFilter() {
		super();
	}

	public void process() {
		output.add(processLine(""));
	}
	
	public String processLine(String directory) {
		return SequentialREPL.currentWorkingDirectory;
	}
	
	public String toString() {
		return "pwd";
	}
	
}
