package com.app.microservices.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.microservices.bean.SaveClientRequest;
import com.app.microservices.bean.ServiceResponse;
import com.app.microservices.domain.Client;
import com.app.microservices.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public ServiceResponse saveClient(SaveClientRequest saveClientRequest) throws Exception {
		Client client = saveClientRequestToClient(saveClientRequest);
		clientRepository.save(client);
		ServiceResponse response = new ServiceResponse("OK");
		return response;
	}

	@Override
	public ServiceResponse findClient(BigDecimal clientId) throws Exception {
		Optional<Client> client = clientRepository.findById(clientId);
		ServiceResponse response = new ServiceResponse();
		response.setResponseData(client.get());
		return response;
	}

	private Client saveClientRequestToClient(SaveClientRequest saveClientRequest) {
		Client client = new Client();
		client.setId(saveClientRequest.getId());
		client.setName(saveClientRequest.getName());
		client.setPhone(saveClientRequest.getPhone());
		client.setAddress(saveClientRequest.getAddress());
		return client;
	}
	
	
}
