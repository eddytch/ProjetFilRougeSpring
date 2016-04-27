package fr.iocean.application.configuration.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.iocean.application.user.model.User;
import fr.iocean.application.user.repository.UserRepository;



@Service
public class AuthenticationService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String username) {
		Optional<User> option = userRepository.findOneByLogin(username);
		if (option.isPresent()) {
			User user = option.get();
			List<GrantedAuthority> rules = new ArrayList<>();//this.getUserCredentials(user);
			return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), rules);
		}
		throw new UsernameNotFoundException("username.not.found");
	}

//	private List<GrantedAuthority> getUserCredentials(User user) {
//		List< GrantedAuthority> rules = new ArrayList<>();
//		
//		for(Credential c : user.getCredentials())
//		{
//			rules.add(new SimpleGrantedAuthority(c.getValue()));
//		}
//		
//		return rules;
//	}	
}