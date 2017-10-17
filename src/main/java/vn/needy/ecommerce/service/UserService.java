package vn.needy.ecommerce.service;

import org.springframework.mobile.device.Device;

import vn.needy.ecommerce.model.base.BaseResponse;
import vn.needy.ecommerce.model.json.request.RegisterUserRequest;
import vn.needy.ecommerce.model.json.response.CertificationResponse;

public interface UserService {
	
	CertificationResponse registerUser(RegisterUserRequest registerUserRequest, Device device);
	
	BaseResponse findUserExist(String username);
}
