package com.piccolo.airline.model;

import jakarta.persistence.*;

@Entity
@Table (name = "aeroporto")
public class Aeroporto {
	
	@Id
	@Column (name = "codice")
	private Long codice;

	@Column (name = "citta", nullable = false)
	private String citta;
	
	@Column (name = "nome", nullable = false)
	private String nome;
	
	public Aeroporto() {
		
	}

	public Aeroporto(Long codice, String citta, String nome) {
		this.codice = codice;
		this.citta = citta;
		this.nome = nome;
	}

	public void setCodice(Long codice) {
		this.codice = codice;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Long getCodice() {
		return codice;
	}

	public String toString() {
		return "Aeroporto [codice=" + codice + ", citta=" + citta + ", nome=" + nome + "]";
	}

}
