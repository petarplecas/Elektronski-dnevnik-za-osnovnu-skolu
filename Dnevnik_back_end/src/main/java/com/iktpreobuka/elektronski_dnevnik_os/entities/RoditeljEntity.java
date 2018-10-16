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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "Roditelji")
@JsonPropertyOrder({"idRoditelj", "ime", "prezime","email", "korisnickoIme", "lozinka", "uloga"})
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class RoditeljEntity {
	@Id
	@GeneratedValue
	private Integer idRoditelj;
	
	@Column
	@NotNull(message = "Ime mora biti uneseno.")
	@Size(min=2, max=30, message = "Ime mora biti izmedju {min} i {max} karaktera dugacko.")
	private String ime;
	
	@Column
	@NotNull(message = "Prezime mora biti uneseno.")
	@Size(min=2, max=30, message = "Prezime mora biti izmedju {min} i {max} karaktera dugacko.")
	private String prezime;
	
	@Column(unique = true)
	@NotNull(message = "Email mora biti unesen.")
	@Pattern(regexp= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String email;
	
	@Column(unique = true)
	@NotNull(message = "Korisnicko ime mora biti uneseno.")
	@Size(min=5, max=15, message = "Korisnicko ime mora biti izmedju {min} i {max} karaktera dugacko.")
	private String korisnickoIme;
	
	@Column
	@JsonBackReference
	@NotNull(message = "Lozinka mora biti unesena.")
	@Size(min=5, max=100, message = "Lozinka mora biti izmedju {min} i {max} karaktera dugacko.")
	private String lozinka;
	
	@JsonBackReference(value = "u-r")
	@OneToMany(mappedBy = "roditelj", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	private List<UcenikEntity> ucenik = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idUloga")
	private UlogaEntity uloga;
	
	

	public RoditeljEntity(Integer idRoditelj,
			@NotNull(message = "Ime mora biti uneseno.") @Size(min = 2, max = 30, message = "Ime mora biti izmedju {min} i {max} karaktera dugacko.") String ime,
			@NotNull(message = "Prezime mora biti uneseno.") @Size(min = 2, max = 30, message = "Prezime mora biti izmedju {min} i {max} karaktera dugacko.") String prezime,
			@NotNull(message = "Email mora biti unesen.") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") String email,
			@NotNull(message = "Korisnicko ime mora biti uneseno.") @Size(min = 5, max = 15, message = "Korisnicko ime mora biti izmedju {min} i {max} karaktera dugacko.") String korisnickoIme,
			@NotNull(message = "Lozinka mora biti unesena.") @Size(min = 5, max = 100, message = "Lozinka mora biti izmedju {min} i {max} karaktera dugacko.") String lozinka,
			UlogaEntity uloga) {
		super();
		this.idRoditelj = idRoditelj;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.uloga = uloga;
	}

	public RoditeljEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdRoditelj() {
		return idRoditelj;
	}

	public void setIdRoditelj(Integer idRoditelj) {
		this.idRoditelj = idRoditelj;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<UcenikEntity> getUcenik() {
		return ucenik;
	}

	public void setUcenik(List<UcenikEntity> ucenik) {
		this.ucenik = ucenik;
	}

	public UlogaEntity getUloga() {
		return uloga;
	}

	public void setUloga(UlogaEntity uloga) {
		this.uloga = uloga;
	}

	
}
