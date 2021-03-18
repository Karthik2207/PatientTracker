package com.tracker.patienttracker.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tracker.patienttracker.service.CustomUserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);
	@Autowired
	CustomUserDetailsService userDetailsService;


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	System.out.println("Start of Configure Method of Auth ");
        auth.userDetailsService(userDetailsService);
        System.out.println("End of Configure Method of Auth ");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("Start of Configure Method of HttpSecurity ");
		http.cors();
		http.csrf().disable().httpBasic().and().authorizeRequests()
		.antMatchers("/billing/**").hasAnyAuthority("ROLE_ADMIN","ROLE_CLERK")
		.antMatchers("/inpatientrecord/**").hasAnyAuthority("ROLE_ADMIN")
		.antMatchers("/testreport/").hasAnyAuthority("ROLE_ADMIN")
		.antMatchers("/testreport/requested").hasAnyAuthority("ROLE_ADMIN")
		.antMatchers("/patient/getallpatients").hasAnyAuthority("ROLE_ADMIN","ROLE_CLERK")
		.antMatchers("/patient/updatepatient").hasAnyAuthority("ROLE_ADMIN","ROLE_CLERK")
		.antMatchers("/treatment/update").hasAnyAuthority("ROLE_ADMIN","ROLE_DOCTOR","ROLE_PATIENT")
		.antMatchers("/patient/details").hasAnyAuthority("ROLE_PATIENT","ROLE_CLERK")
		.antMatchers("patient/patientrecord/**").hasAnyAuthority("ROLE_PATIENT","ROLE_CLERK")
		.antMatchers("/patientrecord/**").hasAnyAuthority("ROLE_DOCTOR")
		.antMatchers("/users/help").hasAnyAuthority("ROLE_ADMIN","ROLE_DOCTOR","ROLE_PATIENT","ROLE_CLERK")
		.antMatchers("/users/login").permitAll()
		.antMatchers("/users/registration").permitAll()
		.antMatchers("/doctors/**").permitAll()
		.antMatchers("/auth/forgotpassword").permitAll()
		.antMatchers("/auth/forgotuserid").permitAll()
		.antMatchers("/auth/resetpassword").hasAnyAuthority("ROLE_ADMIN","ROLE_DOCTOR","ROLE_PATIENT","ROLE_CLERK")
		.anyRequest().authenticated();
		
		 http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
		 System.out.println("End of Configure Method of HttpSecurity ");
	}
	
	  	@Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	  		System.out.println("AuthenticationManager Bean");
	        return super.authenticationManagerBean();
	    }

	    @Bean
	    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
	    	System.out.println("JwtAuthenticationFilter Bean");
	        return new JwtAuthenticationFilter();
	    }
	
	
}
