package cs131.pa1.filter.sequential;

import java.util.HashSet;

public class UniqFilter extends SequentialFilter {
	private HashSet<String> existingStrings; // again hash set is used for look up efficiency
	
	public UniqFilter() {
		existingStrings = new HashSet<String>();
	}
	
	public String processLine(String line) {
		if(existingStrings.contains(line)) {
			return null;
		}
		
		existingStrings.add(line);
		return line;
	}
	
	public String toString() {
		return "uniq";
	}
}
