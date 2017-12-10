package vn.needy.ecommerce.api.v1.authentication.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.api.v1.authentication.request.LoginRequest;

public interface AuthenticationService {
	
	BaseResponse authentication(LoginRequest credentials, Device device);

	BaseResponse authenticationRefresh(HttpServletRequest request);
}
