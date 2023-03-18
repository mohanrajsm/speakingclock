package com.mohan.speakingclock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mohan.speakingclock.service.SpeakingClockService;
import com.mohan.speakingclock.util.SpeakingClockUtilities;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/speakingclock")
@Validated
public class SpeakingClockController {

	@Autowired
	SpeakingClockService speakingClockService;
	
	
	@GetMapping("/timetoword/{time}")
	public String convertTimeToWord(@PathVariable 
			@NotEmpty 
			String time) {
		if(!SpeakingClockUtilities.timePatternMatch(time)) {
			throw new ConstraintViolationException("please enter proper time format HH:MM", null);
		}
		
		String timeInWords = speakingClockService.convertTimeToWord(time);
		 return timeInWords;
	}

	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	private ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
	  return new ResponseEntity<>("Validation Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
