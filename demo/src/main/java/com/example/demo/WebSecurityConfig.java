package com.example.demo;

import java.beans.Customizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
//		authenticationMgr.inMemoryAuthentication()
//			.withUser("user").password("user").authorities("ROLE_USER")
//			.and()
//			.withUser("admin").password("admin").authorities("ROLE_USER","ROLE_ADMIN");
//	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.antMatchers("/", "/find-product").permitAll()
				.antMatchers("/list-products").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
//			.formLogin((form) -> form
//				.loginPage("/login")
//				.permitAll()
//			)
			//withDefaults is a shorcut for "it -> {}", 
			//source: https://spring.io/blog/2019/11/21/spring-security-lambda-dsl
 			.formLogin(it -> {})
			.logout((logout) -> logout.permitAll());
		
//		http.auth?
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")
				.password("user")
				.roles("USER")
				.build();
		
 		UserDetails admin = User.withDefaultPasswordEncoder()
 	 			.username("admin")
 	 			.password("user")
 	 			.roles("ADMIN", "USER")
 	 			.build();

		return new InMemoryUserDetailsManager(user, admin);
	}
	
}