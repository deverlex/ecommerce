package vn.needy.ecommerce.service.impl;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.tasks.OnCompleteListener;
import com.google.firebase.tasks.OnSuccessListener;
import com.google.firebase.tasks.Task;

import vn.needy.ecommerce.common.model.Lock;
import vn.needy.ecommerce.domain.entity.User;
import vn.needy.ecommerce.model.base.BaseResponse;
import vn.needy.ecommerce.model.factory.UserLicenseFactory;
import vn.needy.ecommerce.model.json.request.RegisterUserRequest;
import vn.needy.ecommerce.model.json.request.ResetPasswordRequest;
import vn.needy.ecommerce.model.json.response.CertificationResponse;
import vn.needy.ecommerce.repository.UserRepository;
import vn.needy.ecommerce.security.TokenUtils;
import vn.needy.ecommerce.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Value("${needy.token.prefix}")
	private String tokenPrefix;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public CertificationResponse registerUser(RegisterUserRequest registerInfo, Device device) {
		if (!isVerifiedByFirebase(registerInfo.getFirebaseUid(), registerInfo.getFirebaseToken()) ) {
			return new CertificationResponse(null, "Phone number is not valid");
		}
		User userExist = userRepository.findUserExistByUsername(registerInfo.getUsername());
		if (userExist != null) {
			String message = "This phone number has been registered by " + userExist.getFullName();
			return new CertificationResponse(null, message);
		}
		registerInfo.setPassword(passwordEncoder.encode(registerInfo.getPassword()));
		userRepository.registerUser(registerInfo);
		User user = new User();
		user.setUsername(registerInfo.getUsername());
		String token = tokenPrefix + " " + tokenUtils.generateToken(UserLicenseFactory.create(user, new LinkedList<>()), device);
		return new CertificationResponse(token);
	}
	
	private boolean isVerifiedByFirebase(String uid, String accessToken) {
		Lock lock = new Lock(true);
		StringBuilder fbUid = new StringBuilder();
		FirebaseAuth.getInstance().verifyIdToken(accessToken)
		    .addOnSuccessListener(new OnSuccessListener<FirebaseToken>() {
		        @Override
		        public void onSuccess(FirebaseToken decodedToken) {
		            fbUid.append(decodedToken.getUid());
		        }
		    }).addOnCompleteListener(new OnCompleteListener<FirebaseToken>() {
				@Override
				public void onComplete(Task<FirebaseToken> task) {
					lock.setAsync(false);
				}
			});
		while(lock.isAsync()) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
			}
		}
		return uid.equals(fbUid.toString());
	}

	@Override
	public BaseResponse findUserExist(String username) {
		User user = userRepository.findUserExistByUsername(username);
		
		if (user != null) {
			BaseResponse response = new BaseResponse();
			response.setMessage("Account registered by " + user.getFullName());
			return response;
		}
		return null;
	}

	@Override
	@Transactional
	public CertificationResponse resetPassword(String username, ResetPasswordRequest resetPasswordRequest, Device device) {
		User user = userRepository.findUserByUsernameForResetPassword(username);
		if (user == null) {
			return new CertificationResponse(null, "Phone number is not valid");
		}
		
		if (!isVerifiedByFirebase(user.getFirebaseUid(), resetPasswordRequest.getFirebaseToken())) {
			return new CertificationResponse(null, "Phone number is not valid");
		}
		String encodePassword = passwordEncoder.encode(resetPasswordRequest.getPassword());
		userRepository.updatePasswordByUserId(user.getId(), encodePassword);
		user.setUsername(username);
		String token = tokenPrefix + " " + tokenUtils.generateToken(UserLicenseFactory.create(user, new LinkedList<>()), device);
		return new CertificationResponse(token);
	}
}
