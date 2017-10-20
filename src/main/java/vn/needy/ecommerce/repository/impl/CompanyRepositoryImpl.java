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
	public Company findCompanyInherentByUserId(long userId) {
		SqlRowSet rs = jdbc.queryForRowSet("SELECT c.* "
				+ "FROM Companies c "
				+ "INNER JOIN CompanyStaff cs ON c.id = cs.companyId "
				+ "WHERE cs.userId = ? AND c.state <> ?", 
				new Object[] {userId, CompanyState.CLOSED.getState()});
		if (rs.first()) {
			Company company = new Company();
			company.setId(rs.getLong("id"));
			company.setCompanyNumber(rs.getString("companyNumber"));
			company.setState(rs.getInt("state"));
			company.setName(rs.getString("name"));
			company.setNumberEmployee(rs.getInt("numberEmployee"));
			company.setOfficeAddress(rs.getString("officeAddress"));
			company.setFoundedDate(rs.getDate("foundedDate"));
			company.setOpeningTime(rs.getTime("openingTime"));
			company.setClosingTime(rs.getTime("closingTime"));
			company.setAvatar(rs.getString("avatar"));
			company.setPictures(rs.getString("pictures"));
			company.setDescription(rs.getString("description"));
			company.setWebsite(rs.getString("website"));
			company.setCreatedTime(rs.getDate("createdTime"));
			company.setLastUpdatedTime(rs.getDate("lastUpdatedTime"));
			return company;
		}
		return null;
	}

	@Override
	public long registerCompany(Company company) {
		Map<String, Object> params = new HashMap<>(11);
		params.put("state", company.getState());
		params.put("level", company.getLevel());
		params.put("numberEmployee", company.getNumberEmployee());
		params.put("name", company.getName());
		params.put("officeAddress", company.getOfficeAddress());
		params.put("companyNumber", company.getCompanyNumber());
		params.put("foundedDate", company.getFoundedDate());
		params.put("openingTime", company.getOpeningTime());
		params.put("closingTime", company.getClosingTime());
		params.put("createdBy", company.getCreatedBy());
		params.put("lastUpdatedBy", company.getLastUpdatedBy());
		return insert.executeAndReturnKey(params).longValue();
	}

}
