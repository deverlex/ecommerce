package vn.needy.ecommerce.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.needy.ecommerce.model.base.BaseResponse;
import vn.needy.ecommerce.model.json.request.RegisterUserRequest;
import vn.needy.ecommerce.model.json.request.ResetPasswordRequest;
import vn.needy.ecommerce.model.json.response.CertificationResponse;
import vn.needy.ecommerce.security.IdentificationUtils;
import vn.needy.ecommerce.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;


@RestController
public class UserRestService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private IdentificationUtils idUtils;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "${needy.route.user.register}", method = RequestMethod.POST)
	public ResponseEntity<CertificationResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest, Device device) {
		CertificationResponse cert = userService.registerUser(registerUserRequest, device);
		return ResponseEntity.ok(cert);
	}
	
	@RequestMapping(value = "${needy.route.user.find}", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> findUserExist(@RequestParam(value = "username", required = true) String username) {
		BaseResponse response = userService.findUserExist(username);
		return ResponseEntity.ok(response);
	}
	
	@RequestMapping(value = "${needy.route.user.password.reset}", method = RequestMethod.POST)
	public ResponseEntity<CertificationResponse> resetPassword(@RequestParam(value = "username", required = true) String username,
			@RequestBody ResetPasswordRequest resetPasswordRequest, Device device) {
		System.out.println(username);
		CertificationResponse cert = userService.resetPassword(username, resetPasswordRequest, device);
		return ResponseEntity.ok(cert);
	}

	
	@RequestMapping(value = {""})
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> demo(HttpServletRequest request) {
		Long idUser = idUtils.getIdentification(request);
		return new ResponseEntity<String>("UserId: " + idUser, HttpStatus.OK);
	}
}
