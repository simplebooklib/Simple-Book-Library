package com.library.account;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 계정정보
 */
@Data
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@Table(name = "account", indexes = {
		@Index(name = "account_idx_acct_id", columnList = "acct_id") })
public class Account {

	/**
	 * 계정번호
	 */
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "acct_no", nullable = false)
	private Integer acctNo;

	/**
	 * 아이디
	 */
	/*@NotNull
	@Range(min = 8, max = 25)*/
	@Column(name = "acct_id", nullable = false, length = 25)
	private String acctId;

	/**
	 * 비밀번호
	 */
	/*@NotNull
	@Range(min = 8, max = 255)*/
	@Column(name = "acct_pw", nullable = false)
	private String acctPw;

	/**
	 * 가입일
	 */
	@CreatedDate
	@Column(name = "create_date", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime createDate;

}
