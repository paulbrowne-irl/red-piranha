package net.firstpartners;

import net.firstpartners.core.excel.SpreadSheetConvertorTest;
import net.firstpartners.core.word.DocumentConvertorTest;
import net.firstpartners.core.word.DocumentXConvertorTest;

/**
 * Constants used for testing - mainly where we store data
 * 
 * @author PBrowne
 *
 */
public class TestConstants {

	// Tmp rules and data file names
	public static final String XLS_DATA_FILE = "test/testdata/chocolate-data-tricky-formatting.xls";
	public static final String XLS_DATA_FILE_AS_URL = "https://github.com/paulbrowne-irl/red-piranha/blob/master/test/testdata/chocolate-data.xls?raw=true";
	
	public static final String XLSX_DATA_FILE = "test/testdata/chocolate-data-tricky-formatting.xlsx";
	
	//Sample word files
	public static final String WORDX_DATA_FILE = "test/testdata/simple-word.docx";
	public static final String WORD_DATA_FILE = "test/testdata/simple-word.doc";

	//Sample CSV file to append to
	public static final String CSV_APPEND_FILE="test/testdata/sample-csv-to-append-to.csv";
	
	
	//Sample Rules files
	public static final String[] RULES_FILES = new String[] {
			"test/testrules/log-then-modify-rules.drl" };
	
	public static final String[] RULES_FILES_IN_EXCEL = new String[] {
			"test/testrules/log-rules.drl", "test/testrules/TradingRules.xls" };
	
	public static final String RULE_FILE_FOR_WORD = "test/testrules/modify-word.drl";

	public static final String FILE_OUTPUT_FILE = "test/testdata/chocolate-output.xls";
	
	//tmp files
	public static final String EXCEL_TMP_FILE = "test/testdata/tmp.xls";
	public static final String CSV_TMP_FILE = "test/testdata/tmp.csv";
	public static final String PDF_TMP_FILE = "test/testdata/tmp.pdf";
	public static final String JSON_TMP_FILE = "test/testdata/tmp.json";
	
	public static final String KNOWLEDGE_BASE_FILE = "test/testrules/log-then-modify-rules.KnowledgeBase";
	public static final String KNOWLEDGE_BASE_FILE_TMP ="test/testrules/tmp.KnowledgeBase";

	// the name of the sheet the we log files to
	public static final String EXCEL_LOG_WORKSHEET_NAME = "log";
	
	//Serialized Test Data
	public final static String SAVED_EXCEL_RANGEHOLDER_DATA="test/testdata/RANGEHOLDER_RANGE_CELLS.testdata";
	public final static String SAVED_WORD_RANGEHOLDER_DATA="test/testdata/RANGEHOLDER_RANGE_WORD.testdata";
	public final static String SAVED_WORDX_RANGEHOLDER_DATA="test/testdata/RANGEHOLDER_RANGE_WORDX.testdata";
	
	
	/**
	 * Testing utility to generate test Data
	 */
	public static void main (String [] args) throws Exception
	{
		
		SpreadSheetConvertorTest.main(null);
		DocumentConvertorTest.main(null);
		DocumentXConvertorTest.main(null);
		
	}
}
