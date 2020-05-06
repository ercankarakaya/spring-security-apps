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
		        .withUser(users.username("veli").password("333").roles("PERSONEL"))
				.withUser(users.username("ali").password("222").roles("PERSONEL","MANAGER"))
				 .withUser(users.username("ercan").password("111").roles("PERSONEL","ADMIN"));

		// super.configure(auth);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		  .authorizeRequests()
		        .antMatchers("/").hasRole("PERSONEL")
		        .antMatchers("/leaders/**").hasRole("MANAGER")
		        .antMatchers("/systems/**").hasRole("ADMIN")
		      .and()
		        .formLogin()
		  		.loginPage("/loginForm")
		  		.loginProcessingUrl("/authenticateTheUser")
		  		.permitAll()
		      .and()
		         .logout()
		         .permitAll()
		         .and()
		         .exceptionHandling().accessDeniedPage("/access-denied");

	}

}
