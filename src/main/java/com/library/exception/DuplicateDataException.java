package com.library.exception;

/**
 * 중복된 데이터가 존재할 경우
 */
public class DuplicateDataException extends RuntimeException {

	public DuplicateDataException(String message) {
		super(message);
	}

}
