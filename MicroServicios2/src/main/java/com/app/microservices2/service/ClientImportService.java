package com.app.microservices2.service;

import java.math.BigDecimal;

import com.app.microservices2.bean.SaveClientImportRequest;
import com.app.microservices2.bean.ServiceResponse;

public interface ClientImportService {
	
	ServiceResponse saveClientImport(SaveClientImportRequest saveClientRequest) throws Exception;
	
	ServiceResponse findClientImports(BigDecimal clientId) throws Exception;
	

}
