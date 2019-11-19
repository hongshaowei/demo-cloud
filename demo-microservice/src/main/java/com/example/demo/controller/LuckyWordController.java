package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/api")
public class LuckyWordController {

	@Value("${wordConfig.luckyWord}")
	String luckyWord;
	
	
	@GetMapping("/luckyWord")
	public String showLuckyWord() {
		return "The lucky word is " + luckyWord;
	}
	
}
