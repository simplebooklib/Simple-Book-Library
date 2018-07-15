package com.library.security;

import com.library.account.Account;
import com.library.account.AccountRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

	private final AccountRepository accountRepository;

	public UserDetailServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> account = accountRepository.findByAcctId(username);

		return account.map(acct -> {
			List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
			return new SimpleBookLibraryUser(acct.getAcctNo(), acct.getAcctId(), acct.getAcctPw(), authorities);
		}).orElseThrow(() -> new UsernameNotFoundException("Invalid id or password."));
	}

}
