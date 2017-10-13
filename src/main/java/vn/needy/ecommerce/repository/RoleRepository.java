package vn.needy.ecommerce.repository;

import java.util.List;

public interface RoleRepository {
	List<String> findRoleStringByUserId(long userId);
}
