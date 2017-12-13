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
import vn.needy.ecommerce.api.v1.user.request.LoginReq;
import vn.needy.ecommerce.api.v1.user.request.RegisterUserReq;
import vn.needy.ecommerce.api.v1.user.request.UpdateUserInfoRequest;
import vn.needy.ecommerce.api.v1.user.response.TokenResponse;
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


	@RequestMapping(value = "${needy.route.v1.users.login}", method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> login(
			@RequestBody LoginReq request, Device device) {
		BaseResponse response = userService.login(request, device);
		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = "${needy.route.v1.users.refreshments}", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> refreshment(HttpServletRequest request) {
		BaseResponse response = userService.refresh(request);
		return ResponseEntity.ok(response);
	}
	
	// User can reset password when they forget
	@RequestMapping(value = "${needy.route.v1.users.reset_account}", method = RequestMethod.POST)
	public DeferredResult<TokenResponse> resetPassword(@RequestParam(value = "username", required = true) String username,
													   @RequestBody ResetPasswordRequest resetPasswordRequest, Device device) {
		DeferredResult<TokenResponse> result = new DeferredResult<>();
		userService.resetPassword(result, username, resetPasswordRequest, device);
		return result;
	}

	// Use register new account
	@RequestMapping(value = "${needy.route.v1.users.registers}", method = RequestMethod.POST)
	public DeferredResult<TokenResponse> registerUser(@RequestBody RegisterUserReq registerUserReq, Device device) {
		DeferredResult<TokenResponse> result = new DeferredResult<>();

		userService.registerUser(result, registerUserReq, device);
		return result;
	}
	
	// Sometime, user's behavior need check account is existed. Example: register/reset password
	@RequestMapping(value = "${needy.route.v1.users.find_username_exist}", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> findUsernameExist(@RequestParam(value = "username", required = true) String username) {
		BaseResponse response = userService.findUserExist(username);
		return ResponseEntity.ok(response);
	}
	
	// Get user info
	@RequestMapping(value = "${needy.route.v1.users.information_details}", method = RequestMethod.GET)
//	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<BaseResponse> getUserInformation(HttpServletRequest request) {
		long userid = idUtils.getIdentification(request);
		BaseResponse response = userService.getUserInformation(userid);
		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = "${needy.route.v1.users.update_information_details}", method = RequestMethod.PUT)
	public ResponseEntity<BaseResponse> updateUserInformation(
			HttpServletRequest request,
			@RequestBody UpdateUserInfoRequest updateRequest) {
		long userId = idUtils.getIdentification(request);
		BaseResponse response = userService.updateUserInformation(userId, updateRequest);
		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = "${needy.route.v1.users.business_information}", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> getBusinessesInformation(HttpServletRequest request) {
		long userId = idUtils.getIdentification(request);
		BaseResponse response = userService.findBusinessesInformation(userId);
		return ResponseEntity.ok(response);
	}

	@RequestMapping(value = {""})
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> demo(HttpServletRequest request) {
		Long idUser = idUtils.getIdentification(request);
		return new ResponseEntity<String>("UserId: " + idUser, HttpStatus.OK);
	}
	
}
