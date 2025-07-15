package com.security.securityApplicaton.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


// here we gonna take values from the application properties to maintain the privacy of the secret key
@ConfigurationProperties(prefix = "jwt")
public record JwtConfig (String secret,long expiration){}
