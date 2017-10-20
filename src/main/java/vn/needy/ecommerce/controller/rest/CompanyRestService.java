package vn.needy.ecommerce.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.needy.ecommerce.model.json.request.RegisterCompanyRequest;
import vn.needy.ecommerce.model.json.response.CompanyResponse;
import vn.needy.ecommerce.security.IdentificationUtils;
import vn.needy.ecommerce.service.CompanyService;

@RestController
public class CompanyRestService {
	
	@Autowired
	private IdentificationUtils idUtils;
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value= "${needy.route.company.inherent}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<CompanyResponse> findPesonCompany(HttpServletRequest request) {
		Long userId = idUtils.getIdentification(request);
		CompanyResponse companyResponse = companyService.findCompanyInherent(userId);
		return ResponseEntity.ok(companyResponse);
	}
	
	@RequestMapping(value= "${needy.route.company.register}", method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<CompanyResponse> registerCompany(HttpServletRequest request, 
			@RequestBody RegisterCompanyRequest registerCompanyRequest) {
		Long userId = idUtils.getIdentification(request);
		CompanyResponse companyResponse = companyService.registerCompany(userId, registerCompanyRequest);
		return ResponseEntity.ok(companyResponse);
	}
}
