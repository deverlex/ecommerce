package vn.needy.ecommerce.api.v1.company;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyStaffRestService {
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> updateFirebaseToken() {
		
		return null;
	}
}
