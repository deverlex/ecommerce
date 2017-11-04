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
import vn.needy.ecommerce.model.json.entity.UserJson;
import vn.needy.ecommerce.model.json.request.ActiveAccountRequest;
import vn.needy.ecommerce.model.json.request.RegisterUserRequest;
import vn.needy.ecommerce.model.json.request.ResetPasswordRequest;
import vn.needy.ecommerce.model.json.response.CertificationResponse;
import vn.needy.ecommerce.model.json.response.UserResponse;
import vn.needy.ecommerce.repository.UsersRepository;
import vn.needy.ecommerce.security.TokenUtils;
import vn.needy.ecommerce.service.UsersService;

@Service("usersService")
public class UsersServiceImpl implements UsersService {

	@Value("${needy.token.prefix}")
	private String tokenPrefix;
	
	@Autowired
	private UsersRepository usersRepository;
	
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
		User userExist = usersRepository.findUserExistByUsername(registerInfo.getUsername());
		if (userExist != null) {
			String message = "This phone number has been registered";
			return new CertificationResponse(null, message);
		}
		registerInfo.setPassword(passwordEncoder.encode(registerInfo.getPassword()));
		usersRepository.registerUser(registerInfo);
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
		System.out.println(fbUid != null);
		System.out.println("uid? " + uid);
		return uid.equals(fbUid.toString());
	}

	@Override
	public BaseResponse findUserExist(String username) {
		User user = usersRepository.findUserExistByUsername(username);
		if (user != null) {
			BaseResponse response = new BaseResponse();
			response.setMessage("This phone number/account is registered");
			return response;
		}
		return null;
	}

	@Override
	@Transactional
	public CertificationResponse resetPassword(String username, ResetPasswordRequest resetPasswordRequest, Device device) {
		User user = usersRepository.findUserByUsernameForResetPassword(username);
		if (user == null) {
			return new CertificationResponse(null, "Phone number is not valid");
		}
		
		if (!isVerifiedByFirebase(user.getFirebaseUid(), resetPasswordRequest.getFirebaseToken())) {
			return new CertificationResponse(null, "Phone number is not valid");
		}
		String encodePassword = passwordEncoder.encode(resetPasswordRequest.getPassword());
		usersRepository.updatePasswordByUserId(user.getId(), encodePassword);
		user.setUsername(username);
		String token = tokenPrefix + " " + tokenUtils.generateToken(UserLicenseFactory.create(user, new LinkedList<>()), device);
		return new CertificationResponse(token);
	}

	@Override
	public UserResponse getUserInfomation(long id) {
		User user = usersRepository.findUserForResponseById(id);
		UserResponse response = new UserResponse();
		response.setUser(new UserJson(user));
		return response;
	}

	@Override
	public BaseResponse activeAccount(long userId, ActiveAccountRequest request) {
		int rowsEffect = usersRepository.activeAccount(userId, request);
		System.out.println("rowsEffect? " + rowsEffect);
		if (rowsEffect == 1) 
			return new BaseResponse();
		return null;
	}
}
