package com.piccolo.airline.repository;

import java.util.List;
import java.sql.Date;
import java.sql.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.piccolo.airline.model.Scalo;

public interface ScaloRepository extends JpaRepository<Scalo, Long> {
	
	@Query(value = "SELECT * FROM scalo s WHERE s.codice = ?1", nativeQuery = true)
	Scalo findByCodice(long codice);
	
	@Query(value = "SELECT * FROM scalo s WHERE s.codice_volo = ?1", nativeQuery = true)
	List<Scalo> findByCodiceVolo (long codiceVolo);
	
	@Query(value = "SELECT * FROM scalo s WHERE s.codice_aeroporto = ?1", nativeQuery = true)
	List<Scalo> findByCodiceAeroporto (long codiceAeroporto);
	
	List<Scalo> findByDataScalo (Date data);
	
	List<Scalo> findByOraScalo (Time ora);
}
