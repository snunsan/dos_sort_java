package simulating.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import simulating.model.DosSortCommand;
import simulating.test.model.doubles.DosSortCommandDouble;

/**
 * Unit tests for the DOS sort command simulated in Java.
 * 
 * @author Santiago Nuñez
 * @date 18/08/2016 
 */
public class DosSortTest {
	
	@Test
	public void test_SortFileAndWriteOutputSucceeds() {	
		String[] inputFileLines = new String[]{
			"John", "Ann", "doubt", "Ted", 
		};
		
		DosSortCommand sortCommand = new DosSortCommandDouble(null, inputFileLines);
		sortCommand.execute(new String[]{"<", "a.txt"});
		String[] output = sortCommand.getOutputLines();
		
		assertThat(output, equalTo(new String[]{"Ann", "doubt", "John", "Ted"}));
	}

	@Test
	public void test_SortFileReverselyAndWriteOutputSucceeds() {
		String[] inputFileLines = new String[]{
			"John", "Ann", "doubt", "Ted", 
		};
		
		DosSortCommand sortCommand = new DosSortCommandDouble(null, inputFileLines);
		sortCommand.execute(new String[]{"/R", "<", "a.txt"});
		String[] output = sortCommand.getOutputLines();
		
		assertThat(output, equalTo(new String[]{"Ted", "John", "doubt", "Ann"}));
	}
	
	@Test
	public void test_SortConsoleInputAndDisplayOutputSucceeds() {
		String[] consoleInputLines = new String[]{
			"D", "b", "C", "a", 
		};
		
		DosSortCommand sortCommand = new DosSortCommandDouble(consoleInputLines, null);
		sortCommand.execute(new String[]{});
		String[] output = sortCommand.getOutputLines();
		
		assertThat(output, equalTo(new String[]{"a", "b", "C", "D"}));
	}
	
	@Test
	public void test_SortFileWithColumn3AndDisplayOutputSucceeds() {
		String[] inputFileLines = new String[]{
			"1   4   5   9", "2   1   6   7", "3   0   1   0", "", "4   6   0   7", 
		};
		
		DosSortCommand sortCommand = new DosSortCommandDouble(null, inputFileLines);
		sortCommand.execute(new String[]{"/+9", "<", "a.txt"});
		String[] output = sortCommand.getOutputLines();
		
		assertThat(output, equalTo(new String[]{"", "4   6   0   7", "3   0   1   0", "1   4   5   9", "2   1   6   7"}));
	}
}
