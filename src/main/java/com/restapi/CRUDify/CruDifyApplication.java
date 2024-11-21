package com.restapi.CRUDify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Spring Boot application. This class contains the main
 * method which is used to launch the application. It is annotated with
 * {@link SpringBootApplication}, which is a convenience annotation that
 * includes @Configuration, @EnableAutoConfiguration, and @ComponentScan.
 */
@SpringBootApplication
public class CruDifyApplication {

	/**
	 * The main method that starts the Spring Boot application. This method triggers
	 * the auto-configuration and component scanning of Spring Boot and runs the
	 * application.
	 *
	 * @param args The command-line arguments passed to the application (if any).
	 */
	public static void main(String[] args) {
		SpringApplication.run(CruDifyApplication.class, args);
	}

}
