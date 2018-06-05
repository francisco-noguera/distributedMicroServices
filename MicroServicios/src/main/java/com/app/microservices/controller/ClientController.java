package com.app.microservices.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.microservices.bean.SaveClientRequest;
import com.app.microservices.bean.ServiceResponse;
import com.app.microservices.service.ClientService;
import com.app.microservices.util.HttpUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/client")
public class ClientController {

	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
	private ClientService clientService;
	
	
	@PostMapping("/save-client")
	public ResponseEntity<ServiceResponse> saveClient(@RequestBody SaveClientRequest saveClientRequest) throws JsonProcessingException {
		ServiceResponse serviceResponse = new ServiceResponse();
		try {
			serviceResponse = clientService.saveClient(saveClientRequest);
			return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
		} catch (Exception e) {
			HttpUtils.preparedErrorResponse(
        			serviceResponse, 
        			e.getMessage(), 
        			String.valueOf(INTERNAL_SERVER_ERROR.value())
            	);
            logger.info(".:: End Save Client Error Response : {}", HttpUtils.printInformation(serviceResponse), e);
            return new ResponseEntity<>(serviceResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/find-client")
	public ResponseEntity<ServiceResponse> getClientById(@RequestParam BigDecimal clientId) throws JsonProcessingException {
		ServiceResponse serviceResponse = new ServiceResponse();
		try {
			serviceResponse = clientService.findClient(clientId);
			return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
		} catch (Exception e) {
			HttpUtils.preparedErrorResponse(
        			serviceResponse, 
        			e.getMessage(), 
        			String.valueOf(NOT_FOUND.value())
            	);
            logger.info(".:: End Get Client Error Response : {}", HttpUtils.printInformation(serviceResponse), e);
            return new ResponseEntity<>(serviceResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
