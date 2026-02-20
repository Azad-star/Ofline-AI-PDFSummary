package com.Summarize.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = { "com.Summarize" })
@ComponentScan(basePackages = { "com.Summarize" })
@EnableJpaRepositories(basePackages = { "com.Summarize" })
@SpringBootApplication
public class AiSummarizeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiSummarizeApplication.class, args);
	}

}
