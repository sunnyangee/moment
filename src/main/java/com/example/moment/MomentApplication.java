package com.example.moment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.moment.config.PasswordProperties;


@SpringBootApplication
@EnableConfigurationProperties(PasswordProperties.class)
public class MomentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MomentApplication.class, args);
	}
	
}
