package simulating;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import simulating.model.DosSortCommand;
import simulating.model.impl.DosSortCommandImpl;

/**
 * DOS sort command simulated in Java.
 * 
 * Behaviour described at:
 * @see http://www.easydos.com/sort.html
 * 
 * @author Santiago Nuñez
 * @date 18/08/2016 
 */
public class DosSort {

	private static final boolean TESTING_MODE = false;
	
	public static void main(String[] args) {
		DosSortCommand sortCommand = new DosSortCommandImpl();
		sortCommand.execute(args);
	}
	
	public static void setLogger(Logger logger) {
		if(TESTING_MODE) {
			logger.setLevel(Level.ALL);
			ConsoleHandler ch = new ConsoleHandler();
	        ch.setLevel(Level.FINEST);
	        logger.addHandler(ch);
	        logger.setLevel(Level.FINEST);
		}
	}
}
