package com.app.microservices2.service;

import java.util.Map;

public interface HttpMessageSendingService {

	boolean sendPostJsonMessage(String httpURL, Map<String, String> headerMap, String jsonMessage);
	
	Map<String, Object> sendGetMessage(String httpURL, Map<String, String> headerMap);
}
