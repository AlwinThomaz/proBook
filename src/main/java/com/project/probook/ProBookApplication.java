package com.project.probook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProBookApplication extends SpringBootServletInitializer {
	
	@RequestMapping("/")
	  public String home() {
	    return "Hello Docker World";
	  }

	public static void main(String[] args) {
		SpringApplication.run(ProBookApplication.class, args);
	}

}
