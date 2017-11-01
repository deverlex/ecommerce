package vn.needy.ecommerce.repository.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.domain.entity.CompanyStaff;
import vn.needy.ecommerce.repository.CompanyStaffResponsitory;

@Repository("companyStaffResponsitory")
public class CompanyStaffResponsitoryImpl implements CompanyStaffResponsitory {

	private SimpleJdbcInsert insert;
	
//	@Autowired
//	private JdbcTemplate jdbc;
	
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.insert = new SimpleJdbcInsert(dataSource)
        		.withTableName(CompanyStaff.TABLE)
        		.usingGeneratedKeyColumns("id");
    }
	
	@Override
	public long insertCompanyStaff(CompanyStaff staff) {
		Map<String, Object> params = new HashMap<>(5);
		params.put("userId", staff.getUserId());
		params.put("companyId", staff.getCompanyId());
		params.put("storeId", staff.getStoreId());
		params.put("fcmToken", staff.getFcmToken());
		params.put("state", staff.getState());
		params.put("status", staff.getStatus());
		params.put("createdBy", staff.getCreatedBy());
		params.put("lastUpdatedBy", staff.getLastUpdatedBy());
		return insert.executeAndReturnKey(params).longValue();
	}

}
