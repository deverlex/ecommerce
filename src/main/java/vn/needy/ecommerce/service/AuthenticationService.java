package vn.needy.ecommerce.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;

import vn.needy.ecommerce.model.json.request.CredentialsRequest;
import vn.needy.ecommerce.model.json.response.CertificationResponse;

public interface AuthenticationService {
	
	CertificationResponse authentication(CredentialsRequest credentials, Device device);
	
	CertificationResponse authenticationRefresh(HttpServletRequest request);
}
