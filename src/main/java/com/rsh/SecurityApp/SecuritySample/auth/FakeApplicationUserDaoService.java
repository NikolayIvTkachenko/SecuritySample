package com.rsh.SecurityApp.SecuritySample.auth;

import java.util.List;
import java.util.Optional;

import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.rsh.SecurityApp.SecuritySample.security.ApplicationUserRole;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao {

	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
		System.out.println("");
		System.out.println("selectApplicationUserByUsername ");
		System.out.println("username = "+username);
		
		Optional<ApplicationUser> userOptional = getApplicationUsers()
				.stream()
				.filter(applicationUser -> 
					applicationUser.getUsername().equals(username))
				.findFirst();
		
		System.out.println("userOptional = " + userOptional.get());
		
		return userOptional;
	}
		
	
	
	
	private List<ApplicationUser> getApplicationUsers(){
		List<ApplicationUser> applicationUsers = Lists.newArrayList(
				new ApplicationUser(
						"nicknikolson",
						passwordEncoder.encode("password"),
						ApplicationUserRole.STUDENT.getGrantedAuthorities(),
						true,
						true,
						true,
						true),
				new ApplicationUser("ivanpetrov",
						passwordEncoder.encode("password"),
						ApplicationUserRole.ADMIN.getGrantedAuthorities(),
						true,
						true,
						true,
						true),
				new ApplicationUser("ivhannagibson",
						passwordEncoder.encode("password"),
						ApplicationUserRole.USER.getGrantedAuthorities(),
						true,
						true,
						true,
						true)
				
				);
		return applicationUsers;
	}
	
	
//	UserDetails nickNikolson = User.builder()
//			.username("nicknikolson")
//			.password(passwordEncoder.encode("password"))
//			//.roles(ApplicationUserRole.STUDENT.name())
//			.authorities(ApplicationUserRole.STUDENT.getGrantedAuthorities())
//			.build();
//	
//	
//	UserDetails ivanPetrov = User.builder()
//			.username("ivanpetrov")
//			.password(passwordEncoder.encode("password"))
//			//.roles(ApplicationUserRole.ADMIN.name())
//			.authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
//			.build();
//	
//	
//	UserDetails hannaGibson = User.builder()
//			.username("ivhannagibson")
//			.password(passwordEncoder.encode("password"))
//			//.roles(ApplicationUserRole.USER.name())
//			.authorities(ApplicationUserRole.USER.getGrantedAuthorities())
//			.build();

}
