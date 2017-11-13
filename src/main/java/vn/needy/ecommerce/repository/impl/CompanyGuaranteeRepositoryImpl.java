package vn.needy.ecommerce.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.model.enums.CompanyReputationState;
import vn.needy.ecommerce.repository.CompanyGuaranteeRepository;

@Repository("companyReputationRepository")
public class CompanyGuaranteeRepositoryImpl implements CompanyGuaranteeRepository {

	@Autowired
	JdbcTemplate jdbc;
	
	@Override
	public boolean isCompanyReputationById(long companyId) {
		SqlRowSet rs = jdbc.queryForRowSet("SELECT id "
				+ "FROM CompanyGuarantees "
				+ "WHERE companyId = ? AND state = ?", 
				new Object[] {companyId, CompanyReputationState.ACTIVE.getState()});
		return rs.first();
	}
	
}
