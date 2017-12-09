package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.entity.CompanyStaff;

public interface CompanyStaffRepository {

	long findCompanyStaffByUserId(long userId);

	long insertCompanyStaff(CompanyStaff staff);
}
