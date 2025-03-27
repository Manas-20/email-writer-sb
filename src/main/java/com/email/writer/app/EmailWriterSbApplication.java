package com.email.writer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.email.writer.app"})
public class EmailWriterSbApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailWriterSbApplication.class, args);
	}

}

