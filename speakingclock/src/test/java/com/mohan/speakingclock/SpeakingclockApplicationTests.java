package com.mohan.speakingclock;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.core.StringContains.containsString;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.mohan.speakingclock.controller.SpeakingClockController;
import com.mohan.speakingclock.service.SpeakingClockService;

@WebMvcTest(SpeakingClockController.class)
class SpeakingclockApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SpeakingClockService speakingClockService;
	
	/* Positive Cases */
	
	@Test
	public void shouldReturnMidday() throws Exception {
		when(speakingClockService.convertTimeToWord("12:00")).thenReturn("It's Midday");
		this.mockMvc.perform(get("/speakingclock/timetoword/12:00"))
		.andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("It's Midday")));
	}

	@Test
	public void shouldReturnMidnight() throws Exception {
		when(speakingClockService.convertTimeToWord("12:00")).thenReturn("It's Midnight");
		this.mockMvc.perform(get("/speakingclock/timetoword/12:00"))
		.andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("It's Midnight")));
	}

	@Test
	public void shouldReturnAMTimeInWords() throws Exception {
		when(speakingClockService.convertTimeToWord("08:39")).thenReturn("It's eight thirty nine");
		this.mockMvc.perform(get("/speakingclock/timetoword/08:39"))
		.andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("It's eight thirty nine")));
	}

	@Test
	public void shouldReturnPMTimeInWords() throws Exception {
		when(speakingClockService.convertTimeToWord("20:19")).thenReturn("It's twenty nineteen");
		this.mockMvc.perform(get("/speakingclock/timetoword/20:19"))
		.andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("It's twenty nineteen")));
	}
	
	/* Negative Cases */
	
	@Test
	public void shouldFailForNonNumeric() throws Exception {
		when(speakingClockService.convertTimeToWord("abcd")).thenReturn("please enter proper time format HH:MM");
		this.mockMvc.perform(get("/speakingclock/timetoword/abcd"))
		.andDo(print()).andExpect(status().isBadRequest())
		.andExpect(content().string(containsString("please enter proper time format HH:MM")));
	}
	
	@Test
	public void shouldFailForExcessLength() throws Exception {
		when(speakingClockService.convertTimeToWord("123456")).thenReturn("please enter proper time format HH:MM");
		this.mockMvc.perform(get("/speakingclock/timetoword/123456"))
		.andDo(print()).andExpect(status().isBadRequest())
		.andExpect(content().string(containsString("please enter proper time format HH:MM")));
	}


	@Test
	public void shouldFailForShortLength() throws Exception {
		when(speakingClockService.convertTimeToWord("156")).thenReturn("please enter proper time format HH:MM");
		this.mockMvc.perform(get("/speakingclock/timetoword/156"))
		.andDo(print()).andExpect(status().isBadRequest())
		.andExpect(content().string(containsString("please enter proper time format HH:MM")));
	}

	@Test
	public void shouldFailForWrongFormat() throws Exception {
		when(speakingClockService.convertTimeToWord("1:5")).thenReturn("please enter proper time format HH:MM");
		this.mockMvc.perform(get("/speakingclock/timetoword/1:5"))
		.andDo(print()).andExpect(status().isBadRequest())
		.andExpect(content().string(containsString("please enter proper time format HH:MM")));
	}


}
