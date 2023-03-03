package com.piccolo.airline.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.piccolo.airline.model.Aereo;
import com.piccolo.airline.model.Prenotazione;
import com.piccolo.airline.repository.AereoRepository;
import com.piccolo.airline.repository.ClienteRepository;
import com.piccolo.airline.repository.PrenotazioneRepository;
import com.piccolo.airline.repository.VoloRepository;


@RestController
public class PrenotazioneController {
	
	@Autowired
	PrenotazioneRepository repo;
	
	@Autowired
	ClienteRepository clienteRepo;
	
	@Autowired
	VoloRepository voloRepo;
	
	@Autowired
	AereoRepository aereoRepo;
	
	@CrossOrigin("http://localhost:8080")
	
	@GetMapping("/prenotazione")
	public List<Prenotazione> getAllPrenotazioni(){
		return repo.findAll();
	}
	
	@GetMapping("/prenotazione/getCodice")
	public Prenotazione getPrenotazioneByCodice(@RequestParam ("codice") long codice) {
		return repo.findByCodice(codice);
	}
	
	@GetMapping("/prenotazione/getCodiceCliente")
	public List<Prenotazione> getPrenotazioniByCodiceCliente(@RequestParam ("codice") Optional<Long> searchParam){
		return searchParam.map(param -> repo.findByCodiceCliente(param)).orElse(null);
	}
	
	@GetMapping("/prenotazione/getCodiceVolo")
	public List<Prenotazione> getPrenotazioniByCodiceVolo(@RequestParam ("codice") Optional<Long> searchParam){
		return searchParam.map(param -> repo.findByCodiceVolo(param)).orElse(null);
	}
	
	@GetMapping("/prenotazione/getData")
	public List<Prenotazione> getPrenotazioniByData(@RequestParam ("data") Optional<String> searchParam){
		return searchParam.map(param -> repo.findByData(Date.valueOf(param))).orElse(null);
	}
	
	@PostMapping("/prenotazione")
	public Prenotazione addPrenotazione(@RequestBody Map<String, String> body) {
		Prenotazione prenotazione = new Prenotazione();
		prenotazione.setCodice(Long.parseLong(body.get("codice")));
		long codiceCliente = Long.parseLong(body.get("codiceCliente"));
		prenotazione.setCodiceCliente(clienteRepo.findByCodice(codiceCliente));
		long codiceVolo = Long.parseLong(body.get("codiceVolo"));
		prenotazione.setCodiceVolo(voloRepo.findByCodice(codiceVolo));
		prenotazione.setData(Date.valueOf(body.get("data")));
		Aereo aereo = voloRepo.findByCodice(codiceVolo).getCodiceAereo();
		aereo.setPosti(aereo.getPosti() - 1);
		System.out.println("Siamo arrivatiu");
		return repo.save(prenotazione);
	}
	
	@PutMapping("/prenotazione/{id}")
	public Prenotazione updatePrenotazione(@PathVariable("id") Long codice, @RequestBody Map<String, String> body) {
		Prenotazione prenotazione = getPrenotazioneByCodice(codice);
		Aereo aereoPrec = prenotazione.getCodiceVolo().getCodiceAereo();
		aereoPrec.setPosti(aereoPrec.getPosti() + 1);
		prenotazione.setCodice(Long.parseLong(body.get("codice")));
		long codiceCliente = Long.parseLong(body.get("codiceCliente"));
		prenotazione.setCodiceCliente(clienteRepo.findByCodice(codiceCliente));
		long codiceVolo = Long.parseLong(body.get("codiceVolo"));
		prenotazione.setCodiceVolo(voloRepo.findByCodice(codiceVolo));
		prenotazione.setData(Date.valueOf(body.get("data")));
		Aereo aereo = voloRepo.findByCodice(codiceVolo).getCodiceAereo();
		aereo.setPosti(aereo.getPosti() - 1);
		return repo.save(prenotazione);
	}
	
	@DeleteMapping("/prenotazione/{id}")
	public void deletePrenotazione(@PathVariable Long id) {	
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
