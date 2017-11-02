package vn.needy.ecommerce.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.needy.ecommerce.model.json.request.CredentialsRequest;
import vn.needy.ecommerce.model.json.response.CertificationResponse;
import vn.needy.ecommerce.service.AuthenticationsService;

@RestController
public class AuthenticationRestService {

	@Autowired
	private AuthenticationsService authenticationsService;

	@RequestMapping(value = "${needy.route.securities.authentications}", method = RequestMethod.POST)
	public ResponseEntity<CertificationResponse> authentications(
			@RequestBody CredentialsRequest credentials, Device device) {
		CertificationResponse cert = authenticationsService.authentication(credentials, device);
        return ResponseEntity.ok(cert);
	}
	
	@RequestMapping(value = "${needy.route.securities.refreshments}", method = RequestMethod.GET)
	public ResponseEntity<CertificationResponse> authenticationRefreshment(HttpServletRequest request) {
		CertificationResponse cert = authenticationsService.authenticationRefresh(request);
		
		if (cert != null) return ResponseEntity.ok(cert);
		else return ResponseEntity.badRequest().body(null);
	}
	
}
