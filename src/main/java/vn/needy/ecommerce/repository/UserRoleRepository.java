package vn.needy.ecommerce.repository;

import java.util.List;

public interface UserRoleRepository {
	List<String> findRoleAuthenticationByUserId(long userId);
}
