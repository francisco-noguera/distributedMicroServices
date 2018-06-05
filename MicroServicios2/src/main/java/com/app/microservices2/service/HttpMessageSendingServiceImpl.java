package com.app.microservices2.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.app.microservices2.AppProperties;
import com.app.microservices2.util.MicroServicesConstants;

@Service
public class HttpMessageSendingServiceImpl implements HttpMessageSendingService {
	private static final Logger logger = LoggerFactory.getLogger(HttpMessageSendingServiceImpl.class);
	
    @Autowired
    private AppProperties appProperties;
    
	@Override
	public boolean sendPostJsonMessage(String httpURL, Map<String, String> headerMap, String jsonMessage) {
		Boolean flag = false;
		try {
			URL url = new URL(httpURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(appProperties.getHttpClientConnectTimeout());
			con.setRequestMethod(RequestMethod.POST.name());
			
			//--add request headers--//
			if(MapUtils.isNotEmpty(headerMap)) {
				headerMap.forEach((k, v)->{
					con.setRequestProperty(k, v);
				});				
			}
			
			// Send post request
			con.setDoOutput(true);
			
			//--add request body--//
			if(StringUtils.isNotEmpty(jsonMessage)) {
				byte[] postData = jsonMessage.getBytes(StandardCharsets.UTF_8);
				int postDataLength = postData.length;
				con.setRequestProperty(MicroServicesConstants.LABEL_CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
				con.setRequestProperty(MicroServicesConstants.LABEL_CONTENT_LENGTH, String.valueOf(postDataLength));				
				con.getOutputStream().write(postData);
				logger.info(".:: send jsonMessage ::. {} ", jsonMessage);
			}
			
			//--Status Request--//
			int httpStatus = con.getResponseCode();
			if(httpStatus >= 200 && httpStatus <= 299) {
				logger.info(".:: Success Send jsonMessage : {}", con.getResponseCode());
				flag = true;
			}else {
				logger.warn(".:: Error Send jsonMessage : {} to this url={} with this headers={}", con.getResponseCode(), httpURL, headerMap);
				flag = false;
			}
			
		}catch(Exception e) {
			logger.error(".:: Error sendPostJsonMessage to this url={} with this headers={}", httpURL, headerMap, e);
			flag = false;			
		}
		return flag;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> sendGetMessage(String httpURL, Map<String, String> headerMap) {
		Map<String, Object> jsonMap = null;
		try {
			URL url = new URL(httpURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(appProperties.getHttpClientConnectTimeout());
			con.setRequestMethod(RequestMethod.GET.name());
			
			//--add request headers--//
			if(MapUtils.isNotEmpty(headerMap)) {
				headerMap.forEach((k, v)->{
					con.setRequestProperty(k, v);
				});				
			}
			
			// Send post request
			con.setDoOutput(true);
			//--Status Request--//
			int httpStatus = con.getResponseCode();
			if(httpStatus >= 200 && httpStatus <= 299) {
				// Read output
				ObjectMapper mapper = new ObjectMapper();
				jsonMap = mapper.readValue(con.getInputStream(), Map.class);
			}			
		}catch(Exception e) {
			logger.error(".:: Error sendGetMessage ", e);
			jsonMap = null;			
		}
		return jsonMap;
	}
}
