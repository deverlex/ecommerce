package vn.needy.ecommerce.api.v1.user.service;

import java.util.LinkedList;
import java.util.Map;

import com.google.firebase.tasks.OnFailureListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.tasks.OnSuccessListener;

import org.springframework.web.context.request.async.DeferredResult;
import vn.needy.ecommerce.api.base.ResponseCode;
import vn.needy.ecommerce.api.v1.user.request.LoginReq;
import vn.needy.ecommerce.api.v1.user.request.RegisterUserReq;
import vn.needy.ecommerce.api.v1.user.request.UpdateUserInfoRequest;
import vn.needy.ecommerce.api.v1.user.response.LoginResp;
import vn.needy.ecommerce.api.v1.user.response.TokenResponse;
import vn.needy.ecommerce.api.v1.user.response.BusinessIdResponse;
import vn.needy.ecommerce.api.v1.user.response.UserInfoResponse;
import vn.needy.ecommerce.common.utils.TextUtils;
import vn.needy.ecommerce.common.utils.TimeProvider;
import vn.needy.ecommerce.domain.mysql.User;
import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.model.enums.UserState;
import vn.needy.ecommerce.model.factory.NeedyUserDetailsFactory;
import vn.needy.ecommerce.model.security.NeedyUserDetails;
import vn.needy.ecommerce.model.wrapper.UserWrapper;
import vn.needy.ecommerce.api.v1.user.request.ResetPasswordRequest;
import vn.needy.ecommerce.repository.CompanyStaffRepository;
import vn.needy.ecommerce.repository.UserRepository;
import vn.needy.ecommerce.security.TokenUtils;

import javax.servlet.http.HttpServletRequest;

@Service("usersService")
public class UserServiceImpl implements UserService {

	@Value("${needy.token.prefix}")
	private String tokenPrefix;

	@Value("${needy.token.header}")
	private String tokenHeader;
	
	@Autowired
	private UserRepository usersRepo;

	@Autowired
	private CompanyStaffRepository companyStaffRepo;
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private TimeProvider timeProvider;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public BaseResponse login(LoginReq request, Device device) {
		// Perform the security
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(
						request.getUsername(),
						request.getPassword())
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// Reload password post-security so we can generate token
		final NeedyUserDetails needyUserDetails = (NeedyUserDetails) userDetailsService.loadUserByUsername(request.getUsername());

		// If user is locked, do not return an token
		if (needyUserDetails.getState() == UserState.LOCKED.getState()) {
			String message = "Your account is locked, we will unlock on "
					+ timeProvider.formatDate(needyUserDetails.getUnlockTime());
			return new BaseResponse(BaseResponse.ERROR,
					ResponseCode.NOT_IMPLEMENTED, message);
		}
		final String token = tokenPrefix  + " " + tokenUtils.generateToken(needyUserDetails, device);
		// get user info return to client
		User user = usersRepo.findUserById(needyUserDetails.getId());
		// wrapper user before return to client
		return new LoginResp(new UserWrapper(user), token);
	}

	@Override
	public BaseResponse refresh(HttpServletRequest request) {
		String token = request.getHeader(this.tokenHeader).replace(tokenPrefix + " ", "");

		String username = this.tokenUtils.getUsernameFromToken(token);
		NeedyUserDetails needyUserDetails = (NeedyUserDetails) this.userDetailsService.loadUserByUsername(username);
		if (this.tokenUtils.canTokenBeRefreshed(token, needyUserDetails.getLastResetPassword())) {
			String refreshedToken = tokenPrefix  + " " + this.tokenUtils.refreshToken(token);

			// Add refresh token to response
			//response.addHeader(tokenHeader,refreshedToken);
			return new TokenResponse(refreshedToken);
		}
		return new BaseResponse(BaseResponse.ERROR,
				ResponseCode.UNAUTHORIZED, "UNAUTHORIZED");
	}

