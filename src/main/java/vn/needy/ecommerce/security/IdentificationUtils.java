package vn.needy.ecommerce.security;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IdentificationUtils implements Serializable {
	
	private static final long serialVersionUID = 1449239427492L;
	
	@Value("${needy.identification.header}")
	private String identificationHeader;
	
	public Long getUserIdFromRequest(HttpServletRequest request) {
		return (Long) request.getSession().getAttribute(identificationHeader);
	}
}
