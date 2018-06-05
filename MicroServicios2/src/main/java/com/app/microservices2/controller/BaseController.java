package com.app.microservices2.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.app.microservices2.bean.ServiceResponse;
import com.app.microservices2.util.HttpUtils;


public class BaseController {

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		
		ServiceResponse serviceResponse = new ServiceResponse();
		HttpUtils.preparedErrorResponse(serviceResponse, 
				HttpStatus.BAD_REQUEST.getReasonPhrase() + " " + ex.getMessage(),
				String.valueOf(HttpStatus.BAD_REQUEST.value()));

	    return new ResponseEntity<>(serviceResponse, HttpStatus.BAD_REQUEST);
	}
	
}
