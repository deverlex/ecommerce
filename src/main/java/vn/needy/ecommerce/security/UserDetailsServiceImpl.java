package vn.needy.ecommerce.security;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.needy.ecommerce.common.utils.TimeProvider;
import vn.needy.ecommerce.domain.mysql.User;
import vn.needy.ecommerce.model.enums.UserState;
import vn.needy.ecommerce.model.factory.NeedyUserDetailsFactory;
import vn.needy.ecommerce.repository.UserRoleRepository;
import vn.needy.ecommerce.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    UserRepository usersRepository;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Autowired
	TimeProvider timeProvider;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = usersRepository.findUserByUsernameForAuthenticate(username);
		
		if (user.getState() == UserState.LOCKED.getState() 
				&& isExpiredLockTime(user.getUnlockTime())) {
			user.setState(UserState.ACTIVE.getState());
			usersRepository.updateUserState(user.getId(), UserState.ACTIVE.getState());
		}
		
		List<String> rolePermissions = userRoleRepository.findRolePermissionByUserId(user.getId());
		
		if (user == null || rolePermissions == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
        	return NeedyUserDetailsFactory.create(user, rolePermissions);
        }
	}
	
	private boolean isExpiredLockTime(Date unlockTime) {
		return timeProvider.now().after(unlockTime);
	}

}
