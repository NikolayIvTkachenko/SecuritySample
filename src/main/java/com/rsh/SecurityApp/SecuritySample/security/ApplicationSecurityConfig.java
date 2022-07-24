package com.rsh.SecurityApp.SecuritySample.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.rsh.SecurityApp.SecuritySample.auth.ApplicationUserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	private final PasswordEncoder passwordEncoder;
	private final ApplicationUserService applicationUserService;
	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService) {
		this.passwordEncoder = passwordEncoder;
		this.applicationUserService = applicationUserService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			//.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			//.and()
			.authorizeRequests()
			.antMatchers("/", "index", "/css/*", "/js/*").permitAll()
			.antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
			//because user prePostEnabled and annotation!!!!!
			.antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.name())
//			.antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.name())
//			.antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.name())
			.antMatchers("management/api/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.USER.name())
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
	}
	
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(applicationUserService);
		return provider;
	}

//	@Override
//	@Bean
//	protected UserDetailsService userDetailsService() {
//		
//		
//		UserDetails nickNikolson = User.builder()
//				.username("nicknikolson")
//				.password(passwordEncoder.encode("password"))
//				//.roles(ApplicationUserRole.STUDENT.name())
//				.authorities(ApplicationUserRole.STUDENT.getGrantedAuthorities())
//				.build();
//		
//		
//		UserDetails ivanPetrov = User.builder()
//				.username("ivanpetrov")
//				.password(passwordEncoder.encode("password"))
//				//.roles(ApplicationUserRole.ADMIN.name())
//				.authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
//				.build();
//		
//		
//		UserDetails hannaGibson = User.builder()
//				.username("ivhannagibson")
//				.password(passwordEncoder.encode("password"))
//				//.roles(ApplicationUserRole.USER.name())
//				.authorities(ApplicationUserRole.USER.getGrantedAuthorities())
//				.build();
//		
//		
//		
//		
////		UserDetails nickNikolson = User.builder()
////				.username("nicknikolson")
////				.password(passwordEncoder.encode("password"))
////				.roles("STUDENT")
////				.build();
////		
////		
////		UserDetails ivanPetrov = User.builder()
////				.username("ivanpetrov")
////				.password(passwordEncoder.encode("password"))
////				.roles("ADMIN")
////				.build();
////		
////		UserDetails hannaGibson = User.builder()
////				.username("ivhannagibson")
////				.password(passwordEncoder.encode("password"))
////				.roles("USER")
////				.build();
//		
//		return new InMemoryUserDetailsManager(nickNikolson, ivanPetrov, hannaGibson);
//	}
//	
	
	

}
