package com.piccolo.airline.controller;

import java.sql.Date;
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

import com.piccolo.airline.model.Cliente;
import com.piccolo.airline.repository.ClienteRepository;

@RestController
public class ClienteController {
	
	@Autowired
	private ClienteRepository repo;
	
	@CrossOrigin("http://localhost:8080")
	
	@GetMapping("/cliente")
	public List<Cliente> getAllClienti(){
		return repo.findAll();
	}
	
	@GetMapping("/cliente/getCodice")
	public Cliente getByCodice(@RequestParam("codice") long codice) {
		return repo.findByCodice(codice);
	}
	
	@GetMapping("/cliente/getCitta")
	public List<Cliente> getByCitta(@RequestParam("citta") Optional<String> searchParam){
		return searchParam.map(param -> repo.getClienteByCitta(param))
				.orElse(repo.findAll());
	}
	
	@GetMapping("/cliente/getData")
	public List<Cliente> getByDataNascita (@RequestParam("dataNascita") Optional<String> searchParam){
		return searchParam.map(param -> repo.findByData(Date.valueOf(param))).orElse(repo.findAll());
	}
	
	
	@GetMapping("/cliente/getIndirizzo")
	public List<Cliente> getByIndirizzo(@RequestParam("indirizzo") Optional<String> searchParam){
		return searchParam.map(param -> repo.getClienteByIndirizzo(param))
				.orElse(repo.findAll());
	}
	
	@GetMapping("/cliente/getNome")
	public List<Cliente> getByNome(@RequestParam("nome") Optional<String> searchParam){
		return searchParam.map(param -> repo.getClienteByNome(param))
				.orElse(repo.findAll());
	}
	
	@GetMapping("/cliente/getNumero")
	public List<Cliente> getByNumeroTelefono(@RequestParam("telefono") Optional<String> searchParam){
		return searchParam.map(param -> repo.getClienteByNumeroTelefono(param))
				.orElse(repo.findAll());
	}
	
	@PostMapping("/cliente")
	public Cliente addCliente(@RequestBody Map<String, String> body) {
		Cliente cliente = new Cliente();
		cliente.setCitta(body.get("citta"));
		cliente.setCodice(Long.parseLong(body.get("codice")));
		cliente.setData(Date.valueOf(body.get("data")));
		cliente.setIndirizzo(body.get("indirizzo"));
		cliente.setNome(body.get("nome"));
		cliente.setNumeroTelefono(body.get("numero"));
		return repo.save(cliente);
	}
	
	@PutMapping("/cliente")
	public Cliente updateCliente(@RequestParam ("codice") long id, @RequestBody Map<String, String> body) {
		Cliente cliente = getByCodice(id);
		cliente.setCitta(body.get("citta"));
		cliente.setCodice(Long.parseLong(body.get("codice")));
		cliente.setData(Date.valueOf(body.get("data")));
		cliente.setIndirizzo(body.get("indirizzo"));
		cliente.setNome(body.get("nome"));
		cliente.setNumeroTelefono(body.get("numero"));
		return repo.save(cliente);
	}
	
	@DeleteMapping("/cliente")
	public String deleteCliente(@RequestParam ("codice") long id) {
		repo.deleteById(id);
		return "Cliente cancellato";
	}

}
