package springj.authenticas.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import springj.authenticas.model.SimpleUser;
import springj.authenticas.payload.UserDTO;
import springj.authenticas.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username)
	throws UsernameNotFoundException {
		SimpleUser user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(
				"User not found with username: " + username
			);
		}
		return new org.springframework.security.core.userdetails.User(
			user.getUsername()
			,user.getPassword()
			,new ArrayList<>()
		);
	}

	public SimpleUser save(UserDTO user) {
		SimpleUser newUser = new SimpleUser();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(newUser);
	}
}