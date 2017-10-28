package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.entity.User;
import vn.needy.ecommerce.model.json.request.RegisterUserRequest;

public interface UserRepository {
	
	// Get user for authenticate
	User findUserByUsernameForAuthenticate(String username);
	
	// Update state user
	boolean updateUserState(long id, int state);
	
	User findUserExistByUsername(String username);
	
	long registerUser(RegisterUserRequest registerUserRequest);
	
	User findUserForResponseById(long id);
	
	User findUserByUsernameForResetPassword(String username);
	
	boolean updatePasswordByUserId(long id, String password);
	
}
