package vn.needy.ecommerce.api.v1.user.service;

import org.springframework.mobile.device.Device;

import org.springframework.web.context.request.async.DeferredResult;
import vn.needy.ecommerce.api.v1.user.request.RegisterUserRequest;
import vn.needy.ecommerce.api.v1.user.response.CertificationResponse;
import vn.needy.ecommerce.api.v1.user.response.UserResponse;
import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.api.v1.user.request.ResetPasswordRequest;

public interface UserService {
	
	void registerUser(DeferredResult result, RegisterUserRequest registerUserRequest, Device device);
	
	BaseResponse findUserExist(String username);
	
	void resetPassword(DeferredResult result, String username, ResetPasswordRequest resetPasswordRequest, Device device);
	
	UserResponse getUserInformation(long id);
	
}