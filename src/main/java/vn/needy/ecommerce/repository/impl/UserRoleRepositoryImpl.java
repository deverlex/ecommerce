package vn.needy.ecommerce.repository.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.repository.UserRoleRepository;

@Repository("userRoleRepository")
public class UserRoleRepositoryImpl implements UserRoleRepository {
	
	@Override
	public List<String> findRoleStringByUserId(long userId) {
		List<String> roles = new LinkedList<>();
		roles.add("ADMIN");
		return roles;
	}
}
