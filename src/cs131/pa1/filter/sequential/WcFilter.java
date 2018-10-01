package cs131.pa1.filter.sequential;

public class WcFilter extends SequentialFilter{
	private int lineCount;
	private int wordCount;
	private int charCount;
	
	public WcFilter() {
		super();
		lineCount = 0;
		wordCount = 0;
		charCount = 0;
	}
	
	public void process() {
		if(isDone()) {
			output.add(processLine(null));
		}else {
			super.process();
		}
	}

	public String processLine(String line) {
		if(line == null) {
			return lineCount + " " + wordCount + " " + charCount;
		}
		
		if(isDone()) {
			String[] words = line.split(" ");
			String[] characters = line.split("|");
			wordCount += words.length;
			charCount += characters.length;
			lineCount++;
			return lineCount + " " + wordCount + " " + charCount;
		}else {
			lineCount++;
			String[] words = line.split(" ");
			String[] characters = line.split("|");
			wordCount += words.length;
			charCount += characters.length;
			return null;
		}
	}
	
	public String toString() {
		return "wc";
	}
}
