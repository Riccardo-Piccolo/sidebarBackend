package com.piccolo.airline.controller;

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
public class CompagniaController {
	
	@Autowired
	CompagniaRepository repo;
	
	@Autowired
	AereoRepository aereoRepo;
	
	@Autowired
	VoloRepository voloRepo;
	
	VoloController voloCont = new VoloController();
	
	@CrossOrigin("http://localhost:8080")
	
	@GetMapping("/compagnia")
	public List<Compagnia> getAllCompagnie(){
		return repo.findAll();
	}
	
	@GetMapping("/compagnia/getNome")
	public Compagnia getCompagniaByNome(@RequestParam("nome") Optional<String> searchParam){
		return searchParam.map(param -> repo.findByNome(param)).orElse(null);
	}
	
	@GetMapping("/compagnia/getSede")
	public List<Compagnia> getCompagniaBySede(@RequestParam("sede") Optional<String> searchParam){
		return searchParam.map(param -> repo.findBySede(param)).orElse(null);
	}
	
	@GetMapping("/compagnia/getCodice")
	public Compagnia getCompagniaByCodice(@RequestParam("codice") long codice) {
		return repo.findByCodice(codice);
	}
	
	@PostMapping("/compagnia")
	public Compagnia addCompagnia(@RequestBody Map<String, String> body) {
		Compagnia compagnia = new Compagnia();
		compagnia.setCodice(Long.parseLong(body.get("codice")));
		compagnia.setNome(body.get("nome"));
		compagnia.setSede(body.get("sede"));
		return repo.save(compagnia);
	}
	
	@PutMapping("/compagnia")
	public Compagnia updateCompagnia(@RequestParam long id, @RequestBody Map<String, String> body) {
		Compagnia compagnia = getCompagniaByCodice(id);
		compagnia.setCodice(Long.parseLong(body.get("codice")));
		compagnia.setNome(body.get("nome"));
		compagnia.setSede(body.get("sede"));
		return repo.save(compagnia);
	}
	
	@DeleteMapping("/compagnia")
	public String deleteCompagnia(@RequestParam("id") long id) {
		try {
			repo.deleteById(id);
			for (Aereo a : aereoRepo.findByCompagnia(getCompagniaByCodice(id))) {
				long codiceAereo = a.getCodice();
				for(Volo v : voloRepo.findByCodiceAereo(codiceAereo)) {
					voloCont.deleteVolo(v.getCodice());
				}
				aereoRepo.delete(a);
			}
			return "Compagnia con id " + id + " cancellata";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}
