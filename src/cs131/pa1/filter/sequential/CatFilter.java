package cs131.pa1.filter.sequential;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CatFilter extends SequentialFilter {
	private Scanner fileScanner;
	
	public CatFilter(String[] subCommands) throws FileNotFoundException {
		super();
		
		//can directly initialize these variables because error checking happens beforehand
		String fileName = subCommands[1];
		fileScanner = new Scanner(new File(fileName));
		
	}
		
	public void process() {
		while(!isDone()) {
			String processedLine = processLine(fileScanner.nextLine());
			if(processedLine == null) { // if no more lines left to read, break out of loop
				break;
			}
			output.add(processedLine);
		}
		
		fileScanner.close();
	}
		
	public String processLine(String line) {
		return line;
	}

	public String toString() {
		return "cat";
	}
	
	public boolean isDone() {
		return !fileScanner.hasNextLine();
	}
}
