/**
 *
 */
package net.firstpartners.core.drools;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;

import org.drools.compiler.compiler.DroolsParserException;
import org.junit.Test;

import net.firstpartners.TestConstants;
import net.firstpartners.core.excel.ExcelOutputStrategy;

/**
 * @author paul
 *
 */
public class PreCompileRuleBuilderTest {

	// Class under test
	private final PreCompileRuleBuilder preCompileRuleBuilder = new PreCompileRuleBuilder();

	// Handle to Logger
	private static final Logger log = Logger.getLogger(PreCompileRuleBuilderTest.class.getName());



	@Test
	public final void testpreCompileRules() throws ClassNotFoundException, DroolsParserException, IOException {

		log.debug("Starting to compile rules");
		preCompileRuleBuilder.compileRule(TestConstants.RULES_FILES[0], TestConstants.KNOWLEDGE_BASE_FILE_TMP);

		// check that this exists
		File f = new File(TestConstants.KNOWLEDGE_BASE_FILE_TMP);
		assertTrue("Cannot find file that should exist", f.exists());
		f = null; // avoid any interference in the next step
		
		ExcelOutputStrategy fileUtils = new ExcelOutputStrategy(TestConstants.KNOWLEDGE_BASE_FILE_TMP);
		fileUtils.deleteOutputFileIfExists();
		
	}

}
