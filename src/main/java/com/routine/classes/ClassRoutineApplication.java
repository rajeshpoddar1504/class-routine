package com.routine.classes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@SpringBootApplication
@EnableWebSecurity
public class ClassRoutineApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ClassRoutineApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	      return builder.sources(ClassRoutineApplication.class);
	  }
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
        	.authorizeRequests()
        	.antMatchers("/class-routine/**")
        	.permitAll()
            .antMatchers("/admin/**")
            .authenticated()
            .and()
            .formLogin()
            .failureHandler(authenticationFailureHandler());
        return http.build();
    }
	
	@Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }
	

}
