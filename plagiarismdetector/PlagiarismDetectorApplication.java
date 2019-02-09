package com.team113.plagiarismdetector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class contains method which is used to run the project application
 */
@SpringBootApplication
public class PlagiarismDetectorApplication {

	/**
	 * This is the main method of the spring boot spplication
	 * @param args conmmand line arguments passed
	 */
	public static void main(String[] args) {
		SpringApplication.run(PlagiarismDetectorApplication.class, args);
	}
}
