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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.needy.ecommerce.model.security.Credentials;
import vn.needy.ecommerce.model.security.UserLicense;

@RestController
public class AuthenticationRest {

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

	@RequestMapping(value = "${needy.route.authentication.path}", method = RequestMethod.POST)
	public ResponseEntity<Void> createAuthenticationToken(@RequestBody Credentials credentials,
			Device device, HttpServletResponse response) throws AuthenticationException {
		
		// Perform the security
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(
						credentials.getUsername(),
						credentials.getPassword())
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(credentials.getUsername());
        final String token = tokenUtils.generateToken(userDetails, device);
        
        // Add new token to response
        response.addHeader(tokenHeader,tokenPrefix  + " " + token);
        return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "${needy.route.authentication.refresh}", method = RequestMethod.GET)
	public ResponseEntity<?> authenticationRequest(HttpServletRequest request, HttpServletResponse response) {
		String token = request.getHeader(this.tokenHeader);
		String username = this.tokenUtils.getUsernameFromToken(token);
		UserLicense user = (UserLicense) this.userDetailsService.loadUserByUsername(username);
		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastUpdatedPassword())) {
			String refreshedToken = this.tokenUtils.refreshToken(token);
			
			// Add refresh token to response
			response.addHeader(tokenHeader,tokenPrefix  + " " + refreshedToken);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}
}
