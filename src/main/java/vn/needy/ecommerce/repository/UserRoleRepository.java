package vn.needy.ecommerce.repository;

import java.util.List;

public interface UserRoleRepository {
	
	List<String> findRolePermissionByUserId(long userId);
	
	long registerUserRole(long userId, String role, long lastUpdatedBy);
	
	int registerUserListRole(long userId, String[] roles, long lastUpdatedBy);
}
