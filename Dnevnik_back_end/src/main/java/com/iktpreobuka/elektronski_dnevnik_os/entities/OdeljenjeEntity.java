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
@Table(name = "Odeljenje")
@JsonPropertyOrder({"idOdeljenje", "odeljenje", "idRazred"})
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class OdeljenjeEntity {

	@Id
	@GeneratedValue
	private Integer idOdeljenje;
	
	@Column
	@NotNull(message ="Odeljenje mora biti definisano") 
	@Min(value=1, message="Odeljenje moze biti u opsegu izmedju 1 i 15.")
	@Max(value=15, message="Odeljenje moze biti u opsegu izmedju 1 i 15.")
	private Integer odeljenje;

	@JsonBackReference(value = "nop-o")
	@OneToMany(mappedBy = "odeljenje", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<Nastavnik_Odeljenje_PredmetEntity> nastavnikOdeljenjePredmet = new ArrayList<>();

	@JsonBackReference(value = "u-o")
	@OneToMany(mappedBy = "odeljenje", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<UcenikEntity> ucenik = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "idRazred")
	private Razred_SkolskaGodinaEntity razred;
	
	

	public OdeljenjeEntity(Integer idOdeljenje,
			@NotNull(message = "Odeljenje mora biti definisano") @Min(value = 1, message = "Odeljenje moze biti u opsegu izmedju 1 i 15.") @Max(value = 15, message = "Odeljenje moze biti u opsegu izmedju 1 i 15.") Integer odeljenje,
			Razred_SkolskaGodinaEntity razred) {
		super();
		this.idOdeljenje = idOdeljenje;
		this.odeljenje = odeljenje;
		this.razred = razred;
	}

	public OdeljenjeEntity() {
		super();
		// Auto-generated constructor stub
	}

	public Integer getIdOdeljenje() {
		return idOdeljenje;
	}

	public void setIdOdeljenje(Integer idOdeljenje) {
		this.idOdeljenje = idOdeljenje;
	}

	public Integer getOdeljenje() {
		return odeljenje;
	}

	public void setOdeljenje(Integer odeljenje) {
		this.odeljenje = odeljenje;
	}

	public List<Nastavnik_Odeljenje_PredmetEntity> getNastavnikOdeljenjePredmet() {
		return nastavnikOdeljenjePredmet;
	}

	public void setNastavnikOdeljenjePredmet(List<Nastavnik_Odeljenje_PredmetEntity> nastavnikOdeljenjePredmet) {
		this.nastavnikOdeljenjePredmet = nastavnikOdeljenjePredmet;
	}

	public List<UcenikEntity> getUcenik() {
		return ucenik;
	}

	public void setUcenik(List<UcenikEntity> ucenik) {
		this.ucenik = ucenik;
	}

	public Razred_SkolskaGodinaEntity getRazred() {
		return razred;
	}

	public void setRazred(Razred_SkolskaGodinaEntity razred) {
		this.razred = razred;
	}

}