	@Override
	@Transactional
	public void registerUser(DeferredResult result, RegisterUserReq registerInfo, Device device) {
		FirebaseAuth.getInstance().verifyIdToken(registerInfo.getFirebaseToken())
			.addOnSuccessListener(new OnSuccessListener<FirebaseToken>() {
				@Override
				public void onSuccess(FirebaseToken decodedToken) {
					// Verify token when use phone authentication
					if (!registerInfo.getFirebaseUid().equals(decodedToken.getUid())) {
						BaseResponse response = new BaseResponse(BaseResponse.ERROR,
								ResponseCode.UNAUTHORIZED, "Phone number is not valid");
						result.setResult(response);
					} else {
						String userExist = usersRepo.findUsernameExist(registerInfo.getUsername());
						if (!TextUtils.isEmpty(userExist)) {
							BaseResponse response = new BaseResponse(BaseResponse.ERROR,
									ResponseCode.NOT_IMPLEMENTED, "This phone number has been registered");
							String message = "This phone number has been registered";
							result.setResult(response);
						} else {
							registerInfo.setPassword(passwordEncoder.encode(registerInfo.getPassword()));
							usersRepo.registerUser(registerInfo);
							User user = new User();
							user.setUsername(registerInfo.getUsername());
							String token = tokenPrefix + " " + tokenUtils.generateToken(
									NeedyUserDetailsFactory.create(user, new LinkedList<>()), device);
							result.setResult(new TokenResponse(token));
						}
					}
				}
			}).addOnFailureListener(new OnFailureListener() {
				@Override
				public void onFailure(Exception e) {
					BaseResponse response = new BaseResponse(BaseResponse.ERROR,
							ResponseCode.NOT_IMPLEMENTED, "Sorry, server has error");
				}
			});
	}

	@Override
	public BaseResponse findUserExist(String username) {
		String userExist = usersRepo.findUsernameExist(username);
		BaseResponse response = new BaseResponse();
		if (!TextUtils.isEmpty(userExist)) {
			response.setMessage("This phone number/account is registered");
		} else {
			response.setStatus(BaseResponse.ERROR);
			response.setCode(ResponseCode.NO_CONTENT.getCode());
			response.setMessage("Empty");
		}

		return response;
	}

	@Override
	@Transactional
	public void resetPassword(DeferredResult result,String username, ResetPasswordRequest resetPasswordRequest, Device device) {
		User user = usersRepo.findUserByUsernameForResetPassword(username);
		if (user == null) {
			BaseResponse response = new BaseResponse(BaseResponse.ERROR,
					ResponseCode.NO_CONTENT, "Phone number is not valid");
			result.setResult(response);
		}

		FirebaseAuth.getInstance().verifyIdToken(resetPasswordRequest.getFirebaseToken())
				.addOnSuccessListener(new OnSuccessListener<FirebaseToken>() {
					@Override
					public void onSuccess(FirebaseToken decodedToken) {
						// Verify token when use phone authentication
						if (user.getFirebaseUid().equals(decodedToken.getUid())) {
							String encodePassword = passwordEncoder.encode(resetPasswordRequest.getPassword());
							usersRepo.updatePasswordByUserId(user.getId(), encodePassword);
							user.setUsername(username);
							String token = tokenPrefix + " "
									+ tokenUtils.generateToken(NeedyUserDetailsFactory.create(user, new LinkedList<>()), device);


							result.setResult(new TokenResponse(token));
						} else {
							BaseResponse response = new BaseResponse(BaseResponse.ERROR,
									ResponseCode.UNAUTHORIZED, "Phone number is not valid");
							result.setResult(response);
						}
					}
				}).addOnFailureListener(new OnFailureListener() {
			@Override
			public void onFailure(Exception e) {
				BaseResponse response = new BaseResponse(BaseResponse.ERROR,
						ResponseCode.NOT_IMPLEMENTED, "Sorry, server has error");
			}
		});
	}

    @Override
    public BaseResponse getUserInformation(long id) {
        User user = usersRepo.findUserById(id);
        if (user != null) {
            UserInfoResponse userInfoResponse = new UserInfoResponse();
            userInfoResponse.setUser(new UserWrapper(user));
            return userInfoResponse;
        } else {
            return new BaseResponse("Error", ResponseCode.ERROR);
        }
    }

    @Override
    public BaseResponse updateUserInformation(long id, UpdateUserInfoRequest request) {
        boolean isUpdate = usersRepo.updateUserInformation(id, request);
        if (isUpdate) {
            return new BaseResponse();
        } else {
            return new BaseResponse("Error", ResponseCode.ERROR);
        }
    }

	@Override
	public BaseResponse findBusinessId(long userId) {
		Map<String, Long> result = companyStaffRepo.findInfoIdByUserId(userId);
		if (result != null) {
			return new BusinessIdResponse(result.get("company_id"), result.get("store_id"));
		}
		return new BaseResponse(BaseResponse.ERROR,
				ResponseCode.NO_CONTENT, "Do not have a business establishment");
	}

}
