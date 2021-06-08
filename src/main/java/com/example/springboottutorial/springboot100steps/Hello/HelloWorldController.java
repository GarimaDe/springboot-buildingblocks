package com.example.springboottutorial.springboot100steps.Hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

//Controller
@RestController
public class HelloWorldController {

	@Autowired
	private ResourceBundleMessageSource messageSource;
	//Simple Method
	//URL - /helloworld - Get method
//	@RequestMapping(method=RequestMethod.GET, path="/helloworld")
	@GetMapping("/helloworld")
	public String helloWorld()
	{
		return "Hello World";
	}

	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean()
	{
		return  new UserDetails("Garima","De","Kolkata");
	}
@GetMapping("/hello-int")
	public String getMessagesInI18NFormat(@RequestHeader(name="Accept-Language", required = false) String locale){
		return messageSource.getMessage("label.hello", null, new Locale(locale));
	}

	@GetMapping("/hello-int2")
	public String getMessagesInI18NFormat2(){
		return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
	}
}
