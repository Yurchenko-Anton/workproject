package com.example;

import com.example.LoadSource.property.RequestProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RequestProperty.class)
public class KyivstarSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(KyivstarSpringApplication.class, args);
	}
}
