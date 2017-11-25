package vn.needy.ecommerce.api.v1.auth.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;

import vn.needy.ecommerce.api.v1.user.response.CertificationResponse;
import vn.needy.ecommerce.api.v1.auth.request.CredentialsRequest;

public interface AuthenticationsService {
	
	CertificationResponse authentication(CredentialsRequest credentials, Device device);
	
	CertificationResponse authenticationRefresh(HttpServletRequest request);
}
