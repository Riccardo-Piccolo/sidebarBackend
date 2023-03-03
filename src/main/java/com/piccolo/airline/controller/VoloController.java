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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.piccolo.airline.model.Scalo;
import com.piccolo.airline.model.Volo;
import com.piccolo.airline.repository.AereoRepository;
import com.piccolo.airline.repository.AeroportoRepository;
import com.piccolo.airline.repository.PrenotazioneRepository;
import com.piccolo.airline.repository.ScaloRepository;
import com.piccolo.airline.repository.VoloRepository;

@RestController
public class VoloController {
	
	@Autowired
	VoloRepository repo;
	
	@Autowired
	ScaloRepository scaloRepo;
	
	@Autowired
	PrenotazioneRepository prenotazioneRepo;
	
	@Autowired
	AereoRepository aereoRepo;
	
	@Autowired
	AeroportoRepository aeroportoRepo;
	
	ScaloController scaloCont = new ScaloController();
	PrenotazioneController prenotazioneCont = new PrenotazioneController();
	
	@CrossOrigin("http://localhost:8080")
	
	@GetMapping("/volo/getCodice")
	public Volo getVoloByCodice(@RequestParam("codice") Optional<Long> codice) {
		return codice.map(param -> repo.findByCodice(param)).orElse(null);
	}
	
	@GetMapping("/volo")
	public List<Volo> getAllVoli() {
		return repo.findAll();
	}
	
	@GetMapping("/volo/getCodiceAereo")
	public List<Volo> getVoloByCodiceAereo(@RequestParam("codice") Optional<Long> codice){
		return codice.map(param -> repo.findByCodiceAereo(param)).orElse(null);
	}
	
	@GetMapping("/volo/getVoliFromToday")
	public List<Volo> getVoliFromToday(){
		long millis = System.currentTimeMillis();
		Date today = new Date(millis);
		return repo.findByToday(today);
	}
	
	@GetMapping("/volo/getDataArrivo")
	public List<Volo> getVoloByDataArrivo(@RequestParam("data") Optional<String> searchParam){
		return searchParam.map(param -> repo.findByDataArrivo(Date.valueOf(param))).orElse(null);
	}
	
	@GetMapping("/volo/getDataPartenza")
	public List<Volo> getVoloByDataPartenza(@RequestParam("data") Optional<String> searchParam){
		return searchParam.map(param -> repo.findByDataPartenza(Date.valueOf(param))).orElse(null);
	}
	
	@GetMapping("/volo/getOraArrivo")
	public List<Volo> getVoloByOraArrivo(@RequestParam("ora") Optional<String> searchParam){
		return searchParam.map(param -> repo.findByOraArrivo(Time.valueOf(param))).orElse(null);
	}
	
	@GetMapping("/volo/getOraPartenza")
	public List<Volo> getVoloByOraPartenza(@RequestParam("ora") Optional<String> searchParam){
		return searchParam.map(param -> repo.findByOraPartenza(Time.valueOf(param))).orElse(null);
	}
	
	@GetMapping("/volo/getPrezzoInterval")
	public List<Volo> getVoloByPrezzoInterval(@RequestParam("min") long prezzoMin, @RequestParam("max") long prezzoMax){
		try {
			return repo.findByPrezzoBetween(prezzoMin, prezzoMax);
		} catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping("/volo/getTerminalDestinazione")
	public List<Volo> getVoloByTerminalDestinazione(@RequestParam("codice") Optional<Long> searchParam){
		return searchParam.map(param -> repo.findByTerminalDestinazione(param)).orElse(null);
	}
	
	@GetMapping("/volo/getTerminalOrigine")
	public List<Volo> getVoloByTerminalOrigine(@RequestParam("codice") Optional<Long> searchParam){
		return searchParam.map(param -> repo.findByTerminalOrigine(param)).orElse(null);
	}
	
	@PostMapping("/volo")
	public Volo addVolo(@RequestBody Map<String, String> body) {
		Volo v = new Volo();
		v.setCodice(Long.parseLong(body.get("codice")));
		long codiceAereo = Long.parseLong(body.get("codiceAereo"));
		v.setCodiceAereo(aereoRepo.findByCodice(codiceAereo));
		v.setDataArrivo(Date.valueOf(body.get("dataArrivo")));
		v.setDataPartenza(Date.valueOf(body.get("dataPartenza")));
		v.setOraPartenza(Time.valueOf(body.get("oraPartenza")));
		v.setOraArrivo(Time.valueOf(body.get("oraArrivo")));
		v.setPrezzo(Float.parseFloat(body.get("prezzo")));
		long destinazione = Long.parseLong(body.get("terminalDestinazione"));
		v.setTerminalDestinazione(aeroportoRepo.findByCodice(destinazione));
		long origine = Long.parseLong(body.get("terminalOrigine"));
		v.setTerminalOrigine(aeroportoRepo.findByCodice(origine));
		return repo.save(v);
	}
	
	@PutMapping("/volo")
	public Volo updateVolo(@RequestParam long id, @RequestBody Map<String, String> body) {
		Volo v = getVoloByCodice(Optional.of(id));
		v.setCodice(Long.parseLong(body.get("codice")));
		long codiceAereo = Long.parseLong(body.get("codiceAereo"));
		v.setCodiceAereo(aereoRepo.findByCodice(codiceAereo));
		v.setDataArrivo(Date.valueOf(body.get("dataArrivo")));
		v.setDataPartenza(Date.valueOf(body.get("dataPartenza")));
		v.setOraPartenza(Time.valueOf(body.get("oraPartenza")));
		v.setOraArrivo(Time.valueOf(body.get("oraArrivo")));
		v.setPrezzo(Float.parseFloat(body.get("prezzo")));
		long destinazione = Long.parseLong(body.get("terminalDestinazione"));
		v.setTerminalDestinazione(aeroportoRepo.findByCodice(destinazione));
		long origine = Long.parseLong(body.get("terminalOrigine"));
		v.setTerminalOrigine(aeroportoRepo.findByCodice(origine));
		return repo.save(v);
	}
	
	@DeleteMapping("/volo")
	public String deleteVolo(@RequestParam("codice") long codice) {
		try {
			repo.deleteById(codice);
			scaloRepo.deleteAll(scaloCont.getScaloByCodiceVolo(codice));
			Optional<Long> codiceOptional = Optional.ofNullable(codice);
			prenotazioneRepo.deleteAll(prenotazioneCont.getPrenotazioniByCodiceVolo(codiceOptional));
			return "Volo con id " + codice + " cancellato";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	@GetMapping("/volo/{id}/getNumeroScali")
	public int getNumeroScali(@PathVariable("id") Optional<Long> id) {
		int count = 0;
		try {
			List<Scalo> scali = scaloRepo.findAll();
			for(Scalo scalo : scali) {
				if(scalo.getCodiceVolo().equals(getVoloByCodice(id))) {
					count++;
				}
			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
