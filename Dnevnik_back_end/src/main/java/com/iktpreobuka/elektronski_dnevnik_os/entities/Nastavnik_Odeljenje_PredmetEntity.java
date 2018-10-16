package com.iktpreobuka.elektronski_dnevnik_os.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "NasOdePre")
@JsonPropertyOrder({"idNop", "idNastavnik", "idOdeljenje", "idPredmet"})
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Nastavnik_Odeljenje_PredmetEntity {
	
	@Id
	@GeneratedValue
	private Integer idNop;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idNastavnik")
	@NotNull(message = "Nastavnik mora biti unesen.")
	private NastavnikEntity nastavnik;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idOdeljenje")
	@NotNull(message = "Odeljenje mora biti uneseno.")
	private OdeljenjeEntity odeljenje;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idPredmet")
	@NotNull(message = "Predmet mora biti unesen.")
	private PredmetEntity predmet;

	@JsonBackReference(value = "o-nop")
	@OneToMany(mappedBy = "nastavnikOdeljenjePredmet", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<OcenaEntity> ocena = new ArrayList<>();
	
	

	public Nastavnik_Odeljenje_PredmetEntity(Integer idNop,
			@NotNull(message = "Nastavnik mora biti unesen.") NastavnikEntity nastavnik,
			@NotNull(message = "Odeljenje mora biti uneseno.") OdeljenjeEntity odeljenje,
			@NotNull(message = "Predmet mora biti unesen.") PredmetEntity predmet) {
		super();
		this.idNop = idNop;
		this.nastavnik = nastavnik;
		this.odeljenje = odeljenje;
		this.predmet = predmet;
	}

	public Nastavnik_Odeljenje_PredmetEntity() {
		super();
		// Auto-generated constructor stub
	}

	public Integer getIdNop() {
		return idNop;
	}

	public void setIdNop(Integer idNop) {
		this.idNop = idNop;
	}

	public NastavnikEntity getNastavnik() {
		return nastavnik;
	}

	public void setNastavnik(NastavnikEntity nastavnik) {
		this.nastavnik = nastavnik;
	}

	public OdeljenjeEntity getOdeljenje() {
		return odeljenje;
	}

	public void setOdeljenje(OdeljenjeEntity odeljenje) {
		this.odeljenje = odeljenje;
	}

	public PredmetEntity getPredmet() {
		return predmet;
	}

	public void setPredmet(PredmetEntity predmet) {
		this.predmet = predmet;
	}

	public List<OcenaEntity> getOcena() {
		return ocena;
	}

	public void setOcena(List<OcenaEntity> ocena) {
		this.ocena = ocena;
	}

}
