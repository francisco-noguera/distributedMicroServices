package com.app.microservices.service;

import java.math.BigDecimal;

import com.app.microservices.bean.SaveClientRequest;
import com.app.microservices.bean.ServiceResponse;

public interface ClientService {
	
	ServiceResponse saveClient(SaveClientRequest saveClientRequest) throws Exception;
	
	ServiceResponse findClient(BigDecimal clientId) throws Exception;
	

}
