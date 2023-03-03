package com.piccolo.airline.model;

import jakarta.persistence.*;

@Entity
@Table (name = "compagnia")
public class Compagnia {
	
	@Id
	@Column (name = "codice")
	private Long codice;
	
	@Column (name = "nome", nullable = false)
	private String nome;
	
	@Column (name = "sede", nullable = false)
	private String sede;
	
	public Compagnia() {
		
	}

	public Compagnia(Long codice, String nome, String sede) {
		this.codice = codice;
		this.nome = nome;
		this.sede = sede;
	}

	public void setCodice(long codice) {
		this.codice = codice;
	}

	public Long getCodice() {
		return codice;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String sede) {
		this.sede = sede;
	}

	public String toString() {
		return "Compagnia [codice=" + codice + ", nome=" + nome + ", sede=" + sede + "]";
	}

}

