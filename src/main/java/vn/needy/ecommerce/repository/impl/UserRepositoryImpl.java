package vn.needy.ecommerce.repository.impl;

import org.springframework.stereotype.Repository;

import vn.needy.ecommerce.domain.entity.User;
import vn.needy.ecommerce.model.enums.UserState;
import vn.needy.ecommerce.repository.UserRepository;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {


	@Override
	public User findUserByUsername(String username) {
		User user = new User();
		user.setId(1);
		user.setUsername("admin");
		user.setPassword("$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi");
		user.setState(UserState.ACTIVE.getState());
		return user;
	}

}
