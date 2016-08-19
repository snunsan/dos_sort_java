package simulating.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import simulating.DosSort;
import simulating.cli.DosCliArgumentsHandler;

/**
 * Model for a Sort command.
 * 
 * @author Santiago Nuñez
 * @date 18/08/2016 
 */
public abstract class DosSortCommand {
	
	private static final Logger LOGGER = Logger.getLogger(DosSortCommand.class.getName());
	static {
		DosSort
		 .setLogger(LOGGER);
	}
	private static final String DEFAULT_LINE_CONTENT = "";
	
	private String[] outputLines;
	
	public String[] getOutputLines() {
		return outputLines;
	}

	public final void execute(String[] args) {
		
		DosCliArgumentsHandler cliArgsHandler = new DosCliArgumentsHandler(args);
		
		printArgs(args);
		
		List<DosSortableLine> sortableLines = getSortableLines(cliArgsHandler);
		
		if(sortableLines != null) {
			if(cliArgsHandler.isAscendingSort()) {
				Collections.sort(sortableLines);
			}
			else {
				Collections.sort(sortableLines);
				Collections.reverse(sortableLines);
			}
			
			outputLines = calculateOutputLines(sortableLines);
						
			String outputContent = String.join(getLineFeedSeparator(), outputLines);

			if(outputContent != null) {
				DosPath outputFilePath = cliArgsHandler.getOutputFilePath();
				if(outputFilePath != null) {
					writeFileContent(outputFilePath, outputContent);
				}
				else {
					displayText(outputContent);
				}
			}
		}
	}
	
	private void printArgs(String[] args)
	{
		LOGGER.log(Level.FINE, "Running command with arguments");

		int i = 0;
		for(String arg:args) {
			LOGGER.log(Level.FINE, "\tArg #" + (i++) + ": " + arg);
		}
	}
	
	private List<DosSortableLine> getSortableLines(DosCliArgumentsHandler cliArgsHandler) {
		List<DosSortableLine> sortableLines = new ArrayList<DosSortableLine>();
		
		String[] lines = getInputLines(cliArgsHandler);
		int sortingColumnIndex = cliArgsHandler.getSortingColumnIndex();
		
		for(String line:lines) {
			Character character = sortingColumnIndex < line.length()? line.charAt(sortingColumnIndex):null;
			String sortableColumn = character != null? String.valueOf(character):DEFAULT_LINE_CONTENT;
			
			if(sortableColumn != null) {
				//The sorting needs to implement case insensivity
				sortableLines.add(new DosSortableLine(sortableColumn.toUpperCase(), line));
			}
		}
		
		return sortableLines;
	}
	
	private String[] getInputLines(DosCliArgumentsHandler cliArgsHandler) {
		String[] lines = null;
		
		DosPath inputFilePath = cliArgsHandler.getInputFilePath();
		
		if(inputFilePath == null) {
			lines = readStandardInput();
		}
		else {
			lines = readTextFileLines(inputFilePath);
		}
		
		return lines;
	}
	
	protected abstract String[] readStandardInput();
	
	protected abstract String[] readTextFileLines(DosPath filePath);

	protected abstract String getLineFeedSeparator();
	
	private String[] calculateOutputLines(List<DosSortableLine> sortableLines) {
		String[] lines = new String[sortableLines.size()];
		
		int i = 0;
		for(DosSortableLine sortableLine:sortableLines) {
			lines[i++] = sortableLine.getContent();
		}
		
		return lines;
	}

	protected void writeFileContent(DosPath filePath, String content) {
		//Does nothing by default
	}
	
	protected void displayText(String content) {
		//Does nothing by default
	}
}
