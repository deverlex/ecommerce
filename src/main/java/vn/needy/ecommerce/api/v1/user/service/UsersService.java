package vn.needy.ecommerce.api.v1.user.service;

import org.springframework.mobile.device.Device;

import vn.needy.ecommerce.api.v1.user.request.RegisterUserRequest;
import vn.needy.ecommerce.api.v1.user.response.CertificationResponse;
import vn.needy.ecommerce.api.v1.user.response.UserResponse;
import vn.needy.ecommerce.model.base.BaseResponse;
import vn.needy.ecommerce.api.v1.user.request.ResetPasswordRequest;

public interface UsersService {
	
	CertificationResponse registerUser(RegisterUserRequest registerUserRequest, Device device);
	
	BaseResponse findUserExist(String username);
	
	CertificationResponse resetPassword(String username, ResetPasswordRequest resetPasswordRequest, Device device);
	
	UserResponse getUserInfomation(long id);
	
}
