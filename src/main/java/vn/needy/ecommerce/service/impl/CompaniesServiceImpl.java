package vn.needy.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vn.needy.ecommerce.common.utils.TimeProvider;
import vn.needy.ecommerce.domain.entity.Budget;
import vn.needy.ecommerce.domain.entity.Company;
import vn.needy.ecommerce.domain.entity.CompanyStaff;
import vn.needy.ecommerce.domain.entity.Pay;
import vn.needy.ecommerce.domain.entity.Role;
import vn.needy.ecommerce.domain.entity.Store;
import vn.needy.ecommerce.model.enums.StaffState;
import vn.needy.ecommerce.model.enums.StaffStatus;
import vn.needy.ecommerce.model.enums.StoreState;
import vn.needy.ecommerce.model.enums.StoreStatus;
import vn.needy.ecommerce.model.enums.CompanyState;
import vn.needy.ecommerce.model.enums.PayBehavior;
import vn.needy.ecommerce.model.json.entity.CompanyJson;
import vn.needy.ecommerce.model.json.request.RegisterCompanyRequest;
import vn.needy.ecommerce.model.json.response.CompanyResponse;
import vn.needy.ecommerce.repository.BudgetRepository;
import vn.needy.ecommerce.repository.CompanyRepository;
import vn.needy.ecommerce.repository.CompanyGuaranteeRepository;
import vn.needy.ecommerce.repository.CompanyStaffResponsitory;
import vn.needy.ecommerce.repository.PayRepository;
import vn.needy.ecommerce.repository.StoreResponsitory;
import vn.needy.ecommerce.repository.UserRoleRepository;
import vn.needy.ecommerce.service.CompaniesService;

@Service("companiesService")
public class CompaniesServiceImpl implements CompaniesService {
	
	@Autowired
	private TimeProvider timeProvider;
	
	@Autowired
	CompanyRepository companiesRepository;
	
	@Autowired
	StoreResponsitory storesResponsitory;
	
	@Autowired
	BudgetRepository budgetRepository;
	
	@Autowired
	PayRepository paysRepository;
	
	@Autowired
	CompanyGuaranteeRepository companyReputationRepository;
	
	@Autowired
	CompanyStaffResponsitory companyStaffResponsitory;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Override
	public CompanyResponse findCompanyInformation(long userId) {
		Company company = companiesRepository.findCompanyInformationByUserId(userId);
		if (company != null) {
			boolean isCompanyReputation = companyReputationRepository.isCompanyGuaranteeById(company.getId());
			CompanyJson companyJson = new CompanyJson(company);
			companyJson.setReputation(isCompanyReputation);
			return new CompanyResponse(companyJson);
		}
		return new CompanyResponse();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public CompanyResponse registerCompany(long userId, RegisterCompanyRequest registerCompanyRequest) {
		CompanyResponse companyResponse = new CompanyResponse();
		Company findCompany = companiesRepository.findCompanyInformationByUserId(userId);
		
		if (findCompany != null) {
			companyResponse = new CompanyResponse();
			companyResponse.setStatus(-1);
			companyResponse.setMessage("You can not register 2 company both active.");
			return companyResponse;
		}
				
		// insert into Companies tables
		Company registerCompany = new Company();
		registerCompany.setLastUpdatedBy(userId);
		registerCompany.setName(registerCompanyRequest.getCompanyName());
		registerCompany.setAddress(registerCompanyRequest.getOfficeAddress());
		registerCompany.setLevel(0);
		registerCompany.setState(CompanyState.ACTIVE.getState());
		long companyId = companiesRepository.registerCompany(registerCompany);
	
		// insert into Budgets
		// MODIFY UPDATE
		Budget registerBudget = new Budget();
		registerBudget.setCompanyId(companyId);
		registerBudget.setBudget(500000f);
		long budgetId = budgetRepository.createBudget(registerBudget);
		
		// insert into PayLogs
		Pay payLog = new Pay();
		payLog.setBudgetId(budgetId);
		payLog.setBehavior(PayBehavior.INITIALIZE.getBehavior());
		payLog.setBudgetCharge(500000f);
		payLog.setDescription("Initialize budget account.");
		payLog.setCreatedBy(1); // system id
		paysRepository.createPay(payLog);
		
		// insert into Stores
		Store registerStore = new Store();
		registerStore.setCompanyId(companyId);
		registerStore.setState(StoreState.INACTIVE.getState());
		registerStore.setStatus(StoreStatus.CLOSED_TIME.getStatus());
		registerStore.setName(registerCompanyRequest.getStoreName());
		registerStore.setAddress(registerCompanyRequest.getStoreAddress());
		registerStore.setNumberStaff(1);
		registerStore.setOpeningTime(timeProvider.parseTime("08:00:00"));
		registerStore.setClosingTime(timeProvider.parseTime("20:00:00"));
		registerStore.setLat(registerCompanyRequest.getLat());
		registerStore.setLng(registerCompanyRequest.getLng());
		registerStore.setLastUpdatedBy(userId);
		long storeId = storesResponsitory.registerStore(registerStore);
		
		// insert into CompanyStaff
		CompanyStaff staff = new CompanyStaff();
		staff.setUserId(userId);
		staff.setLastUpdatedBy(userId);
		staff.setCompanyId(companyId);
		staff.setStoreId(storeId);
		staff.setFcmToken(registerCompanyRequest.getFcmToken());
		staff.setState(StaffState.ACTIVED.getState());
		staff.setStatus(StaffStatus.READY.getStatus());
		companyStaffResponsitory.insertCompanyStaff(staff);
		
		userRoleRepository.registerUserListRole(userId, Role.List.CompanyOwner, userId);
		
		Company company = companiesRepository.findCompanyInformationByUserId(userId);
		if (company != null) {
			boolean isCompanyReputation = companyReputationRepository.isCompanyGuaranteeById(company.getId());
			CompanyJson companyJson = new CompanyJson(company);
			companyJson.setReputation(isCompanyReputation);
			return new CompanyResponse(companyJson);
		}
		companyResponse.setStatus(-1);
		companyResponse.setMessage("Create company is failed.");
		return companyResponse;
	}

	
}
