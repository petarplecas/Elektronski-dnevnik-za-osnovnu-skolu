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
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "Razred")
@JsonPropertyOrder({"idRsg", "razred", "skolskaGodina"})
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Razred_SkolskaGodinaEntity {
	@Id
	@GeneratedValue
	private Integer idRsg;
	
	@NotNull(message = "Razred mora biti unesen.")
	// prikaz je String, a unos je preko dto klase gde je ovo polje definisano kao Integer(rimski brojevi)
	private String razred;
	
	@Column
	@NotNull(message = "Skolska godina mora biti unesena.")
	@Pattern(regexp = "^20([1-9]{2})\\/([0-9]{2})$", message = "Skolska godina mora da bude unesena u formatu \"2017/18\".")  
	private String skolskaGodina;
	
	@JsonBackReference(value = "o-rsk")
	@OneToMany(mappedBy = "razred", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<OdeljenjeEntity> odeljenje = new ArrayList<>();
	
	@JsonBackReference(value = "p-rsg")
	@OneToMany(mappedBy = "razred", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<PredmetEntity> predmet = new ArrayList<>();
	
	

	public Razred_SkolskaGodinaEntity(Integer idRsg, @NotNull(message = "Razred mora biti unesen.") String razred,
			@NotNull(message = "Skolska godina mora biti unesena.") @Pattern(regexp = "^20([1-9]{2})\\/([0-9]{2})$", message = "Skolska godina mora da bude unesena u formatu \"2017/18\".") String skolskaGodina) {
		super();
		this.idRsg = idRsg;
		this.razred = razred;
		this.skolskaGodina = skolskaGodina;
	}

	public Razred_SkolskaGodinaEntity() {
		super();
		// Auto-generated constructor stub
	}

	public Integer getIdRsg() {
		return idRsg;
	}

	public void setIdRsg(Integer idRsg) {
		this.idRsg = idRsg;
	}

	public String getRazred() {
		return razred;
	}

	public void setRazred(String razred) {
		this.razred = razred;
	}

	public String getSkolskaGodina() {
		return skolskaGodina;
	}

	public void setSkolskaGodina(String skolskaGodina) {
		this.skolskaGodina = skolskaGodina;
	}

	public List<OdeljenjeEntity> getOdeljenje() {
		return odeljenje;
	}

	public void setOdeljenje(List<OdeljenjeEntity> odeljenje) {
		this.odeljenje = odeljenje;
	}

	public List<PredmetEntity> getPredmet() {
		return predmet;
	}

	public void setPredmet(List<PredmetEntity> predmet) {
		this.predmet = predmet;
	}

}
