package com.tcs;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jrtp022MiniRegistrationFormApplication{
	
	private static Logger LOG = LoggerFactory.getLogger(Jrtp022MiniRegistrationFormApplication.class);

	public static void main(String[] args) {

		
		LOG.info("*******************STARTING : Spring boot application starting");

		SpringApplication.run(Jrtp022MiniRegistrationFormApplication.class, args);

		LOG.info("********************STOPPED  : Spring boot application stopped");
		
	}
}
