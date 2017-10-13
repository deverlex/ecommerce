package vn.needy.ecommerce.repository;

import java.util.List;

public interface UserRoleRepository {
	List<String> findRoleStringByUserId(long userId);
}
