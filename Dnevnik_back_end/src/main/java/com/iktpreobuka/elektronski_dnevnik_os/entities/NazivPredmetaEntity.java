package com.iktpreobuka.elektronski_dnevnik_os.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "Naziv_Predmeta")
@JsonPropertyOrder({"idPredmet", "nazivPredmeta"})
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class NazivPredmetaEntity {
	
	@Id
	@GeneratedValue
	private Integer idNazivPredmet;
	
	@Column
	@NotNull(message = "Naziv predmeta mora biti unesen.")
	private String nazivPredmeta;
	
	@JsonBackReference(value = "p-np")
	@OneToMany(mappedBy = "nazivPredmeta", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<PredmetEntity> predmet = new ArrayList<>();
	
	

	public NazivPredmetaEntity(Integer idNazivPredmet,
			@NotNull(message = "Naziv predmeta mora biti unesen.") String nazivPredmeta) {
		super();
		this.idNazivPredmet = idNazivPredmet;
		this.nazivPredmeta = nazivPredmeta;
	}

	public NazivPredmetaEntity() {
		super();
		// Auto-generated constructor stub
	}

	public Integer getIdNazivPredmet() {
		return idNazivPredmet;
	}

	public void setIdNazivPredmet(Integer idNazivPredmet) {
		this.idNazivPredmet = idNazivPredmet;
	}

	public String getNazivPredmeta() {
		return nazivPredmeta;
	}

	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}

	public List<PredmetEntity> getPredmet() {
		return predmet;
	}

	public void setPredmet(List<PredmetEntity> predmet) {
		this.predmet = predmet;
	}

}
