package com.library.exception;

/**
 * 로그인한 회원이 처리할 수 없는 접근을 한 경우
 */
public class InvalidAccessException extends RuntimeException {

	public InvalidAccessException(String message) {
		super(message);
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return this;
	}

}
