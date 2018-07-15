package com.library.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 로그인 계정 정보
 */
public class SimpleBookLibraryUser extends User {

	@Getter
	private final int acctNo;

	public SimpleBookLibraryUser(int acctNo, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.acctNo = acctNo;
	}

}
