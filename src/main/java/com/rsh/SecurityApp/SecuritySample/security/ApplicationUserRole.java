package com.rsh.SecurityApp.SecuritySample.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;


public enum ApplicationUserRole {
	
	STUDENT(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(
			ApplicationUserPermission.COURSE_READ, 
			ApplicationUserPermission.COURSE_WRITE,
			ApplicationUserPermission.STUDENT_READ,
			ApplicationUserPermission.STUDENT_WRITE)),
	USER(Sets.newHashSet(
			ApplicationUserPermission.STUDENT_READ,
			ApplicationUserPermission.STUDENT_WRITE));

	private final Set<ApplicationUserPermission> permissions;
	
	ApplicationUserRole(Set<ApplicationUserPermission> permissions){
		this.permissions = permissions;
	}
	
	
	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}


	public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
		Set<SimpleGrantedAuthority> permissionSGA = getPermissions().stream().map(p -> new SimpleGrantedAuthority(p.getPermission()))
			.collect(Collectors.toSet());
		permissionSGA.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		
		return permissionSGA;
	}
}
