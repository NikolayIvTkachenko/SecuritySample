package com.rsh.SecurityApp.SecuritySample.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// TODO Auto-generated method stub
//		super.configure(auth);
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/", "index", "/css/*", "/js/*")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		
		
		UserDetails nickNikolson = User.builder()
				.username("nicknikolson")
				.password(passwordEncoder.encode("password"))
				.roles(ApplicationUserRole.STUDENT.name())
				.build();
		UserDetails ivanPetrov = User.builder()
				.username("ivanpetrov")
				.password(passwordEncoder.encode("password"))
				.roles(ApplicationUserRole.ADMIN.name())
				.build();
		
		UserDetails hannaGibson = User.builder()
				.username("ivhannagibson")
				.password(passwordEncoder.encode("password"))
				.roles(ApplicationUserRole.USER.name())
				.build();
		
		
		
		
//		UserDetails nickNikolson = User.builder()
//				.username("nicknikolson")
//				.password(passwordEncoder.encode("password"))
//				.roles("STUDENT")
//				.build();
//		
//		
//		UserDetails ivanPetrov = User.builder()
//				.username("ivanpetrov")
//				.password(passwordEncoder.encode("password"))
//				.roles("ADMIN")
//				.build();
//		
//		UserDetails hannaGibson = User.builder()
//				.username("ivhannagibson")
//				.password(passwordEncoder.encode("password"))
//				.roles("USER")
//				.build();
		
		return new InMemoryUserDetailsManager(nickNikolson, ivanPetrov, hannaGibson);
	}
	
	
	

}
