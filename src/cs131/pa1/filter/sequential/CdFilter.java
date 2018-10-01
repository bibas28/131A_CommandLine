package cs131.pa1.filter.sequential;

import java.io.File;

import cs131.pa1.filter.Filter;
import cs131.pa1.filter.Message;

public class CdFilter extends SequentialFilter {
	private String directoryPath;
		
	public CdFilter(String directory) {
		super();
		String instructions = directory;
		directoryPath = SequentialREPL.currentWorkingDirectory;
		
		if(instructions.equals("..")) { 
			directoryPath = directoryPath.substring(0, directoryPath.lastIndexOf(Filter.FILE_SEPARATOR));
		}else {
			String amendedDirectory = directoryPath + Filter.FILE_SEPARATOR + instructions;
			File testDirectory = new File(amendedDirectory);
			if(testDirectory.isDirectory()) {
				directoryPath = amendedDirectory;
			}else {
				System.out.print(Message.DIRECTORY_NOT_FOUND.with_parameter(amendedDirectory));
				directoryPath = SequentialREPL.currentWorkingDirectory;
			}
		}	
	}
	
	public void process() {
		processLine("");
	}

	public String processLine(String line) {
		SequentialREPL.currentWorkingDirectory = directoryPath;
		return null;
	}
	public String toString() {
		return "cd";
	}
}
