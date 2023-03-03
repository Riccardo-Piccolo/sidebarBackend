package com.piccolo.airline.repository;

import com.piccolo.airline.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Query(value = "SELECT * FROM cliente c WHERE c.citta LIKE %?1%", nativeQuery = true)
	List<Cliente> getClienteByCitta(String citta);
	
	@Query(value = "SELECT * FROM Cliente c WHERE c.data = ?1", nativeQuery = true)
	List<Cliente> findByData(Date data);
	
	@Query(value = "SELECT * FROM cliente c WHERE c.indirizzo LIKE %?1%", nativeQuery = true)
	List<Cliente> getClienteByIndirizzo(String indirizzo);
	
	@Query(value = "SELECT * FROM cliente c WHERE c.nome LIKE %?1%", nativeQuery = true)
	List<Cliente> getClienteByNome(String nome);
	
	@Query(value = "SELECT * FROM cliente c WHERE c.numeroTelefono LIKE %?1%", nativeQuery = true)
	List<Cliente> getClienteByNumeroTelefono(String numero);
	
	@Query(value = "SELECT * FROM cliente c WHERE c.codice = ?1", nativeQuery = true)
	Cliente findByCodice(long codice);
	
}
