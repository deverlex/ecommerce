package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.entity.CompanyStaff;

import java.util.Map;

public interface CompanyStaffRepository {

	Map<String, Long> findInfoIdByUserId(long userId);

	long insertCompanyStaff(CompanyStaff staff);
}
