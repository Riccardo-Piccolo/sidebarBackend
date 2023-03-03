package com.piccolo.airline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.piccolo.airline.model.Compagnia;

public interface CompagniaRepository extends JpaRepository<Compagnia, Long>{
	
	@Query(value = "SELECT * FROM compagnia c WHERE c.nome LIKE %?1%", nativeQuery = true)
	Compagnia findByNome(String nome);
	
	@Query(value = "SELECT * FROM compagnia c WHERE c.sede LIKE %?1%", nativeQuery = true)
	List<Compagnia> findBySede(String sede);
	
	@Query(value = "SELECT * FROM compagnia c WHERE c.codice = ?1", nativeQuery = true)
	Compagnia findByCodice(long codice);
}
