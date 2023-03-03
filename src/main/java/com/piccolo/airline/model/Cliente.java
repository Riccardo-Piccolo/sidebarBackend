package com.piccolo.airline.model;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table (name = "cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "codice")
	private Long codice;
	
	@Column (name = "citta", nullable = false)
	private String citta;
	
	@Column (name = "data", nullable = false)
	private Date data;
	
	@Column (name = "indirizzo", nullable = false)
	private String indirizzo;
	
	@Column (name = "nome", nullable = false)
	private String nome;
	
	@Column (name = "numeroTelefono", nullable = false)
	private String numeroTelefono;

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	public Long getCodice() {
		return codice;
	}

	public void setCodice(Long codice) {
		this.codice = codice;
	}

	public String toString() {
		return "Cliente [codice=" + codice + ", citta=" + citta + ", data=" + data + ", indirizzo=" + indirizzo
				+ ", nome=" + nome + ", numeroTelefono=" + numeroTelefono + "]";
	}
}
