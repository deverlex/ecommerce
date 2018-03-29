package vn.needy.ecommerce.repository.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.api.v1.company.request.UpdateCompanyInfoReq;
import vn.needy.ecommerce.domain.mysql.Company;
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
	public Company findOurByUserId(long userId) {
		SqlRowSet rs = jdbc.queryForRowSet("select c.* "
				+ "from company c "
				+ "inner join company_staff cs on c.id = cs.company_id "
				+ "where cs.user_id = ? and c.state <> ?",
				userId, CompanyState.CLOSED.getState());
		if (rs.first()) {
			Company company = new Company();
			company.setId(rs.getLong("id"));
			company.setState(rs.getInt("state"));
			company.setName(rs.getString("name"));
			company.setEmail(rs.getString("email"));
			company.setAddress(rs.getString("address"));
			company.setCreatedTime(rs.getDate("created_time"));
			company.setLastUpdatedTime(rs.getDate("last_updated_time"));
			company.setFoundedDate(rs.getDate("founded_date"));
			company.setOpeningTime(rs.getTimestamp("opening_time"));
			company.setClosingTime(rs.getTimestamp("closing_time"));
			company.setDescription(rs.getString("description"));
			company.setSiteUrl(rs.getString("site_url"));
			company.setLat(rs.getFloat("lat"));
			company.setLng(rs.getFloat("lng"));
			return company;
		}
		return null;
	}

	@Override
	public Map findInformationByUserId(long userId) {
		SqlRowSet rs = jdbc.queryForRowSet("select c.*, (select count(*) from company_staff cs1 where cs1.company_id = c.id) as count_staff " +
						"from company_staff cs2 " +
						"inner join company c on c.id = cs2.company_id" +
						" where cs2.user_id = ? and c.state <> ?",
				userId, CompanyState.CLOSED.getState());
		if (rs.first()) {
			Map map = new HashMap();
			Company company = new Company();
			company.setId(rs.getLong("id"));
			company.setState(rs.getInt("state"));
			company.setName(rs.getString("name"));
			company.setEmail(rs.getString("email"));
			company.setAddress(rs.getString("address"));
			company.setCreatedTime(rs.getDate("created_time"));
			company.setLastUpdatedTime(rs.getDate("last_updated_time"));
			company.setFoundedDate(rs.getDate("founded_date"));
			company.setOpeningTime(rs.getTimestamp("opening_time"));
			company.setClosingTime(rs.getTimestamp("closing_time"));
			company.setDescription(rs.getString("description"));
			company.setSiteUrl(rs.getString("site_url"));
			company.setLat(rs.getFloat("lat"));
			company.setLng(rs.getFloat("lng"));

			map.put("company", company);
			map.put("staffCount", rs.getInt("count_staff"));

			return map;
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

	@Override
	public boolean updateCompanyInformation(long companyId, long userId, UpdateCompanyInfoReq infoRequest) {
		return jdbc.update("update company set name = ?, address = ?, description = ?, " +
						"site_url = ?, email = ?, lat = ?, lng = ?, " +
						"founded_date = ?, opening_time = ?, closing_time = ? " +
						"where id = ? and id = (select company_id " +
								"from company_staff cs " +
								"where user_id = ? and company_id = ? limit 1)",
						infoRequest.getName(),
						infoRequest.getAddress(),
						infoRequest.getDescription(),
						infoRequest.getSiteURL(),
						infoRequest.getEmail(),
						infoRequest.getLat(),
						infoRequest.getLng(),
						infoRequest.getFoundedDate(),
						infoRequest.getOpeningTime(),
						infoRequest.getClosingTime(),
						/** optimize update access with where primary_key = xxx */
						companyId, userId, companyId) == 1;
	}
}
