package com.mohan.speakingclock.service;

import org.springframework.stereotype.Service;

import com.mohan.speakingclock.util.SpeakingClockUtilities;

import jakarta.validation.ConstraintViolationException;

@Service
public class SpeakingClockServiceImpl implements SpeakingClockService{

	@Override
	public String convertTimeToWord(String time) {
		
		if(!SpeakingClockUtilities.timePatternMatch(time)) {
			throw new ConstraintViolationException("please enter proper time format HH:MM", null);
		}
		
		if(time.equals("00:00"))
			return "It's Midnight";
		else if (time.equals("12:00"))
			return "It's Midday";

		String hours = time.split(":")[0];
		String minutes = time.split(":")[1];
		
		return "It's " + convertNumberToWord(hours) + " " + convertNumberToWord(minutes);
	}
	
	private String convertNumberToWord(String number) {
		
		int num = Integer.parseInt(number);
		String[] uniqueNumbers = {"" , "one" , "two" , "three" ,"four" ,"five" ,"six" ,"seven" ,"eight"
						, "nine" , "ten", "eleven" ,"twelve" , "thirteen" , "fourteen" , "fifteen" , "sixteen"
						, "seventeen" , "eighteen" , "nineteen" 
						};
		String[] tensWord = {"" , "" , "twenty" , "thirty" , "fourty" , "fifty" };

		if(num > 0 && num < 20) {
			return uniqueNumbers[num];
		}else if (num < 60){
			int tensDigit = Integer.parseInt(number.split("")[0]);
			int firstDigit = Integer.parseInt(number.split("")[1]);
			return tensWord[tensDigit] + " " + uniqueNumbers[firstDigit];
		}
		
		return "";
	}


}
