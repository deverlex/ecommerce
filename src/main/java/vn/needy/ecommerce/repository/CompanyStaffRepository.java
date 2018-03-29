package vn.needy.ecommerce.repository;

import vn.needy.ecommerce.domain.mysql.CompanyStaff;

import java.util.Map;

public interface CompanyStaffRepository {

	Map<String, Long> findInfoIdByUserId(long userId);

	long insertCompanyStaff(CompanyStaff staff);
}
