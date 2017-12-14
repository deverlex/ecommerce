package vn.needy.ecommerce.api.v1.user.service;

import java.util.LinkedList;

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
import vn.needy.ecommerce.api.v1.user.response.BusinessInfoResp;
import vn.needy.ecommerce.api.v1.user.response.LoginResp;
import vn.needy.ecommerce.api.v1.user.response.TokenResponse;
import vn.needy.ecommerce.api.v1.user.response.UserInfoResponse;
import vn.needy.ecommerce.common.utils.TextUtils;
import vn.needy.ecommerce.common.utils.TimeProvider;
import vn.needy.ecommerce.domain.mysql.Company;
import vn.needy.ecommerce.domain.mysql.Store;
import vn.needy.ecommerce.domain.mysql.User;
import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.model.enums.UserState;
import vn.needy.ecommerce.model.factory.NeedyUserDetailsFactory;
import vn.needy.ecommerce.model.security.NeedyUserDetails;
import vn.needy.ecommerce.model.wrapper.CompanyWrapper;
import vn.needy.ecommerce.model.wrapper.StoreWrapper;
import vn.needy.ecommerce.model.wrapper.UserWrapper;
import vn.needy.ecommerce.api.v1.user.request.ResetPasswordRequest;
import vn.needy.ecommerce.repository.CompanyRepository;
import vn.needy.ecommerce.repository.CompanyStaffRepository;
import vn.needy.ecommerce.repository.StoreRepository;
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
    private StoreRepository storeRepo;

    @Autowired
    private CompanyRepository companyRepo;

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
            return new BaseResponse<>(false, message);
        }
        final String token = tokenPrefix + " " + tokenUtils.generateToken(needyUserDetails, device);
        // get user info return to client
        User user = usersRepo.findUserById(needyUserDetails.getId());
        // wrapper user before return to client
        return new BaseResponse<LoginResp>(true, "")
                .setData(new LoginResp(new UserWrapper(user), token));
    }

    @Override
    public BaseResponse refresh(HttpServletRequest request) {
        String token = request.getHeader(this.tokenHeader).replace(tokenPrefix + " ", "");

        String username = this.tokenUtils.getUsernameFromToken(token);
        NeedyUserDetails needyUserDetails = (NeedyUserDetails) this.userDetailsService.loadUserByUsername(username);
        if (this.tokenUtils.canTokenBeRefreshed(token, needyUserDetails.getLastResetPassword())) {
            String refreshedToken = tokenPrefix + " " + this.tokenUtils.refreshToken(token);

            // Add refresh token to response
            return new BaseResponse<TokenResponse>(true, "")
                    .setData(new TokenResponse(refreshedToken));
        }
        return new BaseResponse<>(false, "Unauthorized");
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
                            result.setResult(new BaseResponse(false, "Phone number is not valid"));

                        } else {
                            String userExist = usersRepo.findUsernameExist(registerInfo.getUsername());
                            if (!TextUtils.isEmpty(userExist)) {
                                result.setResult(new BaseResponse(false, "This phone number has been registered"));
                            } else {
                                registerInfo.setPassword(passwordEncoder.encode(registerInfo.getPassword()));
                                usersRepo.registerUser(registerInfo);
                                User user = new User();
                                user.setUsername(registerInfo.getUsername());
                                String token = tokenPrefix + " " + tokenUtils.generateToken(
                                        NeedyUserDetailsFactory.create(user, new LinkedList<>()), device);
                                result.setResult(
                                        new BaseResponse<>(true, "").setData(new TokenResponse(token))
                                );
                            }
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                result.setResult(new BaseResponse<>(false, "Sorry, server has error"));
            }
        });
    }

    @Override
    public BaseResponse findUserExist(String username) {
        String userExist = usersRepo.findUsernameExist(username);
        if (!TextUtils.isEmpty(userExist)) {
            return new BaseResponse<>(true, "This phone number/account is registered");
        }
        return new BaseResponse<>(false, "Not found");
    }

    @Override
    @Transactional
    public void resetPassword(DeferredResult result, String username, ResetPasswordRequest resetPasswordRequest, Device device) {
        User user = usersRepo.findUserByUsernameForResetPassword(username);
        if (user == null) {
            result.setResult(new BaseResponse<>(false, "Phone number is not valid"));
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

                            result.setResult(new BaseResponse<>(true, "").setData(new TokenResponse(token))
                            );
                        } else {
                            result.setResult(new BaseResponse<>(false, "Phone number is not valid"));
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                result.setResult(new BaseResponse<>(false, "Sorry, server has error"));
            }
        });
    }

    @Override
    public BaseResponse getUserInformation(long id) {
        User user = usersRepo.findUserById(id);
        if (user != null) {
            return new BaseResponse<UserInfoResponse>(true, "").setData(new UserInfoResponse(new UserWrapper(user)));
        } else {
            return new BaseResponse(false, "Not found");
        }
    }

    @Override
    public BaseResponse updateUserInformation(long id, UpdateUserInfoRequest request) {
        boolean isUpdate = usersRepo.updateUserInformation(id, request);
        if (isUpdate) {
            return new BaseResponse(true, "Done");
        } else {
            return new BaseResponse(false, "Failed");
        }
    }

    @Override
    public BaseResponse findBusinessesInformation(long userId) {
        Company company = companyRepo.findOurByUserId(userId);
        Store store = storeRepo.getOurByUserId(userId);
        // check use need in an business (company & store)
        if (company == null || store == null) {
            return new BaseResponse(false, "You have not a business");
        }

        return new BaseResponse<>(true, "")
                .setData(new BusinessInfoResp(
                        new CompanyWrapper(company),
                        new StoreWrapper(store)
                ));
    }

}
