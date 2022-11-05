package hh.palvelinohjelmointi.travel.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.palvelinohjelmointi.travel.domain.User;
import hh.palvelinohjelmointi.travel.domain.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private final UserRepository userRepo;

	@Autowired
	public UserDetailServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User currentUser = userRepo.findByUsername(username);
		if (currentUser == null) {
			throw new UsernameNotFoundException("User not found");
		} else {
			UserDetails user = new org.springframework.security.core.userdetails.User(username,
					currentUser.getPasswordHash(), AuthorityUtils.createAuthorityList(currentUser.getRole()));
			return user;
		}
	}
}
