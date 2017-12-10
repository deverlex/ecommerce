package vn.needy.ecommerce.api.v1.authentication.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import vn.needy.ecommerce.api.base.BaseResponse;
import vn.needy.ecommerce.api.base.ResponseCode;
import vn.needy.ecommerce.api.v1.user.response.TokenResponse;
import vn.needy.ecommerce.common.utils.TimeProvider;
import vn.needy.ecommerce.model.enums.UserState;
import vn.needy.ecommerce.api.v1.authentication.request.LoginRequest;
import vn.needy.ecommerce.model.security.UserLicense;
import vn.needy.ecommerce.security.TokenUtils;

@Service("authenticationsService")
public class AuthenticationServiceImpl implements AuthenticationService {

	@Value("${needy.token.header}")
	private String tokenHeader;
	
	@Value("${needy.token.prefix}")
	private String tokenPrefix;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private TimeProvider timeProvider;
	
	@Override
	public BaseResponse authentication(LoginRequest credentials, Device device) {
		// Perform the security
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(
						credentials.getUsername(),
						credentials.getPassword())
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// Reload password post-security so we can generate token
        final UserLicense userLicense = (UserLicense) userDetailsService.loadUserByUsername(credentials.getUsername());
        
        // If user is locked, do not return an token
        if (userLicense.getState() == UserState.LOCKED.getState()) {
        	String message = "Your account is locked, we will unlock on " 
        			+ timeProvider.formatDate(userLicense.getUnlockTime());
        	return new BaseResponse(BaseResponse.ERROR,
					ResponseCode.NOT_IMPLEMENTED, message);
        }
        final String token = tokenPrefix  + " " + tokenUtils.generateToken(userLicense, device);
        // Add new token to header
        //response.addHeader(tokenHeader, token);
		return new TokenResponse(token);
	}

	@Override
	public BaseResponse authenticationRefresh(HttpServletRequest request) {
		String token = request.getHeader(this.tokenHeader).replace(tokenPrefix + " ", "");
		
		String username = this.tokenUtils.getUsernameFromToken(token);
		UserLicense userLicense = (UserLicense) this.userDetailsService.loadUserByUsername(username);
		if (this.tokenUtils.canTokenBeRefreshed(token, userLicense.getLastResetPassword())) {
			String refreshedToken = tokenPrefix  + " " + this.tokenUtils.refreshToken(token);
			
			// Add refresh token to response
			//response.addHeader(tokenHeader,refreshedToken);
			return new TokenResponse(refreshedToken);
		}
		return new BaseResponse(BaseResponse.ERROR,
				ResponseCode.UNAUTHORIZED, "UNAUTHORIZED");
	}

}
