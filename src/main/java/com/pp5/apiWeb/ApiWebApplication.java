package com.pp5.apiWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//quando der erro de não estar mapeado, isso daqui força a achar
public class ApiWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiWebApplication.class, args);
	}
}
