package com.app.microservices2.bean;

import java.math.BigDecimal;
import java.util.List;

public class SaveClientImportRequest {

	private BigDecimal clientId;
    private List<BigDecimal> imports;

    public SaveClientImportRequest() {
    }

	public BigDecimal getClientId() {
		return clientId;
	}

	public void setClientId(BigDecimal clientId) {
		this.clientId = clientId;
	}

	public List<BigDecimal> getImports() {
		return imports;
	}

	public void setImports(List<BigDecimal> imports) {
		this.imports = imports;
	}


}
