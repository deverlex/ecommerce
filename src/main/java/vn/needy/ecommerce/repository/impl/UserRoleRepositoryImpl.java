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

import vn.needy.ecommerce.domain.mysql.UserRole;
import vn.needy.ecommerce.repository.UserRoleRepository;

@Repository("userRoleRepository")
public class UserRoleRepositoryImpl implements UserRoleRepository {
	
	@Autowired
	JdbcTemplate jdbc;
	
	private SimpleJdbcInsert insert;

	private final String PERMISSION_DEFAULT = "ALL";
	
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.insert = new SimpleJdbcInsert(dataSource)
        		.withTableName(UserRole.TABLE)
        		.usingGeneratedKeyColumns("id");
    }
	
	@Override
	public List<String> findRolePermissionByUserId(long userId) {
		SqlRowSet rs = jdbc.queryForRowSet("select role_name, permission_name "
				+ "from user_role "
				+ "where user_id = ?", new Object[] {userId});
		List<String> rolePermissions = new LinkedList<>();
		while(rs.next()) {
			String role = rs.getString("role_name");
			String permission = rs.getString("permission_name");
			rolePermissions.add(role + "_" + permission);
		}
		return rolePermissions;
	}

	@Override
	public long registerUserRole(long userId, String role, long lastUpdatedBy) {
		Map<String, Object> params = new HashMap<>(2);
		params.put("user_id", userId);
		params.put("role_name", role);
		params.put("last_updated_by", lastUpdatedBy);
		return insert.executeAndReturnKey(params).longValue();
	}

	@Override
	public int registerUserListRole(long userId, String[] roles, long lastUpdatedBy) {
		@SuppressWarnings("unchecked")
		Map<String, ?>[] listParams = new Map[roles.length];
		for (int i = 0; i < roles.length; ++i) {
			Map<String, Object> params = new HashMap<>(4);
			params.put("user_id", userId);
			params.put("permission_name", PERMISSION_DEFAULT);
			params.put("role_name", roles[i]);
			params.put("last_updated_by", lastUpdatedBy);
			listParams[i] = params;
		}
		
		return insert.executeBatch(listParams).length;
	}
}
