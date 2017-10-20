package vn.needy.ecommerce.repository.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.domain.entity.UserRole;
import vn.needy.ecommerce.repository.UserRoleRepository;

@Repository("userRoleRepository")
public class UserRoleRepositoryImpl implements UserRoleRepository {
	
	@Autowired
	JdbcTemplate jdbc;
	
	private SimpleJdbcInsert insert;
	
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.insert = new SimpleJdbcInsert(dataSource)
        		.withTableName(UserRole.TABLE)
        		.usingGeneratedKeyColumns("id");
    }
	
	@Override
	public List<String> findRoleAuthenticationByUserId(long userId) {
		SqlRowSet rs = jdbc.queryForRowSet("SELECT role "
				+ "FROM UserRole "
				+ "WHERE userId = ?", new Object[] {userId});
		List<String> roles = new LinkedList<>();
		while(rs.next()) {
			roles.add(rs.getString("role"));
		}
		return roles;
	}

	@Override
	public long resgisterUserRole(long userId, String role, long createdBy) {
		Map<String, Object> params = new HashMap<>(2);
		params.put("userId", userId);
		params.put("role", role);
		params.put("createdBy", createdBy);
		params.put("lastUpdatedBy", createdBy);
		return insert.executeAndReturnKey(params).longValue();
	}

	@Override
	public int registerUserListRole(long userId, String[] roles, long createdBy) {
		@SuppressWarnings("unchecked")
		Map<String, ?>[] listParams = new Map[roles.length];
		for (int i = 0; i < roles.length; ++i) {
			Map<String, Object> params = new HashMap<>(4);
			params.put("userId", userId);
			params.put("role", roles[i]);
			params.put("createdBy", createdBy);
			params.put("lastUpdatedBy", createdBy);
			listParams[i] = params;
		}
		
		return insert.executeBatch(listParams).length;
	}
}
