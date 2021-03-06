package vn.needy.ecommerce.repository.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.domain.mysql.CompanyStaff;
import vn.needy.ecommerce.model.enums.StaffState;
import vn.needy.ecommerce.repository.CompanyStaffRepository;

@Repository("companyStaffResponsitory")
public class CompanyStaffRepositoryImpl implements CompanyStaffRepository {

	private SimpleJdbcInsert insert;
	
	@Autowired
	private JdbcTemplate jdbc;
	
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.insert = new SimpleJdbcInsert(dataSource)
        		.withTableName(CompanyStaff.TABLE)
        		.usingGeneratedKeyColumns("id");
    }

	@Override
	public Map<String, Long> findInfoIdByUserId(long userId) {
		SqlRowSet rs = jdbc.queryForRowSet("select company_id, store_id " +
						"from company_staff " +
						"where user_id = ? and state <> ?",
				new Object[] {userId, StaffState.INACTIVE});
		if (rs.next()) {
			Map map = new HashMap<String, Long>();
			map.put("company_id", rs.getLong("company_id"));
			map.put("store_id", rs.getLong("store_id"));
			return map;
		}
		return null;
	}
	
	@Override
	public long insertCompanyStaff(CompanyStaff staff) {
		Map<String, Object> params = new HashMap<>(5);
		params.put("user_id", staff.getUserId());
		params.put("company_id", staff.getCompanyId());
		params.put("store_id", staff.getStoreId());
		params.put("fcm_token", staff.getFcmToken());
		params.put("state", staff.getState());
		params.put("status", staff.getStatus());
		params.put("last_updated_by", staff.getLastUpdatedBy());
		return insert.executeAndReturnKey(params).longValue();
	}

}
