package cs131.pa1.filter.sequential;

public class GrepFilter extends SequentialFilter{
	private String searchWord;
	
	public GrepFilter(String searchWord) {
		super();
		
		this.searchWord = searchWord;	
	}

	public String processLine(String line) {
		if(line.contains(searchWord)) {
			return line;
		}
		return null;
	}
	public String toString() {
		return "grep";
	}
}
