package com.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Devendra
 *
 */

@EnableCaching
@SpringBootApplication
public class SpringBootCacheRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCacheRestApiApplication.class, args);
	}

}
