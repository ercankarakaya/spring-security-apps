package com.ercan.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		UserBuilder users = User.withDefaultPasswordEncoder();
		auth.inMemoryAuthentication()
		        .withUser(users.username("ercan").password("ercan").roles("ADMIN"))
				.withUser(users.username("ali").password("can").roles("PERSONEL"))
				.withUser(users.username("veli").password("yilmaz").roles("PERSONEL"));

		// super.configure(auth);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		  .authorizeRequests()
		      .anyRequest()
		      .authenticated();
				
		http
		  .formLogin()
		  		.loginPage("/loginForm")
		  		.loginProcessingUrl("/authenticateTheUser")
		  		.permitAll();
		
		http.logout().permitAll();

	}

}
