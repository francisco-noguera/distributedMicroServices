package com.app.microservices2.bean;

import java.math.BigDecimal;
import java.util.List;

import com.app.microservices2.model.Client;

public class ClientImportsResponse {

	private Client client;
	private List<BigDecimal> imports;
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<BigDecimal> getImports() {
		return imports;
	}
	public void setImports(List<BigDecimal> imports) {
		this.imports = imports;
	} 
	
}
