package com.app.microservices2.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="client_import")
public class ClientImport extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name="client_fk_id")
	private BigDecimal clientId;
	@Column(name="client_import")
	private BigDecimal clientImport;
	
	public ClientImport() {}
	
	public ClientImport(BigDecimal clientId, BigDecimal clientImport) {
		this.clientId = clientId;
		this.clientImport = clientImport;
	}
	
	public BigDecimal getClientId() {
		return clientId;
	}
	public void setClientId(BigDecimal clientId) {
		this.clientId = clientId;
	}
	public BigDecimal getClientImport() {
		return clientImport;
	}
	public void setClientImport(BigDecimal clientImport) {
		this.clientImport = clientImport;
	}
	
}
