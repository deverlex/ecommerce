package vn.needy.ecommerce.api.v1.user.service;

import org.springframework.mobile.device.Device;

import org.springframework.web.context.request.async.DeferredResult;
import vn.needy.ecommerce.api.v1.user.request.LoginReq;
import vn.needy.ecommerce.api.v1.user.request.RegisterUserReq;
import vn.needy.ecommerce.api.v1.user.request.UpdateUserInfoReq;
import vn.needy.ecommerce.api.base.ResponseWrapper;
import vn.needy.ecommerce.api.v1.user.request.ResetPasswordReq;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

	ResponseWrapper login(LoginReq request, Device device);

	ResponseWrapper refresh(HttpServletRequest request);
	
	void registerUser(DeferredResult result, RegisterUserReq registerUserReq, Device device);
	
	ResponseWrapper findUserExist(String username);
	
	void resetPassword(DeferredResult result, String username, ResetPasswordReq resetPasswordReq, Device device);
	
	ResponseWrapper getUserInformation(long id);

	ResponseWrapper updateUserInformation(long id, UpdateUserInfoReq request);

	ResponseWrapper findBusinessesInformation(long userId);
}
