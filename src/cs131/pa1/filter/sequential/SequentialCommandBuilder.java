package cs131.pa1.filter.sequential;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
		for(String subcommand : subcommands) {
			filter = constructFilterFromSubCommand(subcommand);
			if(filter != null) { // if there was no error when creating filter, then add it to the list
				filters.add(constructFilterFromSubCommand(subcommand));
			} else {
				return null;
			}
		}
		
		if(linkFilters(filters)) { // if no errors when linking filters
			return filters;
		}
		
		return null;	
	}
	
	
	private static SequentialFilter constructFilterFromSubCommand(String subcommand){
		String[] subCommands = subcommand.split("\\s", -1); //split by whitespace
		SequentialFilter filter;
		String subFilter = subCommands[0];
		String error;
		
		if(!acceptedCommands.contains(subFilter)) { // invalid command
			System.out.println(Message.COMMAND_NOT_FOUND.with_parameter(subFilter));
			return null;
		}
		
		switch(subFilter) {
		
			case "cd":
				if(subCommands.length < 2) { // cd requires argument
					System.out.println(Message.REQUIRES_PARAMETER.with_parameter("cd"));
					return null;
				}
				filter = new CdFilter(subcommand);
				break;
				
			case "cat":
				error = hasFileError(subCommands, subcommand, "cat");
				if(error != null) {
					System.out.println(error);
					return null;
				}
				filter = new CatFilter(subCommands);
				break;
				
			case "grep": 
				if(subCommands.length < 2) { // grep has no argument
					System.out.println(Message.REQUIRES_PARAMETER.with_parameter("grep"));
				}
				filter = new GrepFilter(subcommand);
				break;
				
			case ">":
				error = hasFileError(subCommands, subcommand, ">");
				if(error != null) {
					System.out.println(error);
					return null;
				}
				filter = new RedirectFilter();
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
			System.out.println(Message.REQUIRES_INPUT.with_parameter(curr.toString()));
			return false;
		}
		
		// now iterate through rest of commands
		while(iter.hasNext()) {
			prev = curr;
			curr = iter.next();
			
			// check for commands that cannot have input
			if(curr instanceof LsFilter || curr instanceof CdFilter || curr instanceof PwdFilter || curr instanceof CatFilter) {
				System.out.println(Message.CANNOT_HAVE_INPUT.with_parameter(curr.toString()));
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
			File file = new File(subCommands[1]);
		} catch (Exception error) {
			return Message.FILE_NOT_FOUND.with_parameter(subcommand);
		}
		return null;
	}
	
}
