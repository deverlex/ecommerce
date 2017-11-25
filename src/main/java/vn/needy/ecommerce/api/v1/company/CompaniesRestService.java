package vn.needy.ecommerce.api.v1.company;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.needy.ecommerce.api.v1.company.request.RegisterCompanyRequest;
import vn.needy.ecommerce.api.v1.company.response.CompanyResponse;
import vn.needy.ecommerce.security.IdentificationUtils;
import vn.needy.ecommerce.api.v1.company.service.CompaniesService;

@RestController
public class CompaniesRestService {
	
	@Autowired
	private IdentificationUtils idUtils;
	
	@Autowired
	private CompaniesService companiesService;
	
	@RequestMapping(value= "${needy.route.companies.infomation}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<CompanyResponse> findCompanyInformation(HttpServletRequest request) {
		Long userId = idUtils.getIdentification(request);
		CompanyResponse companyResponse = companiesService.findCompanyInformation(userId);
		return ResponseEntity.ok(companyResponse);
	}
	
	@RequestMapping(value= "${needy.route.companies.registers}", method = RequestMethod.POST)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<CompanyResponse> registerCompany(HttpServletRequest request, 
			@RequestBody RegisterCompanyRequest registerCompanyRequest) {
		Long userId = idUtils.getIdentification(request);
		CompanyResponse companyResponse = companiesService.registerCompany(userId, registerCompanyRequest);
		return ResponseEntity.ok(companyResponse);
	}
	
}
