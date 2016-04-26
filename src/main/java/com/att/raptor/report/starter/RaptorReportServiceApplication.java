package com.att.raptor.report.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:*application-context.xml")
public class RaptorReportServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RaptorReportServiceApplication.class, args);
	}
}
