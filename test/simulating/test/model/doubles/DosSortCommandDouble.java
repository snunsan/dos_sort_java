package simulating.test.model.doubles;

import simulating.model.DosPath;
import simulating.model.DosSortCommand;

/**
 * Test double that eases unit testing for the Sort command without any dependencies on the file system or the console.
 * 
 * @author Santiago Nuñez
 * @date 18/08/2016 
 */
public class DosSortCommandDouble extends DosSortCommand {
	
	private static final String LINE_FEED = "\n";
	
	private String[] consoleInputLines;
	private String[] inputFileLines;

	public DosSortCommandDouble(String[] consoleInputLines, String[] inputFileLines) {
		super();
		this.consoleInputLines = consoleInputLines;
		this.inputFileLines = inputFileLines;
	}

	@Override
	protected String[] readStandardInput() {
		return consoleInputLines;
	}
	
	@Override
	protected String[] readTextFileLines(DosPath filePath) {
		return inputFileLines;
	}

	@Override
	protected String getLineFeedSeparator() {
		return LINE_FEED;
	}
}
