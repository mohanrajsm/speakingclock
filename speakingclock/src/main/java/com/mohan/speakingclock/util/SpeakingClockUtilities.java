package com.mohan.speakingclock.util;

public class SpeakingClockUtilities {

	public static boolean timePatternMatch(String time) {
		
		if(time == null)
			return false;
		else if (!time.matches("^([01][0-9]|2[0-3]):([0-5][0-9])$"))
			return false;
		
		return true;
		
	}
	

}
