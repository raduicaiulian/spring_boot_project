package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
@ComponentScan(basePackages = { "com.example" })
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.antMatchers("/").permitAll()
				//.antMatchers("/list-products", "/find-product").hasRole("USER")
				.antMatchers("/add-produc", "/change-price", "/remove-product",
							"/add-account", "/remove-account", "list-aconts").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
			//withDefaults is a shortcut for "it -> {}", 
			//source: https://spring.io/blog/2019/11/21/spring-security-lambda-dsl
 			.formLogin(it -> {})
			.logout((logout) -> logout.permitAll());
		
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		List<UserDetails> userDetailsList = new ArrayList<>();
		userDetailsList.add(User
				.withUsername("user")
				.password(passwordEncoder()
				.encode("user"))
				.roles("USER").build());
		userDetailsList.add(User
				.withUsername("admin")
				.password(passwordEncoder()
				.encode("admin"))
				.roles("ADMIN","USER").build());

		return new InMemoryUserDetailsManager(userDetailsList);
	}
}