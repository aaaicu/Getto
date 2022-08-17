package com.yummythings.getto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableCaching
@SpringBootApplication
public class GettoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GettoApplication.class, args);
	}
	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}
}
