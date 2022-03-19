package ca.unb.usermanagement.security.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.unb.usermanagement.model.User;
import ca.unb.usermanagement.model.UserRegistry;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = UserRegistry.getInstance().getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User with username '" + username + "'does not exist.");
		}
		return UserDetailsImpl.build(user);
	}

}
