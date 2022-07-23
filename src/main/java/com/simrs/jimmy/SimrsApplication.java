package com.simrs.jimmy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.regex.Pattern;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SimrsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimrsApplication.class, args);
	}

}
