package vn.needy.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.needy.ecommerce.domain.entity.User;
import vn.needy.ecommerce.model.factory.UserLicenseFactory;
import vn.needy.ecommerce.repository.RoleRepository;
import vn.needy.ecommerce.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByUsername(username);
		List<String> roles = roleRepository.findRoleStringByUserId(user.getId());
		
		if (user == null || roles == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
        	return UserLicenseFactory.create(user, roles);
        }
	}

}
