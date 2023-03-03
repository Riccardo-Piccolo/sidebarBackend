package com.piccolo.airline.model;

import jakarta.persistence.*;

@Entity
@Table (name = "aereo")
public class Aereo {
	
	@Id
	@Column(name = "codice")
	private Long codice;
	
	@ManyToOne
	@JoinColumn(name = "codice_compagnia", nullable = false, referencedColumnName = "codice")
	private Compagnia compagnia;
	
	@Column(name = "modello", nullable = false)
	private String modello;
	
	@Column(name = "posti", nullable = false)
	private Long posti;
	
	@Column(name = "anno_costruzione", nullable = false)
	private Long annoCostruzione;
	
	public Aereo(Long codice, Compagnia compagnia, String modello, Long posti, Long annoCostruzione) {
		this.codice = codice;
		this.compagnia = compagnia;
		this.modello = modello;
		this.posti = posti;
		this.annoCostruzione = annoCostruzione;
	}

	public Aereo() {
		
	}

	public Compagnia getCompagnia() {
		return compagnia;
	}

	public void setCompagnia(Compagnia compagnia) {
		this.compagnia = compagnia;
	}

	public Long getCodice() {
		return codice;
	}
	
	public void setCodice(Long codice) {
		this.codice = codice;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public Long getPosti() {
		return posti;
	}

	public void setPosti(Long posti) {
		this.posti = posti;
	}

	public Long getAnnoCostruzione() {
		return annoCostruzione;
	}

	public void setAnnoCostruzione(Long annoCostruzione) {
		this.annoCostruzione = annoCostruzione;
	}

	public String toString() {
		return "Aereo [codice=" + codice + ", codiceCompagnia=" + compagnia + ", modello=" + modello + ", posti="
				+ posti + ", annoCostruzione=" + annoCostruzione + "]";
	}

}

