package com.library.constant;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 상수
 */
public interface Constant {

	/**
	 * 코드 정보
	 */
	@JsonValue
	String getCode();

	/**
	 * 코드 설명
	 */
	String getDesc();

}
