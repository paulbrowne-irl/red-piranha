package net.firstpartners.core.drools;

import net.firstpartners.core.drools.loader.FileRuleLoader;
import net.firstpartners.core.drools.loader.IRuleLoader;
import net.firstpartners.core.spreadsheet.ExcelInputStrategy;
import net.firstpartners.core.spreadsheet.ExcelOutputStrategy;

/**
 * Return an instance of RuleRunner, Appropriately configured with the various IDocumentStrategy etc
 * to handle the current document request
 * @author PBrowne
 *
 */
public class RuleRunnerFactory {

	/**
	 * Private Constructor, part of singleton Method
	 */
	private RuleRunnerFactory() {
	}
	
	/**
	 * Create a properly configured RuleRunner for the Input / Output file types we are passing
	 * @param ruleLoader
	 * @param outputFileName
	 * @return
	 */
	public static RuleRunner getRuleRunner(IRuleLoader ruleLoader, String outputFileName) {
		RuleRunner rules = new RuleRunner(new ExcelInputStrategy(), ruleLoader, new ExcelOutputStrategy(outputFileName));
		
		return rules;
	}
	
	/**
	 * Create a properly configured RuleRunner for the Input / Output file types we are passing
	 * @param outputFileName
	 * @return
	 */
	public static RuleRunner getRuleRunner(String outputFileName) {
		return getRuleRunner(new FileRuleLoader(),outputFileName);
	}

}
