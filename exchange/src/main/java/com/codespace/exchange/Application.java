package com.codespace.exchange;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ugureli
 */
@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		System.out.println();
		SpringApplication.run(Application.class, args);
	}

	@PostConstruct
	private void init() {
		log.info("It is starting..");
	}
}
