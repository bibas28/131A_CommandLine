package cs131.pa1.filter.sequential;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import cs131.pa1.filter.Message;

public class SequentialCommandBuilder {
	// Finite number of commands
	private static final String[] commandArr = {"pwd", "ls", "cd", "cat", "grep", "wc", "uniq", ">"};
	// Hash set for efficiency. Information look up is fast, REPL searches the list of commands on every iteration
	private static final HashSet<String> acceptedCommands = new HashSet<>(Arrays.asList(commandArr));
	
	
	public static List<SequentialFilter> createFiltersFromCommand(String command){
//		String commandInstructions[] = command.split(" ");
//		if(!acceptedCommands.contains(commandInstructions[0])) { // command not recognized
//			System.out.print(Message.COMMAND_NOT_FOUND.with_parameter(commandInstructions[0]));
//			return null;
//		}
//		
//		String commandName = commandInstructions[0];
//		SequentialFilter filter = determineFilter(commandName);
//		
//		return null;
	}

	private static SequentialFilter determineFinalFilter(String command){
		return null;
	}
	
	private static String adjustCommandToRemoveFinalFilter(String command){
		return null;
	}
	
	private static SequentialFilter constructFilterFromSubCommand(String subCommand){
		return null;
	}

	private static boolean linkFilters(List<SequentialFilter> filters){
		return false;
	}
}
