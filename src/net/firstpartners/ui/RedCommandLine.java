package net.firstpartners.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.drools.compiler.compiler.DroolsParserException;

import net.firstpartners.RedConstants;
import net.firstpartners.core.drools.FileRuleLoader;
import net.firstpartners.core.drools.PreCompileRuleBuilder;
import net.firstpartners.core.drools.SpreadSheetRuleRunner;
import net.firstpartners.core.drools.data.RuleSource;
import net.firstpartners.core.log.ILogger;
import net.firstpartners.core.log.RpLogger;

/**
 * Main Entry Point for Red-Piranha
 * 
 * @author PBrowne
 *
 */
public class RedCommandLine {


	private static final Logger log = RpLogger.getLogger(RedCommandLine.class.getName());

	// Handle to common utility file
	private static final SpreadSheetRuleRunner commonUtils = new SpreadSheetRuleRunner(new FileRuleLoader());

	/**
	 * Usage from command line java -jar [jarName.jar] all args are ignored - we
	 * load from config rulesfile2 ...
	 *
	 * @param args - not used
	 * @throws IOException
	 * @throws DroolsParserException
	 */
	public static void main(String[] inforedArgs) throws Exception {

		// read the properties file
		Properties prop = UiUtils.readConfig();

		// Check and force logging
		Object logFileName = prop.get(UiUtils.LOG_FILE_NAME);
		RpLogger.forceLogToFile(logFileName);

		// Get the params
		String excelFile = prop.getProperty(UiUtils.EXCEL_INPUT);
		String outputFileName = prop.getProperty(UiUtils.EXCEL_OUTPUT);
		RuleSource ruleFiles = UiUtils.convertSourceToRuleArgs(prop);

		// Open the GUI
		RedGui player = new RedGui();
		Runnable readRun = new Thread(player);
		readRun.run();
		// player.compileRules();
		// Sanity check

		if (excelFile.equalsIgnoreCase(outputFileName)) {
			player.info("Stopping - Input and output files should not be the same");
			throw new IllegalArgumentException("Input and output files should not be the same");
		} else {

			try {
				// Open the inputfile as a stream
				player.info("Opening Excel Input file:" + excelFile);
				FileInputStream excelInput = new FileInputStream(excelFile);

				// Call the rules using this Excel datafile
				player.info("Running Rules:" + ruleFiles);
				HSSFWorkbook wb = commonUtils.callRules(excelInput, ruleFiles, RedConstants.EXCEL_LOG_WORKSHEET_NAME);

				// delete the outputFile if it exists
				UiUtils.deleteOutputFileIfExists(outputFileName);

				// Open the outputfile as a stream
				player.info("Opening Excel Output file:" + outputFileName);
				FileOutputStream excelOutput = new FileOutputStream(outputFileName);

				wb.write(excelOutput);

				player.info("Complete");

			} catch (Throwable t) {
				player.exception("Uncaught Exception", t);
			}
		}

	}


}