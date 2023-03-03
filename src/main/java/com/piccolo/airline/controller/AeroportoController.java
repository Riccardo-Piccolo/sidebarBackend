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

import com.piccolo.airline.model.Aeroporto;
import com.piccolo.airline.repository.AeroportoRepository;

@RestController
public class AeroportoController {
	
	@Autowired
	AeroportoRepository repo;
	
	@CrossOrigin("http://localhost:8080")
	
	@GetMapping("/aeroporto")
	public List<Aeroporto> getAllAeroporti(){
		return repo.findAll();
	}
	
	@GetMapping("/aeroporto/getNome")
	public Aeroporto getAeroportoByNome(@RequestParam("nome") Optional<String> searchParam) {
		return searchParam.map(param -> repo.findByNome(param)).orElse(null);
	}
	
	@GetMapping("/aeroporto/getCitta")
	public List<Aeroporto> getAeroportoByCitta(@RequestParam("citta") Optional<String> searchParam){
		return searchParam.map(param -> repo.findByCitta(param)).orElse(null);
	}
	
	@GetMapping("/aeroporto/getCodice")
	public Aeroporto getAeroportoByCodice(@RequestParam("codice") long codice) {
		return repo.findByCodice(codice);
	}
	
	@PostMapping("/aeroporto")
	public Aeroporto addAeroporto(@RequestBody Map<String, String> body) {
		Aeroporto a = new Aeroporto();
		a.setCitta(body.get("citta"));
		a.setCodice(Long.parseLong(body.get("codice")));
		a.setNome(body.get("nome"));
		return repo.save(a);
	}
	
	@PutMapping("/aeroporto")
	public Aeroporto updateAeroporto(@RequestParam ("codice") long id, @RequestBody Map<String, String> body) {
		Aeroporto aeroporto = getAeroportoByCodice(id);
		aeroporto.setCitta(body.get("citta"));
		aeroporto.setCodice(Long.parseLong(body.get("codice")));
		aeroporto.setNome(body.get("nome"));
		return repo.save(aeroporto);
	}
	
	@DeleteMapping("/aeroporto/delete")
	public String deleteAeroporto(@RequestParam("codice") long codice) {
		repo.deleteById(codice);
		return "Aeroporto con id " + codice + " cancellato";
	}

}
