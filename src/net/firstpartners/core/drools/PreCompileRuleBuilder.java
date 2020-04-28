package net.firstpartners.core.drools;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.drools.KnowledgeBase;
import org.drools.compiler.compiler.DroolsParserException;

import net.firstpartners.core.drools.loader.IRuleLoaderStrategy;
import net.firstpartners.core.drools.loader.RuleDTO;
import net.firstpartners.core.log.RpLogger;


/**
 * Pre compile rules, and save them as Drools Knowledge Packages for later (faster) use)
 *
 *
 * @author paul
 *
 */
public class PreCompileRuleBuilder {

	public static String LIST_OF_DRL_FILES_TO_COMPILE="src/PreCompileRuleList.properties";


	private static final Logger log = RpLogger.getLogger(PreCompileRuleBuilder.class
			.getName());


	/**
	 * Cache the pre built knowledgebase.
	 *
	 * We encode using base64 (instead of Binary) so that it can be treated as a normal text file
	 */
	void cacheKnowledgeBase(KnowledgeBase kbToCache,
			String cacheResourceUnderName) throws IOException {

		ByteArrayOutputStream bytes= new ByteArrayOutputStream();
		org.drools.core.common.DroolsObjectOutputStream outBytes = new org.drools.core.common.DroolsObjectOutputStream(bytes);
		outBytes.writeObject( kbToCache );

		Base64 base64Code = new Base64();
		byte[] base64Bytes = base64Code.encode(bytes.toByteArray());

		FileOutputStream out = new FileOutputStream( cacheResourceUnderName );
		out.write(base64Bytes);
		out.close();

	}



	/**
	 * Load and cache the rule to a file
	 * @param key
	 * @param outputFile
	 * @throws IOException
	 * @throws DroolsParserException
	 * @throws ClassNotFoundException
	 */
	public void compileRule(String ruleLocation, String outputFile) throws DroolsParserException, IOException, ClassNotFoundException {


		log.info("Loading Knowledgebase from "+ruleLocation);

		//Get a handle to the rule loader that we will use
		IRuleLoaderStrategy ruleLoader = RuleRunnerFactory.getRuleLoader(ruleLocation);

		//Setup the source
		RuleDTO ruleSource = new RuleDTO();
		ruleSource.setRulesLocation(ruleLocation);


		KnowledgeBase kb= ruleLoader.loadRules(ruleSource);
		if(outputFile!=null){
			cacheKnowledgeBase(kb,outputFile);
			log.info("Cached Knowledgebase to "+outputFile);

		}

	}


	
	/**
	 * Read a properties file
	 * @return
	 * @throws IOException
	 */
	 Map<?,?> readProperties() throws IOException{

		// Read properties file.
		Properties properties = new Properties();
		properties.load(new FileInputStream(LIST_OF_DRL_FILES_TO_COMPILE));

		return properties;
	}

	
}
