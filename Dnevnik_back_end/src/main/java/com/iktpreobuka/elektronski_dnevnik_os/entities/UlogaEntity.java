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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "Uloga")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UlogaEntity {
	
	@Id
	@GeneratedValue
	private Integer idUloga;
	
	@Column
	@NotNull(message = "Uloga mora biti unesena")
	@Size(min=2, max=30, message = "Uloga mora biti izmedju {min} i {max} karaktera dugacko.")
	private String uloga;
	
	
	@OneToMany(mappedBy= "uloga", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH})
	@JsonBackReference(value = "ucenik-u")
	private List<UcenikEntity> ucenici = new ArrayList<>();
	
	
	@OneToMany(mappedBy= "uloga", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH})
	@JsonBackReference(value = "roditelj-u")
	private List<RoditeljEntity> roditelji = new ArrayList<>();
	
	
	@OneToMany(mappedBy= "uloga", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH})
	@JsonBackReference(value = "admin-u")
	private List<AdminEntity> admini = new ArrayList<>();
	
	
	@OneToMany(mappedBy= "uloga", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH})
	@JsonBackReference(value = "nastavnik-u")
	private List<NastavnikEntity> nastavnici = new ArrayList<>();

	

	public UlogaEntity(Integer idUloga,
			@NotNull(message = "Uloga mora biti unesena") @Size(min = 2, max = 30, message = "Uloga mora biti izmedju {min} i {max} karaktera dugacko.") String uloga) {
		super();
		this.idUloga = idUloga;
		this.uloga = uloga;
	}


	public UlogaEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Integer getIdUloga() {
		return idUloga;
	}


	public void setIdUloga(Integer idUloga) {
		this.idUloga = idUloga;
	}


	public String getUloga() {
		return uloga;
	}


	public void setUloga(String uloga) {
		this.uloga = uloga;
	}


	public List<UcenikEntity> getUcenici() {
		return ucenici;
	}


	public void setUcenici(List<UcenikEntity> ucenici) {
		this.ucenici = ucenici;
	}


	public List<RoditeljEntity> getRoditelji() {
		return roditelji;
	}


	public void setRoditelji(List<RoditeljEntity> roditelji) {
		this.roditelji = roditelji;
	}


	public List<AdminEntity> getAdmini() {
		return admini;
	}


	public void setAdmini(List<AdminEntity> admini) {
		this.admini = admini;
	}


	public List<NastavnikEntity> getNastavnici() {
		return nastavnici;
	}


	public void setNastavnici(List<NastavnikEntity> nastavnici) {
		this.nastavnici = nastavnici;
	}

	
	
}
