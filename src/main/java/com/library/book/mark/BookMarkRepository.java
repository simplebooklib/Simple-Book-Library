package com.library.book.mark;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {

	/**
	 * 북마크 조회
	 *
	 * @param acctNo 계정번호
	 * @param isbn   ISBN
	 * @return 북마크 정보
	 */
	Optional<BookMark> findByAcctNoAndIsbn(int acctNo, String isbn);

}
