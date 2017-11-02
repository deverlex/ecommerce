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
import vn.needy.ecommerce.model.json.request.ActiveAccountRequest;
import vn.needy.ecommerce.model.json.request.RegisterUserRequest;
import vn.needy.ecommerce.model.json.request.ResetPasswordRequest;
import vn.needy.ecommerce.model.json.response.CertificationResponse;
import vn.needy.ecommerce.model.json.response.UserResponse;
import vn.needy.ecommerce.security.IdentificationUtils;
import vn.needy.ecommerce.service.UsersService;

import org.springframework.security.crypto.password.PasswordEncoder;


@RestController
public class UsersRestService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private IdentificationUtils idUtils;
	
	@Autowired
	private UsersService usersService;
	
	// User can reset password when they forget
	@RequestMapping(value = "${needy.route.users.reset}", method = RequestMethod.POST)
	public ResponseEntity<CertificationResponse> resetPassword(@RequestParam(value = "username", required = true) String username,
			@RequestBody ResetPasswordRequest resetPasswordRequest, Device device) {
		CertificationResponse cert = usersService.resetPassword(username, resetPasswordRequest, device);
		return ResponseEntity.ok(cert);
	}

	// Use register new account
	@RequestMapping(value = "${needy.route.users.registers}", method = RequestMethod.POST)
	public ResponseEntity<CertificationResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest, Device device) {
		CertificationResponse cert = usersService.registerUser(registerUserRequest, device);
		return ResponseEntity.ok(cert);
	}
	
	// Sometime, user's behavior need check account is existed. Example: register/reset password
	@RequestMapping(value = "${needy.route.users.existences}", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> findUserExistence(@RequestParam(value = "username", required = true) String username) {
		BaseResponse response = usersService.findUserExist(username);
		return ResponseEntity.ok(response);
	}
	
	// Get user info
	@RequestMapping(value = "${needy.route.users.infomation}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<UserResponse> getUserInfomation(HttpServletRequest request) {
		long userid = idUtils.getIdentification(request);
		UserResponse userResponse = usersService.getUserInfomation(userid);
		return ResponseEntity.ok(userResponse);
	}
	
	//Active user account
	@RequestMapping(value = "${needy.route.users.active}", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> activeUserAccount(HttpServletRequest request,
			@RequestBody ActiveAccountRequest activeRequest) {
		long userid = idUtils.getIdentification(request);
		BaseResponse response = usersService.activeAccount(userid, activeRequest);
		System.out.println(response != null);
		if (response != null) return ResponseEntity.ok(response); 
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = {""})
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> demo(HttpServletRequest request) {
		Long idUser = idUtils.getIdentification(request);
		return new ResponseEntity<String>("UserId: " + idUser, HttpStatus.OK);
	}
	
}
