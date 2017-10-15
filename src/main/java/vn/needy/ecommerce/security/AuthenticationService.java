package vn.needy.ecommerce.security;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.needy.ecommerce.common.utils.TimeProvider;
import vn.needy.ecommerce.model.enums.UserState;
import vn.needy.ecommerce.model.security.Certification;
import vn.needy.ecommerce.model.security.Credentials;
import vn.needy.ecommerce.model.security.UserLicense;

@RestController
public class AuthenticationService {

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

	@RequestMapping(value = "${needy.route.security.authentication}", method = RequestMethod.POST)
	public ResponseEntity<Certification> createAuthenticationToken(@RequestBody Credentials credentials,
			Device device, HttpServletResponse response) throws AuthenticationException {
		
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
        	return ResponseEntity.ok(new Certification("", message));
        }
        final String token = tokenPrefix  + " " + tokenUtils.generateToken(userLicense, device);
        // Add new token to header
        //response.addHeader(tokenHeader, token);
        return ResponseEntity.ok(new Certification(token, "Login success"));
	}
	
	@RequestMapping(value = "${needy.route.security.refresh}", method = RequestMethod.GET)
	public ResponseEntity<Certification> authenticationRequest(HttpServletRequest request, HttpServletResponse response) {
		String token = request.getHeader(this.tokenHeader).replace(tokenPrefix + " ", "");
		
		String username = this.tokenUtils.getUsernameFromToken(token);
		UserLicense userLicense = (UserLicense) this.userDetailsService.loadUserByUsername(username);
		if (this.tokenUtils.canTokenBeRefreshed(token, userLicense.getLastResetPassword())) {
			String refreshedToken = tokenPrefix  + " " + this.tokenUtils.refreshToken(token);
			
			// Add refresh token to response
			//response.addHeader(tokenHeader,refreshedToken);
			return ResponseEntity.ok(new Certification(refreshedToken, "Refresh success"));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
}
