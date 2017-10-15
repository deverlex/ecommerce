package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.entity.User;

public interface UserRepository {
	
	User findUserByUsernameForAuthenticate(String username);
}
