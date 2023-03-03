package com.piccolo.airline.model;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.*;

@Entity
@Table (name = "scalo")
public class Scalo {
	
	@Id
	@Column (name = "codice")
	private Long codice;
	
	@ManyToOne
	@JoinColumn(name = "codice_volo", nullable = false, referencedColumnName = "codice")
	private Volo codiceVolo;
	
	@ManyToOne
	@JoinColumn(name = "codice_aeroporto", nullable = false, referencedColumnName = "codice")
	private Aeroporto codiceAeroporto;
	
	@Column (name = "data", nullable = false)
	private Date dataScalo;
	
	@Column (name = "ora", nullable = false)
	private Time oraScalo;
	
	public Scalo() {
		
	}

	public Scalo(Long codice, Volo codiceVolo, Aeroporto codiceAeroporto, Date dataScalo, Time oraScalo) {
		this.codice = codice;
		this.codiceVolo = codiceVolo;
		this.codiceAeroporto = codiceAeroporto;
		this.dataScalo = dataScalo;
		this.oraScalo = oraScalo;
	}

	public void setCodice(Long codice) {
		this.codice = codice;
	}

	public Long getCodice() {
		return codice;
	}

	public Volo getCodiceVolo() {
		return codiceVolo;
	}

	public void setCodiceVolo(Volo codiceVolo) {
		this.codiceVolo = codiceVolo;
	}

	public Aeroporto getCodiceAeroporto() {
		return codiceAeroporto;
	}

	public void setCodiceAeroporto(Aeroporto codiceAeroporto) {
		this.codiceAeroporto = codiceAeroporto;
	}

	public Date getDataScalo() {
		return dataScalo;
	}

	public void setDataScalo(Date dataScalo) {
		this.dataScalo = dataScalo;
	}

	public Time getOraScalo() {
		return oraScalo;
	}

	public void setOraScalo(Time oraScalo) {
		this.oraScalo = oraScalo;
	}

	public String toString() {
		return "Scalo [codice=" + codice + ", codiceVolo=" + codiceVolo + ", codiceAeroporto=" + codiceAeroporto
				+ ", dataScalo=" + dataScalo + ", oraScalo=" + oraScalo + "]";
	}

}
