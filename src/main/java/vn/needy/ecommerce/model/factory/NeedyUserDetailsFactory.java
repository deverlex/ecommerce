package vn.needy.ecommerce.model.factory;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import vn.needy.ecommerce.domain.mysql.User;
import vn.needy.ecommerce.model.security.NeedyUserDetails;

public final class NeedyUserDetailsFactory {
	
	public static NeedyUserDetails create(User user, List<String> roles) {
        return new NeedyUserDetails(
        		user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getState(),
                user.getUnlockTime(),
                mapToGrantedAuthorities(roles),
                user.getLastResetPassword()
        );
    }
	
	private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> roles) {
		return roles.stream().map(
				role -> new SimpleGrantedAuthority("ROLE_" + role)
			).collect(Collectors.toList());
    }
}
