package cs131.pa1.filter.sequential;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import cs131.pa1.filter.Message;

public class SequentialREPL {

	static String currentWorkingDirectory;
	
	public static void main(String[] args){
		updateWorkingDirectory(); // initialize initial directory
		System.out.print(Message.NEWCOMMAND);
		System.out.print(Message.WELCOME);
		System.out.print(Message.NEWCOMMAND);
		
		SequentialCommandBuilder commandBuilder = new SequentialCommandBuilder();
		List<SequentialFilter> filters = new LinkedList<>();
		Scanner user = new Scanner(System.in);
		String input = user.nextLine();
		
		while(!input.equals("exit")) {
			if(input.length() == 0) { // if user simply hits the return button
				System.out.print(Message.NEWCOMMAND);
				input = user.nextLine();
				continue;
			}
			
			if(commandBuilder.createFiltersFromCommand(input) == null) { // some sort of error with the user's request
				System.out.print(Message.NEWCOMMAND);
				input = user.nextLine();
				continue;
			}
			
			/*
			 ...do some stuff
			 */
			
			System.out.print(Message.NEWCOMMAND);
			input = user.nextLine();
		}
		
		user.close();
		System.out.print(Message.GOODBYE);
		
	}

	private static void updateWorkingDirectory() {
		currentWorkingDirectory = System.getProperty("user.dir");
	}
}
