package com.piccolo.airline.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.piccolo.airline.model.Volo;

public interface VoloRepository extends JpaRepository<Volo, Long>{
	
	@Query(value = "SELECT * FROM volo v WHERE v.codice = ?1", nativeQuery = true)
	Volo findByCodice(long codice);
	
	@Query(value = "SELECT * FROM volo v WHERE v.codice_aereo = ?1", nativeQuery = true)
	List<Volo> findByCodiceAereo(long codiceAereo);
	
	@Query(value = "SELECT * FROM volo v WHERE v.origine = ?1", nativeQuery = true)
	List<Volo> findByTerminalOrigine(long origine);
	
	@Query(value = "SELECT * FROM volo v WHERE v.destinazione = ?1", nativeQuery = true)
	List<Volo> findByTerminalDestinazione(long destinazione);
	
	@Query(value = "SELECT * FROM volo v WHERE v.data_partenza >= ?1", nativeQuery = true)
	List<Volo> findByToday(Date today);
	
	List<Volo> findByDataPartenza(Date data);
	
	List<Volo> findByOraPartenza(Time ora);
	
	List<Volo> findByDataArrivo(Date data);
	
	List<Volo> findByOraArrivo (Time ora);
	
	List<Volo> findByPrezzoBetween (long prezzoMin, long prezzoMax);
}
