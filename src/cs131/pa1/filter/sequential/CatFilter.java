package cs131.pa1.filter.sequential;

import java.util.Scanner;

public class CatFilter extends SequentialFilter {
	private Scanner fileScanner;
	
	public CatFilter(String[] subCommands) {
		super();
		
		//can directly initialize these variables because error checking happens beforehand
		String fileName = subCommands[1];
		fileScanner = new Scanner(fileName);
		
	}
		
	public void process() {
		while(fileScanner.hasNext()) {
			String processedLine = processLine("");
			if(processedLine == null) { // if no more lines left to read, break out of loop
				break;
			}
			output.add(processedLine);
		}
		
		fileScanner.close();
	}
		
	public String processLine(String line) {
		if(fileScanner.hasNextLine()) {
			return fileScanner.nextLine();
		}
		
		return null;
	}

	public String toString() {
		return "cat";
	}
	
}
