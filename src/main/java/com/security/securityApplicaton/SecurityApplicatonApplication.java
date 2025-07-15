package com.security.securityApplicaton;

import com.security.securityApplicaton.config.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EntityScan("com.securityApplication.models")
@EnableConfigurationProperties(JwtConfig.class)
public class SecurityApplicatonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplicatonApplication.class, args);
	}

}
