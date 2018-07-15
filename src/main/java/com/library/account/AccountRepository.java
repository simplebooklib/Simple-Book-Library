package com.library.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	/**
	 * 아디로 계정정보 조회.
	 *
	 * @param acctId 아이디
	 * @return 계정정보
	 */
	Optional<Account> findByAcctId(String acctId);

}
