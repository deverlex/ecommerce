package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.api.v1.user.request.RegisterUserReq;
import vn.needy.ecommerce.api.v1.user.request.UpdateUserInfoRequest;
import vn.needy.ecommerce.domain.mysql.User;

public interface UserRepository {
	
	// Get user for authenticate
	User findUserByUsernameForAuthenticate(String username);
	
	// Update state user
	boolean updateUserState(long id, int state);
	
	// Check user exist for: forget password
	String findUsernameExist(String username);
	
	// register new user
	long registerUser(RegisterUserReq registerUserReq);
	
	// get info of user
	User findUserById(long id);
	
	User findUserByUsernameForResetPassword(String username);
	
	boolean updatePasswordByUserId(long id, String password);

	boolean updateUserInformation(long id, UpdateUserInfoRequest updateUserInfoRequest);
}
