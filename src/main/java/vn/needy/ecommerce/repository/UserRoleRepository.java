package vn.needy.ecommerce.repository;

import java.util.List;

public interface UserRoleRepository {
	
	List<String> findRoleAuthenticationByUserId(long userId);
	
	long resgisterUserRole(long userId, String role, long createdBy);
	
	int registerUserListRole(long userId, String[] roles, long createdBy);
}
