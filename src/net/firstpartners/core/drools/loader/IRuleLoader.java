package net.firstpartners.core.drools.loader;

import java.io.IOException;

import org.drools.compiler.compiler.DroolsParserException;

/**
 * Marks a class as being able to load rules on behalf of the system
 * @author PBrowne
 *
 */
public interface IRuleLoader {

	org.drools.KnowledgeBase loadRules(RuleSource ruleSource) throws DroolsParserException, IOException, ClassNotFoundException;


}