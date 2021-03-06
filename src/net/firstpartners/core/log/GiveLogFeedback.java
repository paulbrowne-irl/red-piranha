package net.firstpartners.core.log;

import org.apache.log4j.Logger;

/**
 * Marks as class as being able to display a pre and post rules snapshot of Data as feedback to users.
 * Often this is used as to update a GUI or something similar.
 * 
 * @author PBrowne
 *
 */
public class GiveLogFeedback implements IGiveFeedbackToUsers{

	// Handle to the logger
	private static final Logger log = RpLogger.getLogger(GiveLogFeedback.class.getName());

	
	/**
	 * Allows us to notify the user of a snapshot post rules
	 * @param message
	 */
	public void showPreRulesSnapShot(Object dataToSnapshotToUser) {
		log.info(dataToSnapshotToUser);
	}
	

	/**
	 * Allows us to notify the user of a snapshot post rules
	 * @param message
	 */
	public void showPostRulesSnapShot(Object dataToSnapshotToUser) {
		log.info(dataToSnapshotToUser);
	}
	
	/**
	 * Notifies the user of percentage progress made
	 * @param percentProgressMade 0 to 100
	 */
	public void notifyProgress(int percentProgressMade) {
		
		assert percentProgressMade>=0;
		String str = "I";
		String repeated = str.repeat(percentProgressMade);
		
		log.info(repeated);
	}
	
	/**
	 * Notifies us if we want Provide a visual notification that an Exception has occured
	 */
	public void notifyExceptionOccured() {
		log.warn("EXCEPTION!");
	}

	

}