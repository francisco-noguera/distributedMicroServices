package com.app.microservices.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.microservices.domain.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, BigDecimal>{
	
	//JPQL Query
	@Query("SELECT c FROM Client c where name = ?1")
	public List<Client> findByName(String name);
	
	//Native Query
	@Query(value="SELECT * FROM client where address = ?1", nativeQuery=true)
	public List<Client> findByAddress(String address);
	
	//Default Query
	public List<Client> findByPhone(String phone);

}
