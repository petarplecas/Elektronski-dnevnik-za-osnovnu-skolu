package com.iktpreobuka.elektronski_dnevnik_os.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "Admini")
@JsonPropertyOrder({ "idAdmin", "ime", "prezime", "korisnickoIme", "lozinka", "uloga" })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AdminEntity {
	
	@Id
	@GeneratedValue
	private Integer idAdmin;
	
	@Column
	@NotNull(message = "Ime mora biti uneseno.")
	@Size(min = 2, max = 30, message = "Ime mora biti izmedju {min} i {max} karaktera dugacko.")
	private String ime;
	
	@Column
	@NotNull(message = "Prezime mora biti uneseno.")
	@Size(min = 2, max = 30, message = "Prezime mora biti izmedju {min} i {max} karaktera dugacko.")
	private String prezime;
	
	@Column(unique = true)
	@NotNull(message = "Korisnicko ime mora biti uneseno.")
	@Size(min = 5, max = 30, message = "Korisnicko ime mora biti izmedju {min} i {max} karaktera dugacko.")	
	private String korisnickoIme;
	
	@Column
	@JsonBackReference
	@NotNull(message = "Lozinka mora biti unesena.")
	@Size(min = 5, max = 100, message = "Lozinka mora biti izmedju {min} i {max} karaktera dugacko.")
	private String lozinka;
	
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idUloga")
	private UlogaEntity uloga;

	
	
	public AdminEntity(Integer idAdmin,
			@NotNull(message = "Ime mora biti uneseno.") @Size(min = 2, max = 30, message = "Ime mora biti izmedju {min} i {max} karaktera dugacko.") String ime,
			@NotNull(message = "Prezime mora biti uneseno.") @Size(min = 2, max = 30, message = "Prezime mora biti izmedju {min} i {max} karaktera dugacko.") String prezime,
			@NotNull(message = "Korisnicko ime mora biti uneseno.") @Size(min = 5, max = 30, message = "Korisnicko ime mora biti izmedju {min} i {max} karaktera dugacko.") String korisnickoIme,
			@NotNull(message = "Lozinka mora biti unesena.") @Size(min = 5, max = 100, message = "Lozinka mora biti izmedju {min} i {max} karaktera dugacko.") String lozinka,
			UlogaEntity uloga) {
		super();
		this.idAdmin = idAdmin;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.uloga = uloga;
	}


	public AdminEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Integer getIdAdmin() {
		return idAdmin;
	}


	public void setIdAdmin(Integer idAdmin) {
		this.idAdmin = idAdmin;
	}


	public String getIme() {
		return ime;
	}


	public void setIme(String ime) {
		this.ime = ime;
	}


	public String getPrezime() {
		return prezime;
	}


	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}


	public String getKorisnickoIme() {
		return korisnickoIme;
	}


	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}


	public String getLozinka() {
		return lozinka;
	}


	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}


	public UlogaEntity getUloga() {
		return uloga;
	}


	public void setUloga(UlogaEntity uloga) {
		this.uloga = uloga;
	}


	
	
}
