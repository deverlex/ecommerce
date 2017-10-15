package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.entity.User;
import vn.needy.ecommerce.model.json.request.RegisterUserRequest;

public interface UserRepository {
	
	User findUserByUsernameForAuthenticate(String username);
	
	boolean updateUserState(long id, int state);
	
	User findUserExistByUsername(String username);
	
	long registerUser(RegisterUserRequest registerUserRequest);
	
	User findUserForResponseById(long id);
}
