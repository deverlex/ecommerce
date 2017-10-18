package vn.needy.ecommerce.repository.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.repository.UserRoleRepository;

@Repository("userRoleRepository")
public class UserRoleRepositoryImpl implements UserRoleRepository {
	
	@Autowired
	JdbcTemplate jdbc;
	
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
}
