package co.id.bankbsi.middlewaresnippet.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import co.id.bankbsi.middlewaresnippet.security.entity.User;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);

}
