package cs131.pa1.filter.sequential;

import java.util.Arrays;
import java.util.HashSet;
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
		for(String subcommand : subcommands) {
			filters.add(constructFilterFromSubCommand(subcommand));
		}
		
		return null;
	}
	
	
	private static SequentialFilter constructFilterFromSubCommand(String subCommand){
		String[] subCommands = subCommand.split("\\s", -1);
		String subFilter = subCommands[0];
		
		
		return null;
	}

	private static boolean linkFilters(List<SequentialFilter> filters){
		return false;
	}
}
