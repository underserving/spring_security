package com.security.securityApplicaton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EntityScan("com.securityApplication.models")
public class SecurityApplicatonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplicatonApplication.class, args);
	}

}
