package com.security.securityApplicaton.config;


import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import com.security.securityApplicaton.services.MyUserDetailsService;

@Configuration
public class CustomConfigurations {
    @Autowired
    private  MyUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationConfiguration auth;

    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception {
        http.csrf(customiser -> customiser.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/register","/login")
                        .permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//                .formLogin(Customizer.withDefaults());
        return http.build();
    }
//    @Bean
//    public UserDetailsService users(){
//        UserDetails user1=User.withUsername("praveen")
//                              .password("{noop}1234")
//                              .authorities("user")
//                             .build();
//        return new InMemoryUserDetailsManager(user1);
//    }

    @Bean
    public AuthenticationProvider authenticate(){
        DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
        auth.setPasswordEncoder(new BCryptPasswordEncoder());
        auth.setUserDetailsService(userDetailsService);
        return auth;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
      return auth.getAuthenticationManager();
    }

}
