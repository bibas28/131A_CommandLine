package cs131.pa1.filter.sequential;

public class PrintFilter extends SequentialFilter {

	public PrintFilter() {
		super();
	}

	public void process() {
		while(!isDone()) {
			processLine(input.poll());
		}
	}
	
	public String processLine(String line) {
		System.out.println(line);
		return null;
	}
	public String toString() {
		return "print";
	}
}
