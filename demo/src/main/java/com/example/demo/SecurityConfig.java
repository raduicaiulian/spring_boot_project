package com.example.demo;

import java.beans.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.antMatchers("/").permitAll()
				.antMatchers("/list-products", "/find-product").hasRole("USER")
				.antMatchers("/add-produc", "/change-price", "/remove-product").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
			//withDefaults is a shorcut for "it -> {}", 
			//source: https://spring.io/blog/2019/11/21/spring-security-lambda-dsl
 			.formLogin(it -> {})
			.logout((logout) -> logout.permitAll())
			
//	        .rememberMe()
//            .alwaysRemember(true)
//            .tokenValiditySeconds(30*5)
//            .rememberMeCookieName("mouni")
//            .key("somesecret")
//            	.and()
            //.csrf().disable()
			;
		
		return http.build();
	}

	@SuppressWarnings("deprecation")
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
 	 			.password("admin")
 	 			.roles("ADMIN", "USER")
 	 			.build();

		return new InMemoryUserDetailsManager(user, admin);
	}
	
}