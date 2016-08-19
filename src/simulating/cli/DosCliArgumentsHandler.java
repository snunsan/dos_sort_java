package simulating.cli;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;

import simulating.model.DosPath;

/**
 * Handler for the Command Line Interface arguments of the Sort command
 * 
 * @author Santiago Nuñez
 * @date 18/08/2016 
 */
public class DosCliArgumentsHandler {
	
	private static final String INPUT_FILE_DECLARATION_INDICATOR = "<";
	private static final String OUTPUT_FILE_DECLARATION_INDICATOR = ">";
	private static final String DESCENDING_SORT_MODIFIER = "/R";
	private static final String SORTING_COLUMN_MODIFIER_REGEXP_START = "/\\+";
	private static final String SORTING_COLUMN_MODIFIER_REGEXP = SORTING_COLUMN_MODIFIER_REGEXP_START + "\\d+";
	private static final int DEFAULT_SORTING_COLUMN = 0;

	private String[] args;

	public DosCliArgumentsHandler(String[] args) {
		super();
		this.args = args != null? args:new String[]{};
	}
	
	public DosPath getInputFilePath() {
		final int ARGS_LAST_ELEMENT_INDEX = args.length - 1;
		
		int i = 0;
		for(String arg:args) {
			if(INPUT_FILE_DECLARATION_INDICATOR.equals(arg) && i < ARGS_LAST_ELEMENT_INDEX) {
				return new DosPath(args[i + 1]); //The next element after the input redirection modifier is the input file path
			}
			i++;
		}
		
		return null;
	}
	
	public DosPath getOutputFilePath() {
		final int ARGS_LAST_ELEMENT_INDEX = args.length - 1;
		
		int i = 0;
		for(String arg:args) {
			if(OUTPUT_FILE_DECLARATION_INDICATOR.equals(arg) && i < ARGS_LAST_ELEMENT_INDEX) {
				return new DosPath(args[i + 1]); //The next element after the output redirection modifier is the output file path
			}
			i++;
		}
		
		return null;
	}
	
	public int getSortingColumnIndex() {
		for(String arg:args) {
			Pattern p = Pattern.compile(SORTING_COLUMN_MODIFIER_REGEXP);
			Matcher m = p.matcher(arg);
			
			if(m.find()) {
				Integer argColumnNumber = null;
				
				try {
					//The second element is the number after the regexp start
					argColumnNumber = Integer.valueOf(arg.split(SORTING_COLUMN_MODIFIER_REGEXP_START)[1]);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return argColumnNumber != null? argColumnNumber - 1:DEFAULT_SORTING_COLUMN;
			}
		}
		
		return DEFAULT_SORTING_COLUMN;
	}
	
	public boolean isAscendingSort() {
		return !ArrayUtils.contains(args, DESCENDING_SORT_MODIFIER);
	}
}
