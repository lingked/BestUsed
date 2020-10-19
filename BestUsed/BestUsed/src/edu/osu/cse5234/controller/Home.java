package edu.osu.cse5234.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller()
@RequestMapping("/")
public class Home {
	@RequestMapping(method = RequestMethod.GET)
	public String visitHome() {
		return "Home";
	}
	
	@RequestMapping(path="/about", method = RequestMethod.GET)
	public String visitAbout() {
		return "AboutUs";
	}
	
	@RequestMapping(path="/contact", method = RequestMethod.GET)
	public String visitContact() {
		return "ContactUs";
	}
}
