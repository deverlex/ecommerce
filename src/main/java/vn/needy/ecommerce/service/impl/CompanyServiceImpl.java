package vn.needy.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.needy.ecommerce.domain.entity.Company;
import vn.needy.ecommerce.model.json.entity.CompanyJson;
import vn.needy.ecommerce.model.json.response.CompanyResponse;
import vn.needy.ecommerce.repository.CompanyRepository;
import vn.needy.ecommerce.repository.CompanyReputationRepository;
import vn.needy.ecommerce.service.CompanyService;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	CompanyReputationRepository companyReputationRepository;
	
	@Override
	public CompanyResponse findCompanyInherent(long userId) {
		Company company = companyRepository.findCompanyInherentByUserId(userId);
		if (company != null) {
			boolean isCompanyReputation = companyReputationRepository.isCompanyReputationById(company.getId());
			CompanyJson companyJson = new CompanyJson(company);
			companyJson.setReputation(isCompanyReputation);
			return new CompanyResponse(companyJson);
		}
		return null;
	}

	
}
