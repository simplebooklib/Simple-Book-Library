package com.library.book.mark;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 북마크
 */
@Data
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@Table(name = "book_mark", indexes = { @Index(name = "book_mark_idx_acct_no", columnList = "acct_no,isbn", unique = true) })
public class BookMark {

	/**
	 * 북마크번호
	 */
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "book_mark_no", nullable = false)
	private Long bookMarkNo;

	/**
	 * 계정번호
	 */
	@Column(name = "acct_no", nullable = false)
	private Integer acctNo;

	/**
	 * 책제목
	 */
	@Column(name = "title", nullable = false)
	private String title;

	/**
	 * 출판날짜
	 */
	@Column(name = "datetime", nullable = false)
	private LocalDateTime datetime;

	/**
	 * ISBN
	 */
	@Column(name = "isbn", nullable = false)
	private String isbn;

	/**
	 * 생성일
	 */
	@CreatedDate
	@Column(name = "create_date", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime createDate;

}
