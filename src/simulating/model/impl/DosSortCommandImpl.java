package simulating.model.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import simulating.model.DosPath;
import simulating.model.DosSortCommand;

/**
 * Full implementation for a Sort command, allowing to have:
 * + I/O from/to the file system
 * + I/O from/to the console (in order to stop reading the console input, it is necessary to drop Ctrl-Z + ENTER
 * 
 * @author Santiago Nuñez
 * @date 18/08/2016 
 */
public class DosSortCommandImpl extends DosSortCommand {

	private static final String JAVA_LINE_SEPARATOR_PROPERTY = "line.separator";
	
	@Override
	protected String[] readStandardInput() {
		List<String> lines = new ArrayList<String>();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		try {
			while ((line = in.readLine()) != null && line.length() != 0) {
				lines.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//An empty line or Ctrl-Z + ENTER terminates the program
		
		return lines.toArray(new String[lines.size()]);
	}
	
	@Override
	protected String[] readTextFileLines(DosPath filePath) {
		try {
			List<String> lines = Files.readAllLines(Paths.get(filePath.getPathString()), Charset.defaultCharset());
			return lines.toArray(new String[lines.size()]);
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	protected String getLineFeedSeparator() {
		return System.getProperty(JAVA_LINE_SEPARATOR_PROPERTY);
	}

	@Override
	protected void writeFileContent(DosPath filePath, String content) {
		try {
			Files.write(Paths.get(new File(filePath.getPathString()).toURI()), content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void displayText(String content) {
		System.out.println(content);
	}
}
