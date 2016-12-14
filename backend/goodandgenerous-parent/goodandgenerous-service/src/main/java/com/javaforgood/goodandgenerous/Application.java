package com.javaforgood.goodandgenerous;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableScheduling
@EnableTransactionManagement
@SpringBootApplication
public class Application {

	public static final String ROOT;

	static {
		ROOT = System.getProperty("upload-dir", System.getProperty("user.home") + "/upload");
	}

	public static void main(String[] args) throws Exception {
		SpringApplication app = new SpringApplicationBuilder(Application.class).build();
		app.run(args);

	}


}