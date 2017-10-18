package vn.needy.ecommerce.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.domain.entity.Company;
import vn.needy.ecommerce.repository.CompanyRepository;

@Repository("companyRepository")
public class CompanyRepositoryImpl implements CompanyRepository {

	@Autowired
	JdbcTemplate jdbc;
	
	@Override
	public Company findCompanyInherentByUserId(long userId) {
		SqlRowSet rs = jdbc.queryForRowSet("SELECT c.* "
				+ "FROM Companies c "
				+ "INNER JOIN CompanyStaff cs ON c.id = cs.companyId "
				+ "WHERE cs.userId = ?", new Object[] {userId});
		if (rs.first()) {
			Company company = new Company();
			company.setId(rs.getLong("id"));
			company.setCompanyCode(rs.getString("companyCode"));
			company.setState(rs.getInt("state"));
			company.setName(rs.getString("name"));
			company.setNumberEmployee(rs.getInt("numberEmployee"));
			company.setOfficeAddress(rs.getString("officeAddress"));
			company.setFoundedDate(rs.getDate("foundedDate"));
			company.setOpeningTime(rs.getTimestamp("openingTime"));
			company.setClosingTime(rs.getTimestamp("closingTime"));
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

}
