package com.piccolo.airline.repository;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.piccolo.airline.model.Prenotazione;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long>{
	
	@Query(value = "SELECT * FROM prenotazione p WHERE p.codice = ?1", nativeQuery = true)
	Prenotazione findByCodice(long codice);
	
	@Query(value = "SELECT * FROM prenotazione p WHERE p.codice_volo = ?1", nativeQuery = true)
	List<Prenotazione> findByCodiceVolo (long codiceVolo);
	
	@Query(value = "SELECT * FROM prenotazione p WHERE p.codice_cliente = ?1", nativeQuery = true)
	List<Prenotazione> findByCodiceCliente (long codiceCliente);
	
	List<Prenotazione> findByData (Date data);
}
