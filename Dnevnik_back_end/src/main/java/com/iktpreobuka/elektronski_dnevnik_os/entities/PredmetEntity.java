package com.iktpreobuka.elektronski_dnevnik_os.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "Predmeti")
@JsonPropertyOrder({"idPredmet", "nazivPredmeta", "nedeljniFondCasova", "idRazred"})
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PredmetEntity {
	@Id
	@GeneratedValue
	private Integer idPredmet;
	
	@Column
	@NotNull(message ="Nedeljni fond casova mora biti unesen.") 
	@Min(value = 1, message = "Nedeljni fond casova moze biti izmedju 1 i 10.")
	@Max(value = 10, message = "Nedeljni fond casova moze biti izmedju 1 i 10.")
	private Integer nedeljniFondCasova;

	@JsonBackReference(value = "nop-p")
	@OneToMany(mappedBy = "predmet", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<Nastavnik_Odeljenje_PredmetEntity> nastavnikOdeljenjePredmet = new ArrayList<>();

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "idRsg")
	private Razred_SkolskaGodinaEntity razred;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idNazivPredmet")
	private NazivPredmetaEntity nazivPredmeta;
	
	

	public PredmetEntity(Integer idPredmet,
			@NotNull(message = "Nedeljni fond casova mora biti unesen.") @Min(value = 1, message = "Nedeljni fond casova moze biti izmedju 1 i 10.") @Max(value = 10, message = "Nedeljni fond casova moze biti izmedju 1 i 10.") Integer nedeljniFondCasova,
			Razred_SkolskaGodinaEntity razred, NazivPredmetaEntity nazivPredmeta) {
		super();
		this.idPredmet = idPredmet;
		this.nedeljniFondCasova = nedeljniFondCasova;
		this.razred = razred;
		this.nazivPredmeta = nazivPredmeta;
	}

	public PredmetEntity() {
		super();
		// Auto-generated constructor stub
	}

	public Integer getIdPredmet() {
		return idPredmet;
	}

	public void setIdPredmet(Integer idPredmet) {
		this.idPredmet = idPredmet;
	}

	public Integer getNedeljniFondCasova() {
		return nedeljniFondCasova;
	}

	public void setNedeljniFondCasova(Integer nedeljniFondCasova) {
		this.nedeljniFondCasova = nedeljniFondCasova;
	}

	public List<Nastavnik_Odeljenje_PredmetEntity> getNastavnikOdeljenjePredmet() {
		return nastavnikOdeljenjePredmet;
	}

	public void setNastavnikOdeljenjePredmet(List<Nastavnik_Odeljenje_PredmetEntity> nastavnikOdeljenjePredmet) {
		this.nastavnikOdeljenjePredmet = nastavnikOdeljenjePredmet;
	}

	public Razred_SkolskaGodinaEntity getRazred() {
		return razred;
	}

	public void setRazred(Razred_SkolskaGodinaEntity razred) {
		this.razred = razred;
	}

	public NazivPredmetaEntity getNazivPredmeta() {
		return nazivPredmeta;
	}

	public void setNazivPredmeta(NazivPredmetaEntity nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}

}
