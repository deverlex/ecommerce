package vn.needy.ecommerce.service;

import org.springframework.mobile.device.Device;

import vn.needy.ecommerce.model.base.BaseResponse;
import vn.needy.ecommerce.model.json.request.ActiveAccountRequest;
import vn.needy.ecommerce.model.json.request.RegisterUserRequest;
import vn.needy.ecommerce.model.json.request.ResetPasswordRequest;
import vn.needy.ecommerce.model.json.response.CertificationResponse;
import vn.needy.ecommerce.model.json.response.UserResponse;

public interface UsersService {
	
	CertificationResponse registerUser(RegisterUserRequest registerUserRequest, Device device);
	
	BaseResponse findUserExist(String username);
	
	CertificationResponse resetPassword(String username, ResetPasswordRequest resetPasswordRequest, Device device);
	
	UserResponse getUserInfomation(long id);
	
	BaseResponse activeAccount(long userId, ActiveAccountRequest request);
}
