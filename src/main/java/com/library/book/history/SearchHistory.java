package com.library.book.history;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 검색 히스토리
 */
@Data
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@Table(name = "search_history", indexes = {
		@Index(name = "search_history_idx_acct_no", columnList = "acct_no")})
public class SearchHistory {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "search_history_no")
	private Long searchHistoryNo;

	/**
	 * 계정번호
	 */
	@NotNull
	@Column(name = "acct_no")
	private Integer acctNo;

	/**
	 * 검색어
	 */
	@NotNull
	@Column(name = "query")
	private String query;

	/**
	 * url
	 */
	@NotNull
	@Column(name = "url")
	private String url;

	/**
	 * 생성일
	 */
	@CreatedDate
	@Column(name = "create_date", updatable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime createDate;

}
