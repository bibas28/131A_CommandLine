package cs131.pa1.filter.sequential;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import cs131.pa1.filter.Message;

public class SequentialREPL {

	static String currentWorkingDirectory;
	
	public static void main(String[] args){
		currentWorkingDirectory = System.getProperty("user.dir"); // initialize initial directory
		System.out.print(Message.WELCOME);
		System.out.print(Message.NEWCOMMAND);
		
		
		List<SequentialFilter> linkedFilters = new LinkedList<>();
		Scanner user = new Scanner(System.in);
		String input = user.nextLine();
		
		
		while(!input.equals("exit")) {
			if(input.length() == 0) { // if user simply hits the return button
				System.out.print(Message.NEWCOMMAND);
				input = user.nextLine();
				continue;
			}
			
			linkedFilters = SequentialCommandBuilder.createFiltersFromCommand(input);
			//System.out.println("linkedFilters = " + linkedFilters);
			
			if(linkedFilters != null) { // if list was made successfully
				Iterator<SequentialFilter> iter = linkedFilters.iterator();
				while(iter.hasNext()) {
					iter.next().process();
				}
			}
			
			
			System.out.print(Message.NEWCOMMAND);
			input = user.nextLine();
		}
		
		user.close();
		System.out.print(Message.GOODBYE);
		
	}

}
