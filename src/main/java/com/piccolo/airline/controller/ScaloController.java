package com.piccolo.airline.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.piccolo.airline.model.Scalo;
import com.piccolo.airline.repository.AeroportoRepository;
import com.piccolo.airline.repository.ScaloRepository;
import com.piccolo.airline.repository.VoloRepository;

@RestController
public class ScaloController {

	@Autowired
	ScaloRepository repo;
	
	@Autowired
	AeroportoRepository aeroportoRepo;
	
	@Autowired
	VoloRepository voloRepo;
	
	@CrossOrigin("http://localhost:8080")
	
	@GetMapping("/scalo/getCodice")
	public Scalo getScaloByCodice(@RequestParam("codice") long codice) {
		return repo.findByCodice(codice);
	}
	
	@GetMapping("/scalo")
	public List<Scalo> getAllScali() {
		return repo.findAll();
	}
	
	@GetMapping("/scalo/getCodiceAeroporto")
	public List<Scalo> getScaloByCodiceAeroporto(@RequestParam("codice") long codice){
		return repo.findByCodiceAeroporto(codice);
	}
	
	@GetMapping("/scalo/getCodiceVolo")
	public List<Scalo> getScaloByCodiceVolo(@RequestParam("codice") long codice){
		return repo.findByCodiceVolo(codice);
	}
	
	@GetMapping("/scalo/getDataScalo")
	public List<Scalo> getScaloByData(@RequestParam("data") Optional<String> searchParam){
		return searchParam.map(param -> repo.findByDataScalo(Date.valueOf(param))).orElse(null);
	}
	
	@GetMapping("/scalo/getOraScalo")
	public List<Scalo> getScaloByOra(@RequestParam("ora") Optional<String> searchParam){
		return searchParam.map(param -> repo.findByOraScalo(Time.valueOf(param))).orElse(null);
	}
	
	@PostMapping("/scalo")
	public Scalo addScalo(@RequestBody Map<String, String> body) {
		Scalo s = new Scalo();
		s.setCodice(Long.parseLong(body.get("codice")));
		long codiceAeroporto = Long.parseLong(body.get("codiceAeroporto"));
		s.setCodiceAeroporto(aeroportoRepo.findByCodice(codiceAeroporto));
		long codiceVolo = Long.parseLong(body.get("codiceVolo")); 
		s.setCodiceVolo(voloRepo.findByCodice(codiceVolo));
		s.setDataScalo(Date.valueOf(body.get("data")));
		s.setOraScalo(Time.valueOf(body.get("ora")));
		return repo.save(s);
	}
	
	@PutMapping("/scalo")
	public Scalo updateScalo(@RequestParam("codice") long codice, @RequestBody Map<String, String> body) {
		Scalo s = getScaloByCodice(codice);
		s.setCodice(Long.parseLong(body.get("codice")));
		long codiceAeroporto = Long.parseLong(body.get("codiceAeroporto"));
		s.setCodiceAeroporto(aeroportoRepo.findByCodice(codiceAeroporto));
		long codiceVolo = Long.parseLong(body.get("codiceVolo")); 
		s.setCodiceVolo(voloRepo.findByCodice(codiceVolo));
		s.setDataScalo(Date.valueOf(body.get("data")));
		s.setOraScalo(Time.valueOf(body.get("ora")));
		return repo.save(s);
	}
	
	@DeleteMapping("/scalo")
	public String deleteScalo(@RequestParam("codice") long codice) {
		try {
			repo.deleteById(codice);
			return "Scalo con id " + codice + " cancellato";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
