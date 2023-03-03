package com.piccolo.airline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.piccolo.airline.model.Aeroporto;

public interface AeroportoRepository extends JpaRepository<Aeroporto, Long> {
	
	@Query(value = "SELECT * FROM aeroporto a WHERE a.nome LIKE %?1%", nativeQuery = true)
	Aeroporto findByNome(String nome);
	
	@Query(value = "SELECT * FROM aeroporto a WHERE a.citta LIKE %?1%", nativeQuery = true)
	List<Aeroporto> findByCitta(String citta);

	@Query(value = "SELECT * FROM aeroporto a WHERE a.codice = ?1", nativeQuery = true)
	Aeroporto findByCodice(long codice);

}
