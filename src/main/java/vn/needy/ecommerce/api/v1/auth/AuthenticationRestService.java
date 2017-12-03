package vn.needy.ecommerce.api.v1.auth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.needy.ecommerce.api.v1.user.response.CertificationResponse;
import vn.needy.ecommerce.api.v1.auth.request.CredentialRequest;
import vn.needy.ecommerce.api.v1.auth.service.AuthenticationService;

@RestController
public class AuthenticationRestService {

	@Autowired
	private AuthenticationService authenticationService;

	@RequestMapping(value = "${needy.route.securities.authentications}", method = RequestMethod.POST)
	public ResponseEntity<CertificationResponse> authentications(
            @RequestBody CredentialRequest credentials, Device device) {
		CertificationResponse cert = authenticationService.authentication(credentials, device);
        return ResponseEntity.ok(cert);
	}
	
	@RequestMapping(value = "${needy.route.securities.refreshments}", method = RequestMethod.GET)
	public ResponseEntity<CertificationResponse> authenticationRefreshment(HttpServletRequest request) {
		CertificationResponse cert = authenticationService.authenticationRefresh(request);
		
		if (cert != null) return ResponseEntity.ok(cert);
		else return ResponseEntity.badRequest().body(null);
	}
	
}