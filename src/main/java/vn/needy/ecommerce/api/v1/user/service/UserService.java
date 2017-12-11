package vn.needy.ecommerce.api.v1.user.service;

import org.springframework.mobile.device.Device;

import org.springframework.web.context.request.async.DeferredResult;
import vn.needy.ecommerce.api.v1.user.request.LoginReq;
import vn.needy.ecommerce.api.v1.user.request.RegisterUserReq;
import vn.needy.ecommerce.api.v1.user.request.UpdateUserInfoRequest;
import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.api.v1.user.request.ResetPasswordRequest;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

	BaseResponse login(LoginReq request, Device device);

	BaseResponse refresh(HttpServletRequest request);
	
	void registerUser(DeferredResult result, RegisterUserReq registerUserReq, Device device);
	
	BaseResponse findUserExist(String username);
	
	void resetPassword(DeferredResult result, String username, ResetPasswordRequest resetPasswordRequest, Device device);
	
	BaseResponse getUserInformation(long id);

	BaseResponse updateUserInformation(long id, UpdateUserInfoRequest request);

	BaseResponse findBusinessId(long userId);
}
