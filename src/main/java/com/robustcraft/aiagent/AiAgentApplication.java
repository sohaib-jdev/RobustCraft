package com.robustcraft.aiagent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.robustcraft")

public class AiAgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiAgentApplication.class, args);
	}

}
