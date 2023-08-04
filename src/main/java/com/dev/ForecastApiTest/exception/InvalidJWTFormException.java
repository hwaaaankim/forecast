package com.dev.ForecastApiTest.exception;

import com.dev.ForecastApiTest.constant.ErrorCode;

import io.jsonwebtoken.io.DecodingException;
import lombok.Getter;

@Getter
public class InvalidJWTFormException extends DecodingException{

	private static final long serialVersionUID = 1L;
	private final ErrorCode errorCode;
	
	public InvalidJWTFormException(String message, ErrorCode errorCode){
		super(message);
		this.errorCode = errorCode;
	}
	
	public InvalidJWTFormException(String message, Throwable cause, ErrorCode errorCode){
		super(message, cause);
		this.errorCode = errorCode;
	}
	
}
