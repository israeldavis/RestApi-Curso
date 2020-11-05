package com.vences.rest.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@GetMapping("helloword")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/hello-int")
	public String getMessagesInI18nFormat(@RequestHeader(name="Accept-Language", required=false) String locale) {
		return messageSource.getMessage("label.hello", null,  new Locale(locale));
	}
	
	@GetMapping("/hello-int2")
	public String getMessagesInI18nFormat2() {
		return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
	}
	

}
