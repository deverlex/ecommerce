package vn.needy.ecommerce.api.v1.auth.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.api.v1.user.response.CertificationResponse;
import vn.needy.ecommerce.api.v1.auth.request.CredentialRequest;

public interface AuthenticationService {
	
	BaseResponse authentication(CredentialRequest credentials, Device device);

	BaseResponse authenticationRefresh(HttpServletRequest request);
}
