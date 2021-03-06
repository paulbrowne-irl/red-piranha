package net.firstpartners.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import net.firstpartners.TestConstants;
import net.firstpartners.core.word.DocumentConvertorTest;
import net.firstpartners.data.RangeList;

/**
 * Test to call the main method
 * 
 * @author PBrowne
 *
 */
public class RedGuiTest {

	// Logger
	private static final Logger log = Logger.getLogger(RedGuiTest.class.getName());

	@Test
	public final void testCallMain() throws Throwable {

		// Check where we are
		File whereAmI = new File(".");
		log.debug("Default file location:" + whereAmI.getAbsolutePath());

		// Call test

	}

	/**
	 * The Main method is more of a convenience for testing All it does is display
	 * the GUI
	 * 
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws UnsupportedLookAndFeelException
	 * @throws IOException
	 * @throws InvalidFormatException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException, IOException, InvalidFormatException {

		//Regen the Word data (can be commented out)
		DocumentConvertorTest.main(null);
		
		// Open the GUI
		log.debug("Opening GUI");
		RedGui player = new RedGui();

		// Get the test data - XL
		RangeList redData = getTestDataFromExcel();

		// Get the test data - Word

		RangeList redWordData = getTestDataFromWord();

		player.showPreRulesSnapShot(redData);
		player.showPostRulesSnapShot(redWordData);

		Runnable readRun = new Thread(player);
		readRun.run();

	}

	/**
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static RangeList getTestDataFromExcel() throws FileNotFoundException, IOException, ClassNotFoundException {
		FileInputStream fileIn = null;
		ObjectInputStream in = null;
		RangeList redData = null;
		fileIn = new FileInputStream(TestConstants.SAVED_EXCEL_RANGEHOLDER_DATA);
		in = new ObjectInputStream(fileIn);
		redData = (RangeList) in.readObject();
		fileIn.close();
		return redData;
	}

	/**
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static RangeList getTestDataFromWord() throws FileNotFoundException, IOException, ClassNotFoundException {
		FileInputStream fileIn;
		ObjectInputStream in;
		RangeList redWordData = null;
		fileIn = new FileInputStream(TestConstants.SAVED_WORD_RANGEHOLDER_DATA);
		in = new ObjectInputStream(fileIn);
		redWordData = (RangeList) in.readObject();
		fileIn.close();
		return redWordData;
	}
}
