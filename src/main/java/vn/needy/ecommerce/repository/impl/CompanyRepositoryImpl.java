package vn.needy.ecommerce.repository.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.domain.entity.Company;
import vn.needy.ecommerce.model.enums.CompanyState;
import vn.needy.ecommerce.repository.CompanyRepository;

@Repository("companyRepository")
public class CompanyRepositoryImpl implements CompanyRepository {

	@Autowired
	JdbcTemplate jdbc;
	
	private SimpleJdbcInsert insert;
	
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.insert = new SimpleJdbcInsert(dataSource)
        		.withTableName(Company.TABLE)
        		.usingGeneratedKeyColumns("id");
    }
	
	@Override
	public Company findCompanyInformationByUserId(long userId) {
		SqlRowSet rs = jdbc.queryForRowSet("select c.* "
				+ "from company c "
				+ "inner join company_staff cs on c.id = cs.company_id "
				+ "where cs.user_id = ? and c.state <> ?",
				new Object[] {userId, CompanyState.CLOSED.getState()});
		if (rs.first()) {
			Company company = new Company();
			company.setId(rs.getLong("id"));
			company.setState(rs.getInt("state"));
			company.setName(rs.getString("name"));
			company.setCreatedTime(rs.getDate("created_time"));
			company.setLastUpdatedTime(rs.getDate("last_updated_time"));
			return company;
		}
		return null;
	}

	@Override
	public long registerCompany(Company company) {
		Map<String, Object> params = new HashMap<>(11);
		params.put("state", company.getState());
		params.put("level", company.getLevel());
		params.put("name", company.getName());
		params.put("address", company.getAddress());
		params.put("last_updated_by", company.getLastUpdatedBy());
		return insert.executeAndReturnKey(params).longValue();
	}

}
