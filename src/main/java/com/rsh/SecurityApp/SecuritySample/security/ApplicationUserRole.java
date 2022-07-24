package com.rsh.SecurityApp.SecuritySample.security;

import java.util.Set;

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
	
}
