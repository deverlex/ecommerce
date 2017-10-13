package vn.needy.ecommerce.repository.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.repository.RoleRepository;

@Repository("roleRepository")
public class RoleRepositoryImpl implements RoleRepository {
	
	@Override
	public List<String> findRoleStringByUserId(long userId) {
		List<String> roles = new LinkedList<>();
		roles.add("ADMIN");
		return roles;
	}
}
