package com.piccolo.airline.model;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.*;


@Entity
@Table (name = "volo")
public class Volo {
	
	@Id
	@Column (name = "codice")
	private Long codice;
	
	@ManyToOne
	@JoinColumn(name = "codice_aereo", nullable = false, referencedColumnName = "codice")
	private Aereo codiceAereo;
	
	@ManyToOne
	@JoinColumn(name = "origine", nullable = false, referencedColumnName = "codice")
	private Aeroporto terminalOrigine;
	
	@ManyToOne
	@JoinColumn(name = "destinazione", nullable = false, referencedColumnName = "codice")
	private Aeroporto terminalDestinazione;
	
	@Column (name = "data_partenza", nullable = false)
	private Date dataPartenza;
	
	@Column (name = "ora_partenza", nullable = false)
	private Time oraPartenza;
	
	@Column (name = "data_arrivo", nullable = false)
	private Date dataArrivo;
	
	@Column (name = "ora_arrivo", nullable = false)
	private Time oraArrivo;
	
	@Column (name = "prezzo", nullable = false)
	private Float prezzo;

	public Volo() {
		
	}

	public Volo(Long codice, Aereo codiceAereo, Aeroporto terminalOrigine, Aeroporto terminalDestinazione,
			Date dataPartenza, Time oraPartenza, Date dataArrivo, Time oraArrivo, Float prezzo) {
		this.codice = codice;
		this.codiceAereo = codiceAereo;
		this.terminalOrigine = terminalOrigine;
		this.terminalDestinazione = terminalDestinazione;
		this.dataPartenza = dataPartenza;
		this.oraPartenza = oraPartenza;
		this.dataArrivo = dataArrivo;
		this.oraArrivo = oraArrivo;
		this.prezzo = prezzo;
	}

	public void setCodice(Long codice) {
		this.codice = codice;
	}

	public Aereo getCodiceAereo() {
		return codiceAereo;
	}

	public void setCodiceAereo(Aereo codiceAereo) {
		this.codiceAereo = codiceAereo;
	}

	public Aeroporto getTerminalOrigine() {
		return terminalOrigine;
	}

	public void setTerminalOrigine(Aeroporto terminalOrigine) {
		this.terminalOrigine = terminalOrigine;
	}

	public Aeroporto getTerminalDestinazione() {
		return terminalDestinazione;
	}

	public void setTerminalDestinazione(Aeroporto terminalDestinazione) {
		this.terminalDestinazione = terminalDestinazione;
	}

	public Date getDataPartenza() {
		return dataPartenza;
	}

	public void setDataPartenza(Date dataPartenza) {
		this.dataPartenza = dataPartenza;
	}

	public Time getOraPartenza() {
		return oraPartenza;
	}

	public void setOraPartenza(Time oraPartenza) {
		this.oraPartenza = oraPartenza;
	}

	public Date getDataArrivo() {
		return dataArrivo;
	}

	public void setDataArrivo(Date dataArrivo) {
		this.dataArrivo = dataArrivo;
	}

	public Time getOraArrivo() {
		return oraArrivo;
	}

	public void setOraArrivo(Time oraArrivo) {
		this.oraArrivo = oraArrivo;
	}

	public Float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}

	public Long getCodice() {
		return codice;
	}

	public String toString() {
		return "Volo [codice=" + codice + ", codiceAereo=" + codiceAereo + ", terminalOrigine=" + terminalOrigine
				+ ", terminalDestinazione=" + terminalDestinazione + ", dataPartenza=" + dataPartenza + ", oraPartenza="
				+ oraPartenza + ", dataArrivo=" + dataArrivo + ", oraArrivo=" + oraArrivo + ", prezzo=" + prezzo + "]";
	}

}
