package vn.needy.ecommerce.api.v1.user;

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

import org.springframework.web.context.request.async.DeferredResult;
import vn.needy.ecommerce.api.v1.user.request.RegisterUserRequest;
import vn.needy.ecommerce.api.v1.user.request.UpdateUserInfoRequest;
import vn.needy.ecommerce.api.v1.user.response.CertificationResponse;
import vn.needy.ecommerce.api.v1.user.response.UserResponse;
import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.api.v1.user.request.ResetPasswordRequest;
import vn.needy.ecommerce.security.IdentificationUtils;
import vn.needy.ecommerce.api.v1.user.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
public class UsersRestService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private IdentificationUtils idUtils;
	
	@Autowired
	private UserService userService;
	
	// User can reset password when they forget
	@RequestMapping(value = "${needy.route.users.reset}", method = RequestMethod.POST)
	public DeferredResult<CertificationResponse> resetPassword(@RequestParam(value = "username", required = true) String username,
			@RequestBody ResetPasswordRequest resetPasswordRequest, Device device) {
		DeferredResult<CertificationResponse> result = new DeferredResult<>();
		userService.resetPassword(result, username, resetPasswordRequest, device);
		return result;
	}

	// Use register new account
	@RequestMapping(value = "${needy.route.users.registers}", method = RequestMethod.POST)
	public DeferredResult<CertificationResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest, Device device) {
		DeferredResult<CertificationResponse> result = new DeferredResult<>();

		userService.registerUser(result, registerUserRequest, device);
		return result;
	}
	
	// Sometime, user's behavior need check account is existed. Example: register/reset password
	@RequestMapping(value = "${needy.route.users.find}", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> findUserExistence(@RequestParam(value = "username", required = true) String username) {
		BaseResponse response = userService.findUserExist(username);
		return ResponseEntity.ok(response);
	}
	
	// Get user info
	@RequestMapping(value = "${needy.route.users.information}", method = RequestMethod.GET)
//	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<UserResponse> getUserInformation(HttpServletRequest request) {
		long userid = idUtils.getIdentification(request);
		UserResponse userResponse = userService.getUserInformation(userid);
		return ResponseEntity.ok(userResponse);
	}

	@RequestMapping(value = "${needy.route.users.update_info}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUserInformation(
			HttpServletRequest request,
			@RequestBody UpdateUserInfoRequest updateRequest) {
		long userid = idUtils.getIdentification(request);


		return ResponseEntity.accepted().build();
	}


	@RequestMapping(value = {""})
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> demo(HttpServletRequest request) {
		Long idUser = idUtils.getIdentification(request);
		return new ResponseEntity<String>("UserId: " + idUser, HttpStatus.OK);
	}
	
}
