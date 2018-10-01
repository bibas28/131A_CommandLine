package cs131.pa1.filter.sequential;

import java.io.File;

public class LsFilter extends SequentialFilter {
	private int counter;
	private File folder;
	private File[] folderList;
	
	public LsFilter() {
		super();
		counter = 0;
		folder = new File(SequentialREPL.currentWorkingDirectory);
		folderList = folder.listFiles();
	}
	
	public void process() {
		while(counter < folderList.length) {
			output.add(processLine(""));
			counter++;
		}
	}

	public String processLine(String line) {
		return folderList[counter].getName();
	}
	public String toString() {
		return "ls";
	}
}
