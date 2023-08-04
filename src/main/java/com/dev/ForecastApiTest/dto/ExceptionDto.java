package com.dev.ForecastApiTest.dto;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ExceptionDto {

	private HttpStatus status;
	private String message;
	
	public ExceptionDto(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}
}
