package cs131.pa1.filter.sequential;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import cs131.pa1.filter.Message;

public class SequentialCommandBuilder {
	// Finite number of commands
	private static final String[] commandArr = {"pwd", "ls", "cd", "cat", "grep", "wc", "uniq", ">"};
	// Hash set for efficiency. Information look up is fast, REPL searches the list of commands on every iteration
	private static final HashSet<String> acceptedCommands = new HashSet<>(Arrays.asList(commandArr));
	
	
	public static List<SequentialFilter> createFiltersFromCommand(String command){
		String[] subcommands = command.split("\\s\\|\\s", -1); // separate commands by pipes
		List<SequentialFilter> filters = new LinkedList<>();
		SequentialFilter filter;
		int counter = -1;
		boolean hasRedirect = false;
		String temp = null;
		
		for(String subcommand : subcommands) {
			counter++;
			if(subcommand.contains(">")) {
				hasRedirect = true;
				temp = subcommand.substring(subcommand.indexOf("> "));
				subcommand = subcommand.substring(0, subcommand.indexOf(" > "));
			}
			filter = constructFilterFromSubCommand(subcommand, counter);
			if(filter != null) { // if there was no error when creating filter, then add it to the list
				filters.add(filter);
			} else {
				return null;
			}
		}
		//unless there is a redirect at the end, print filter is always the last filter
		if(hasRedirect) {
			filters.add(constructFilterFromSubCommand(temp, -1));
		}else {
			filters.add(new PrintFilter());
		}	
			
		if(linkFilters(filters)) { // if no errors when linking filters
			return filters;
		}
		
		return null;	
	}
	
	
	private static SequentialFilter constructFilterFromSubCommand(String subcommand,int counter){
	
		String[] subCommands = subcommand.split("\\s", -1); //split by whitespace
		SequentialFilter filter = null;
		String subFilter = subCommands[0];
		String error;
		
		
		if(!acceptedCommands.contains(subFilter)) { // invalid command
			System.out.print(Message.COMMAND_NOT_FOUND.with_parameter(subcommand));
			return null;
		}
		
		switch(subFilter) {
		
			case "cd":
				if(subCommands.length < 2) { // cd requires argument
					System.out.print(Message.REQUIRES_PARAMETER.with_parameter("cd"));
					return null;
				}
				String directory = subCommands[1];
				
				if(directory.equals(".")) { // stay on current directory
					return null;
				}
				filter = new CdFilter(directory);
				if(filter.processLine("") == null) { // if directory is not recognized
					return null;
				}
				
				break;
				
			case "cat":
				error = hasFileError(subCommands, subcommand, "cat");
				if(counter != 0) { // extra test to adjust for/accommodate test suite
					System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter("cat"));
					return null;
				}
				if(error != null) {
					System.out.print(error); // File not found
					return null;
				}
				
			try {
				filter = new CatFilter(subCommands);
			} catch (FileNotFoundException e1) {
				return null;
			}
				break;
				
			case "grep": 
				if(subCommands.length < 2) { // grep has no argument
					System.out.print(Message.REQUIRES_PARAMETER.with_parameter("grep"));
					return null;
				}
				filter = new GrepFilter(subCommands[1]);
				break;
				
			case ">":
				if(subCommands.length < 2) { // > has no argument
					System.out.print(Message.REQUIRES_PARAMETER.with_parameter(">"));
					return null;
				}
			try {
				filter = new RedirectFilter(subCommands[1]);
			} catch (Exception e) {
				return null;
			}
				break;
				
			case "ls" :
				filter = new LsFilter();
				break;
			case "pwd":
				filter = new PwdFilter();
				break;
			case "wc":
				filter = new WcFilter();
				break;
			case "uniq":
				filter = new UniqFilter();
				break;
		}
		
		return filter;
	}

	private static boolean linkFilters(List<SequentialFilter> filters){
		Iterator<SequentialFilter> iter = filters.iterator();
		SequentialFilter prev;
		SequentialFilter curr = iter.next(); //first filter in the list
		
		if(curr instanceof WcFilter || curr instanceof GrepFilter || curr instanceof RedirectFilter) { //first filter cannot be wc, grep, or > because they require input
			System.out.print(Message.REQUIRES_INPUT.with_parameter(curr.toString()));
			return false;
		}
		
		// now iterate through rest of commands
		while(iter.hasNext()) {
			prev = curr;
			curr = iter.next();
			
			// check for commands that cannot have input
			if(curr instanceof LsFilter || curr instanceof CdFilter || curr instanceof PwdFilter || curr instanceof CatFilter) {
				System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(curr.toString()));
				return false;
			}
			
			prev.setNextFilter(curr); // link filters
		}
		
		
		return true;
	}
	
	private static String hasFileError(String[] subCommands, String subcommand, String subFilter) {
		if(subCommands.length < 2) { // cat has no argument
			return Message.REQUIRES_PARAMETER.with_parameter(subFilter);
		}
		try {
			Scanner scanner = new Scanner(new File(subCommands[1]));
		} catch (Exception error) {
			return Message.FILE_NOT_FOUND.with_parameter(subcommand);
		}
		return null;
	}
	
}
