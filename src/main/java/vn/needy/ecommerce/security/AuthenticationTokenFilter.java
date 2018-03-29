package vn.needy.ecommerce.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import vn.needy.ecommerce.model.security.NeedyUserDetails;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

	private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private TokenUtils tokenUtils;
    	
    @Value("${needy.token.header}")
    private String tokenHeader;
    
    @Value("${needy.token.prefix}")
	private String tokenPrefix;
    
    @Value("${needy.identification.header}")
	private String identificationHeader;
    
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestHeader = request.getHeader(this.tokenHeader);

        String username = null;
        String authToken = null;
                
        if (requestHeader != null && requestHeader.startsWith(tokenPrefix)) {
            authToken = requestHeader.replace(tokenPrefix + " ", "");
            try {
                username = tokenUtils.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                logger.error("an error occurred during getting username from token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("the token is expired and not valid anymore", e);
            } catch (SignatureException e) {
                logger.error("the token is not match locally computed signature", e);
            } catch (MalformedJwtException e) {
                logger.error("Unable to read JSON value", e);
            } catch (Exception e) {
                logger.error("Can not find error", e);
            }
        } else {
            logger.warn("couldn't find bearer string, will ignore the header");
        }
        
        logger.info("checking authentication for user " + username);
        
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        	// It is not compelling necessary to load the use details from the database. You could also store the information
            // in the token and read it from it. It's up to you ;)
            NeedyUserDetails needyUserDetails = (NeedyUserDetails) this.userDetailsService.loadUserByUsername(username);

            // For simple validation it is completely sufficient to just check the token integrity. You don't have to call
            // the database compellingly. Again it's up to you ;)
            if (tokenUtils.validateToken(authToken, needyUserDetails)) {
            	UsernamePasswordAuthenticationToken authentication 
            		= new UsernamePasswordAuthenticationToken(needyUserDetails, null, needyUserDetails.getAuthorities());
            	authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            	logger.info("authenticated user " + username + ", setting security context");
            	
            	// Custom header on server, each request, we can get user id, this solution allow we access user success
                // because we will have many username duplicated
                request.getSession().setAttribute(identificationHeader, needyUserDetails.getId());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        
        filterChain.doFilter(request, response);
	}

}
