package com.app.microservices2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
public class AppProperties {

	@Value( "${http.client.connect.timeout}" )
	private int httpClientConnectTimeout;
	
	@Value( "${http.client.obtainClient.endPoint}" )
	private String obtainClientEndPoint;

	public int getHttpClientConnectTimeout() {
		return httpClientConnectTimeout;
	}

	public String getObtainClientEndPoint() {
		return obtainClientEndPoint;
	}
}
