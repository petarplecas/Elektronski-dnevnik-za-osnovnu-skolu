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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "Ucenici")
@JsonPropertyOrder({"idUcenik", "ime", "prezime", "korisnickoIme", "lozinka", "uloga", "idRoditelj"})
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UcenikEntity {
	@Id
	@GeneratedValue
	private Integer idUcenik;
	
	@Column
	@NotNull(message = "Ime mora biti uneseno.")
	@Size(min=2, max=30, message = "Ime mora biti izmedju {min} i {max} karaktera dugacko.")
	private String ime;
	
	@Column
	@NotNull(message = "Prezime mora biti uneseno.")
	@Size(min=2, max=30, message = "Prezime mora biti izmedju {min} i {max} karaktera dugacko.")
	private String prezime;
	
	@Column(unique = true)
	@NotNull(message = "Korisnicko ime mora biti uneseno.")
	@Size(min=5, max=15, message = "Korisnicko ime mora biti izmedju {min} i {max} karaktera dugacko.")
	private String korisnickoIme;
	
	@Column
	@NotNull(message = "Lozinka mora biti unesena.")
	@Size(min=5, max=100, message = "Lozinka mora biti izmedju {min} i {max} karaktera dugacko.")
	@JsonBackReference
	private String lozinka;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "idOdeljenje")
	private OdeljenjeEntity odeljenje;
	
	@JsonBackReference(value = "o-u")
	@OneToMany(mappedBy = "ucenik", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<OcenaEntity> ocena = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "idRoditelj")
	private RoditeljEntity roditelj;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idUloga")
	private UlogaEntity uloga;
	
	

	public UcenikEntity(Integer idUcenik,
			@NotNull(message = "Ime mora biti uneseno.") @Size(min = 2, max = 30, message = "Ime mora biti izmedju {min} i {max} karaktera dugacko.") String ime,
			@NotNull(message = "Prezime mora biti uneseno.") @Size(min = 2, max = 30, message = "Prezime mora biti izmedju {min} i {max} karaktera dugacko.") String prezime,
			@NotNull(message = "Korisnicko ime mora biti uneseno.") @Size(min = 5, max = 15, message = "Korisnicko ime mora biti izmedju {min} i {max} karaktera dugacko.") String korisnickoIme,
			@NotNull(message = "Lozinka mora biti unesena.") @Size(min = 5, max = 100, message = "Lozinka mora biti izmedju {min} i {max} karaktera dugacko.") String lozinka,
			OdeljenjeEntity odeljenje, RoditeljEntity roditelj, UlogaEntity uloga) {
		super();
		this.idUcenik = idUcenik;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.odeljenje = odeljenje;
		this.roditelj = roditelj;
		this.uloga = uloga;
	}

	public UcenikEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdUcenik() {
		return idUcenik;
	}

	public void setIdUcenik(Integer idUcenik) {
		this.idUcenik = idUcenik;
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

	public OdeljenjeEntity getOdeljenje() {
		return odeljenje;
	}

	public void setOdeljenje(OdeljenjeEntity odeljenje) {
		this.odeljenje = odeljenje;
	}

	public List<OcenaEntity> getOcena() {
		return ocena;
	}

	public void setOcena(List<OcenaEntity> ocena) {
		this.ocena = ocena;
	}

	public RoditeljEntity getRoditelj() {
		return roditelj;
	}

	public void setRoditelj(RoditeljEntity roditelj) {
		this.roditelj = roditelj;
	}

	public UlogaEntity getUloga() {
		return uloga;
	}

	public void setUloga(UlogaEntity uloga) {
		this.uloga = uloga;
	}

	
}
	

	