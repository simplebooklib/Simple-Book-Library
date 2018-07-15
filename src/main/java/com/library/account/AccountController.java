package com.library.account;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class AccountController {

	/**
	 * 로그인 페이지
	 */
	@GetMapping(value = "login")
	public String login(Authentication authentication) {
		return Optional.ofNullable(authentication)
				.map(auth -> "redirect:/search")
				.orElse("login");
	}

}
