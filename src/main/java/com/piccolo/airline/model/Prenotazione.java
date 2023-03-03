package com.piccolo.airline.model;

import java.sql.Date;
import jakarta.persistence.*;

@Entity
@Table (name = "prenotazione")
public class Prenotazione {
	
	@Id
	@Column (name = "codice")
	private Long codice;
	
	@ManyToOne
	@JoinColumn(name = "codice_volo", nullable = false, referencedColumnName = "codice")
	private Volo codiceVolo;
	
	@ManyToOne
	@JoinColumn(name = "codice_cliente", nullable = false, referencedColumnName = "codice")
	private Cliente codiceCliente;
	
	@Column (name = "data", nullable = false)
	private Date data;

	public Prenotazione() {
		
	}

	public Prenotazione(Long codice, Volo codiceVolo, Cliente codiceCliente, Date data) {
		this.codice = codice;
		this.codiceVolo = codiceVolo;
		this.codiceCliente = codiceCliente;
		this.data = data;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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

	public Cliente getCodiceCliente() {
		return codiceCliente;
	}

	public void setCodiceCliente(Cliente codiceCliente) {
		this.codiceCliente = codiceCliente;
	}

	public String toString() {
		return "Prenotazione [codice=" + codice + ", codiceVolo=" + codiceVolo + ", codiceCliente=" + codiceCliente
				+ ", dataPrenotazione=" + data + "]";
	}

}
