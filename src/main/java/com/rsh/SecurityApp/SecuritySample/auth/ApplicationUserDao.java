package com.rsh.SecurityApp.SecuritySample.auth;

import java.util.Optional;

public interface ApplicationUserDao {
	
	Optional<ApplicationUser> selectApplicationUserByUsername(String username);

}
