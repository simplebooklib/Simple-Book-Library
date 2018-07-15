package com.library.book.history;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

	/**
	 * 최근 검색 기록 조회
	 *
	 * @param acctNo 계정번호
	 * @return 검색기록
	 */
	List<SearchHistory> findByAcctNoOrderBySearchHistoryNoDesc(int acctNo);

}
