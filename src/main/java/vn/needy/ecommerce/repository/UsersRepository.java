package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.entity.User;
import vn.needy.ecommerce.model.json.request.ActiveAccountRequest;
import vn.needy.ecommerce.model.json.request.RegisterUserRequest;

public interface UsersRepository {
	
	// Get user for authenticate
	User findUserByUsernameForAuthenticate(String username);
	
	// Update state user
	boolean updateUserState(long id, int state);
	
	// Check user exist for: forget password
	String findUserExistByUsername(String username);
	
	// register new user
	long registerUser(RegisterUserRequest registerUserRequest);
	
	// get info of user
	User findUserById(long id);
	
	User findUserByUsernameForResetPassword(String username);
	
	boolean updatePasswordByUserId(long id, String password);
	
	int activeAccount(long userId, ActiveAccountRequest request);
}
