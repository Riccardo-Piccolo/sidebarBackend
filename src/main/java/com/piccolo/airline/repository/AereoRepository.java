package com.piccolo.airline.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.piccolo.airline.model.Aereo;
import com.piccolo.airline.model.Compagnia;


public interface AereoRepository extends JpaRepository<Aereo, Long>{
	List<Aereo> findByCompagnia(Compagnia compagnia);
	
	@Query(value = "SELECT * FROM aereo a WHERE a.modello LIKE %?1%", nativeQuery = true)
	List<Aereo> findByModello(String modello);
	
	@Query(value = "SELECT * FROM aereo a WHERE a.posti BETWEEN ?1 AND ?2", nativeQuery = true)
	List<Aereo> findByPostiBetween(long postiMin, long postiMax);
	
	@Query(value = "SELECT * FROM aereo a WHERE a.posti = ?1", nativeQuery = true)
	List<Aereo> findByPosti(long posti);
	
	@Query(value = "SELECT * FROM aereo a WHERE a.anno_costruzione BETWEEN ?1 AND ?2", nativeQuery = true)
	List<Aereo> findByAnnoCostruzioneBetween(long annoMin, long annoMax);
	
	@Query(value = "SELECT * FROM aereo a WHERE a.anno_costruzione = ?1", nativeQuery = true)
	List<Aereo> findByAnnoCostruzione(long anno);
	
	@Query(value = "SELECT * FROM aereo a WHERE a.codice = ?1", nativeQuery = true)
	Aereo findByCodice(long codice);
}
