package com.app.microservices2.controller;

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

import com.app.microservices2.bean.SaveClientImportRequest;
import com.app.microservices2.bean.ServiceResponse;
import com.app.microservices2.service.ClientImportService;
import com.app.microservices2.util.HttpUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/client-import")
public class ClientImportController {

	private static final Logger logger = LoggerFactory.getLogger(ClientImportController.class);
	
	@Autowired
	private ClientImportService clientService;
	
	
	@PostMapping("/save-client-imports")
	public ResponseEntity<ServiceResponse> saveClient(@RequestBody SaveClientImportRequest saveClientRequest) throws JsonProcessingException {
		ServiceResponse serviceResponse = new ServiceResponse();
		try {
			serviceResponse = clientService.saveClientImport(saveClientRequest);
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
	
	@GetMapping("/find-client-imports")
	public ResponseEntity<ServiceResponse> getClientById(@RequestParam BigDecimal clientId) throws JsonProcessingException {
		ServiceResponse serviceResponse = new ServiceResponse();
		try {
			serviceResponse = clientService.findClientImports(clientId);
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
