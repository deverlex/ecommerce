package vn.needy.ecommerce.api.v1.user.service;

import java.util.LinkedList;

import com.google.firebase.tasks.OnFailureListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.tasks.OnSuccessListener;

import org.springframework.web.context.request.async.DeferredResult;
import vn.needy.ecommerce.api.base.ResponseCode;
import vn.needy.ecommerce.api.v1.user.request.RegisterUserRequest;
import vn.needy.ecommerce.api.v1.user.request.UpdateUserInfoRequest;
import vn.needy.ecommerce.api.v1.user.response.CertificationResponse;
import vn.needy.ecommerce.api.v1.user.response.FindCompanyResponse;
import vn.needy.ecommerce.api.v1.user.response.UserResponse;
import vn.needy.ecommerce.common.utils.TextUtils;
import vn.needy.ecommerce.domain.entity.User;
import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.model.factory.UserLicenseFactory;
import vn.needy.ecommerce.model.json.UserJson;
import vn.needy.ecommerce.api.v1.user.request.ResetPasswordRequest;
import vn.needy.ecommerce.repository.CompanyStaffRepository;
import vn.needy.ecommerce.repository.UserRepository;
import vn.needy.ecommerce.security.TokenUtils;

@Service("usersService")
public class UserServiceImpl implements UserService {

	@Value("${needy.token.prefix}")
	private String tokenPrefix;
	
	@Autowired
	private UserRepository usersRepo;

	@Autowired
	private CompanyStaffRepository companyStaffRepo;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public void registerUser(DeferredResult result, RegisterUserRequest registerInfo, Device device) {
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
									UserLicenseFactory.create(user, new LinkedList<>()), device);
							result.setResult(new CertificationResponse(token));
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
									+ tokenUtils.generateToken(UserLicenseFactory.create(user, new LinkedList<>()), device);


							result.setResult(new CertificationResponse(token));
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
            UserResponse userResponse = new UserResponse();
            userResponse.setUser(new UserJson());
            return userResponse;
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
	public BaseResponse findCompany(long userId) {
		long companyId = companyStaffRepo.findCompanyStaffByUserId(userId);
		if (companyId != -1) {
			return new FindCompanyResponse(companyId);
		}
		return new BaseResponse(BaseResponse.ERROR,
				ResponseCode.NO_CONTENT, "Do not have a company");
	}

}
