package com.library.book.mark;

import com.library.exception.DuplicateDataException;
import com.library.exception.InvalidAccessException;
import com.library.search.KakaoBookSearcher;
import com.library.search.SearchResponse.Document;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class BookMarkService {

	private final KakaoBookSearcher bookSearcher;
	private final BookMarkRepository bookMarkRepository;

	public BookMarkService(KakaoBookSearcher bookSearcher, BookMarkRepository bookMarkRepository) {
		this.bookSearcher = bookSearcher;
		this.bookMarkRepository = bookMarkRepository;
	}

	/**
	 * 북마크 조회
	 *
	 * @param acctNo 계정번호
	 * @param isbn   ISBN
	 * @return 북마크
	 */
	public Optional<BookMark> findByBookMark(int acctNo, String isbn) {
		return bookMarkRepository.findByAcctNoAndIsbn(acctNo, isbn);
	}

	/**
	 * 북마크 페이징 조회.
	 * {@link CompletableFuture}를 이용해서 멀티스레드로 조회한다.
	 *
	 * @param acctNo   계정번호
	 * @param pageable 페이징정보
	 * @return 북마크 정보
	 */
	public Page<Document> findByAcctNo(int acctNo, Pageable pageable) {

		long count = bookMarkRepository.countByAcctNo(acctNo);

		BookMark bookMarkParam = new BookMark();
		bookMarkParam.setAcctNo(acctNo);
		Page<BookMark> bookMarks = bookMarkRepository.findAll(Example.of(bookMarkParam), pageable);

		List<Document> documents = bookMarks.getContent().stream()
				.map(bm -> bookSearcher.getBookInfo(bm.getIsbn()))
				.collect(Collectors.toList());
		return new PageImpl<>(documents, pageable, count);
	}

	/**
	 * 북마크 등록
	 *
	 * @param acctNo 계정번호
	 * @param isbn   ISBN
	 */
	public void create(int acctNo, String isbn) {
		Optional<BookMark> bookMark = findByBookMark(acctNo, isbn);
		bookMark.ifPresent(bm -> {
			throw new DuplicateDataException("이미 등록된 북마크 입니다.");
		});

		Document bookInfo = bookSearcher.getBookInfo(isbn);

		BookMark bookMarkParam = new BookMark();
		bookMarkParam.setAcctNo(acctNo);
		bookMarkParam.setIsbn(isbn);
		bookMarkParam.setTitle(bookInfo.getTitle());
		bookMarkParam.setDatetime(bookInfo.getDatetime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		bookMarkRepository.save(bookMarkParam);
	}

	/**
	 * 북마크 삭제. 로그인회원의 본인 북마크가 맞는지 확인한다.
	 *
	 * @param acctNo 계정번호
	 * @param isbn   ISBN
	 */
	public void delete(int acctNo, String isbn) {
		Optional<BookMark> bookMark = bookMarkRepository.findByAcctNoAndIsbn(acctNo, isbn);
		bookMark.ifPresent(bm -> {
			if (!Objects.equals(bm.getAcctNo(), acctNo)) {
				throw new InvalidAccessException("삭제할 수 없는 북마크입니다.");
			}
			bookMarkRepository.delete(bm);
		});
	}

}
