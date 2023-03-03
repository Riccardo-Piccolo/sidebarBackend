package com.piccolo.airline.controller;

import java.util.ArrayList;
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

import com.piccolo.airline.model.Aereo;
import com.piccolo.airline.model.Compagnia;
import com.piccolo.airline.model.Volo;
import com.piccolo.airline.repository.AereoRepository;
import com.piccolo.airline.repository.CompagniaRepository;
import com.piccolo.airline.repository.VoloRepository;

@RestController
public class AereoController {
	
	@Autowired
	AereoRepository repo;
	
	@Autowired
	VoloRepository voloRepo;
	
	@Autowired
	CompagniaRepository compagniaRepo;
	
	VoloController voloCont = new VoloController();
	
	@CrossOrigin("http://localhost:8080")
	
	@GetMapping("/aereo")
	public List<Aereo> getAllAerei(){
		return repo.findAll();
	}
	
	@GetMapping("/aereo/getCodice")
	public Aereo getByCodice(@RequestParam("codice") long codice) {
		return repo.findByCodice(codice);
	}
	
	@GetMapping("/aereo/getModello")
	public List<Aereo> getByModello(@RequestParam("modello") Optional<String> searchParam){
		return searchParam.map(param -> repo.findByModello(param))
				.orElse(null);
	}
	
	@GetMapping("/aereo/postiInterval")
	public List<Aereo> getByPostiInterval(@RequestParam("min") Long postiMin, @RequestParam("max") Long postiMax){
		if (postiMin < postiMax)
			return repo.findByPostiBetween(postiMin, postiMax);
		else
			return null;
	}
	
	@GetMapping("/aereo/getPosti")
	public List<Aereo> getByPosti(@RequestParam("posti") Optional<Long> searchParam){
		return searchParam.map(param -> repo.findByPosti(param)).orElse(null);
	}
	
	@GetMapping("/aereo/annoInterval")
	public List<Aereo> getByAnnoInterval(@RequestParam("min") Long annoMin, @RequestParam("max") Long annoMax){
		if (annoMin < annoMax)
			return repo.findByAnnoCostruzioneBetween(annoMin, annoMax);
		else
			return null;
	}
	
	@GetMapping("/aereo/getAnnoCostruzione")
	public List<Aereo> getByAnnoCostruzione(@RequestParam("anno") Optional<Long> searchParam){
		return searchParam.map(param -> repo.findByAnnoCostruzione(param)).orElse(null);
	}
	
	@GetMapping("/aereo/getCompagnia")
	public List<Aereo> getByCompagnia(@RequestParam("codice") Long codice){
		List<Aereo> aerei = new ArrayList<Aereo>();
		Compagnia comp = new Compagnia();
		comp.setCodice(codice);
		repo.findByCompagnia(comp).forEach(aerei::add);
		if (aerei.isEmpty())
			return null;
		else
			return aerei;
	}
	
	@PostMapping("/aereo")
	public Aereo addAereo(@RequestBody Map<String, String> body) {
		long codice = Long.parseLong(body.get("codice"));
		long annoCost = Long.parseLong(body.get("annoCostruzione"));
		String modello = body.get("modello");
		long posti = Long.parseLong(body.get("posti"));
		long codiceComp = Long.parseLong(body.get("codiceCompagnia"));
		return repo.save(new Aereo(codice, compagniaRepo.findByCodice(codiceComp), modello, posti, annoCost));
	}
	
	@PutMapping("/aereo")
	public Aereo updateAereo(@RequestParam ("codice") long id, @RequestBody Map<String, String> body) {
		Aereo aereo = getByCodice(id);
		aereo.setAnnoCostruzione(Long.parseLong(body.get("annoCostruzione")));
		aereo.setModello(body.get("modello"));
		aereo.setPosti(Long.parseLong(body.get("posti")));
		long codiceComp = Long.parseLong(body.get("codiceCompagnia"));
		aereo.setCompagnia(compagniaRepo.findByCodice(codiceComp));
		return repo.save(aereo);
	}
	
	@DeleteMapping(value = "/aereo")
	public String deleteAereo(@RequestParam ("id") long id) {
		try {
			repo.deleteById(id);
			for (Volo v : voloRepo.findByCodiceAereo(id)) {
				voloCont.deleteVolo(v.getCodice());
			}
			return "Aereo con id " + id + " cancellato";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
