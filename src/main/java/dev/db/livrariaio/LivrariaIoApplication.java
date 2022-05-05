package dev.db.livrariaio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EnableAutoConfiguration
public class LivrariaIoApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivrariaIoApplication.class, args);
	}

}