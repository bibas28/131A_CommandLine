package cs131.pa1.filter.sequential;

import java.io.FileWriter;
import java.io.IOException;

import cs131.pa1.filter.Filter;

public class RedirectFilter extends SequentialFilter {
	private FileWriter writer;
	
	public RedirectFilter(String fileName) throws Exception {
		super();
		
		try {
			writer = new FileWriter(SequentialREPL.currentWorkingDirectory + Filter.FILE_SEPARATOR + fileName);
		} catch (IOException e) {
			throw new Exception();
		}
	}
	
	public void process() {
		while(!isDone()) {
			processLine(input.poll());
		}
	}
	
	public String processLine(String line) {
		try {
			writer.append(line + "\n");
			if(isDone()) {
				writer.flush();
				writer.close();
			}
		} catch (IOException e) {
			System.out.println();
		}
		return null;
	}
	public String toString() {
		return ">";
	}
}
