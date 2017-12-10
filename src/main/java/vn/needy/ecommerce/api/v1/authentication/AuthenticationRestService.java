package vn.needy.ecommerce.api.v1.authentication;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.api.v1.authentication.request.LoginRequest;
import vn.needy.ecommerce.api.v1.authentication.service.AuthenticationService;

@RestController
public class AuthenticationRestService {

	@Autowired
	private AuthenticationService authenticationService;

	@RequestMapping(value = "${needy.route.securities.authentications}", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> authentications(
			@RequestBody LoginRequest credentials, Device device) {
		BaseResponse cert = authenticationService.authentication(credentials, device);
        return ResponseEntity.ok(cert);
	}
	
	@RequestMapping(value = "${needy.route.securities.refreshments}", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> authenticationRefreshment(HttpServletRequest request) {
		BaseResponse cert = authenticationService.authenticationRefresh(request);
		
		if (cert != null) return ResponseEntity.ok(cert);
		else return ResponseEntity.badRequest().body(null);
	}
	
}
