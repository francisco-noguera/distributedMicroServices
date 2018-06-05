package com.app.microservices2.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.microservices2.bean.SaveClientImportRequest;
import com.app.microservices2.bean.ServiceResponse;
import com.app.microservices2.domain.ClientImport;
import com.app.microservices2.model.Client;
import com.app.microservices2.repository.ClientImportRepository;
import com.app.microservices2.AppProperties;
import com.app.microservices2.bean.ClientImportsResponse;

@Service
public class ClientImportServiceImpl implements ClientImportService {

	@Autowired
	private ClientImportRepository clientImportRepository;
	
	@Autowired
	private HttpMessageSendingService httpMessageSendingService;
	
	@Autowired
	private AppProperties appProperties; 
	
	@Override
	public ServiceResponse saveClientImport(SaveClientImportRequest saveClientRequest) throws Exception {
		
		saveClientRequest.getImports().forEach(amount -> {
			ClientImport clientImport = new ClientImport(saveClientRequest.getClientId(), amount);
			clientImportRepository.save(clientImport);
		});
		ServiceResponse response = new ServiceResponse("OK");
		return response;
	}

	@Override
	public ServiceResponse findClientImports(BigDecimal clientId) throws Exception {

		StringBuilder endpointUrl = new StringBuilder(appProperties.getObtainClientEndPoint());
		endpointUrl.append("?clientId=").append(clientId);
		
        
		ClientImportsResponse clientImportResponse = new ClientImportsResponse(); 
		
		Map<String, String> headerMap = new HashMap<>();
		Map<String, Object> jsonMap = httpMessageSendingService.sendGetMessage(endpointUrl.toString(),headerMap);
		Client client = getClientFromServiceResponse(jsonMap.get("responseData")); 
		List<BigDecimal> imports = clientImportRepository.findImportsByClientId(clientId);
		clientImportResponse.setClient(client);
		clientImportResponse.setImports(imports);
		ServiceResponse response = new ServiceResponse();
		response.setResponseData(clientImportResponse);
		return response;
	}
	
	@SuppressWarnings("unchecked")
	private Client getClientFromServiceResponse(Object object) {
		Map<String, Object> clientMap = (Map<String, Object>) object;
		Client client = new Client();
		client.setId(new BigDecimal((Integer)clientMap.get("id")));
		client.setName((String)clientMap.get("name"));
		client.setPhone((String)clientMap.get("phone"));
		client.setAddress((String)clientMap.get("address"));
		return client;
	}
	
}
