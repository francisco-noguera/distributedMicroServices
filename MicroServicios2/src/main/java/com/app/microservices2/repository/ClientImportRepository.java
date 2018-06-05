package com.app.microservices2.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.microservices2.domain.ClientImport;


public interface ClientImportRepository extends JpaRepository<ClientImport, BigDecimal>{
	
	@Query(name="SELECT client_import FROM client_import WHERE client_id_fk = ?1",nativeQuery=true)
	public List<BigDecimal> findImportsByClientId(BigDecimal clientId) throws Exception;

}
