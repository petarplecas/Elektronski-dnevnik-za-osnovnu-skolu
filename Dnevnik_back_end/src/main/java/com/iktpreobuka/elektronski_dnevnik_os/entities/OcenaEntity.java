package com.iktpreobuka.elektronski_dnevnik_os.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.iktpreobuka.elektronski_dnevnik_os.entities.enumerations.ETipOcene;

@Entity
@Table(name = "Ocene")
@JsonPropertyOrder({ "idOcena", "visinaOcene", "tipOcene", "datum", "opisOcene", "nastavnik", "predmet", "idUcenik", "idNop" })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class OcenaEntity {
	@Id
	@GeneratedValue
	private Integer idOcena;

	@Column
	@NotNull(message = "Ocena mora biti unesena.")
	@Min(value = 1, message = "Ocena mora biti izmedju 1 i 5.")
	@Max(value = 5, message = "Ocena mora biti izmedju 1 i 5.")
	private Integer visinaOcene;

	@Column
	@NotNull(message = "Tip ocene mora da se unese.")
	private ETipOcene tipOcene;

	@Column
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	@NotNull(message = "Datum kada je ocena unesena mora da bude postavljen.")
	private Date datum;

	@Column
	@Size(min=2, max=100, message = "Opis mora biti izmedju {min} i {max} karaktera dugacko.")
	private String opisOcene;
	
	@Column
	@NotNull(message = "Nastavnik koji je dodelio icenu mora da se unese.")
	@Size(min=2, max=30, message = "Nastavnik mora biti izmedju {min} i {max} karaktera dugacko.")
	private String nastavnik;
	
	@Column
	@NotNull(message = "Predmet iz kog je data ocena mora da se unese.")
	@Size(min=2, max=30, message = "Predmet mora biti izmedju {min} i {max} karaktera dugacko.")
	private String predmet;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idUcenik")
	private UcenikEntity ucenik;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "idNop")
	private Nastavnik_Odeljenje_PredmetEntity nastavnikOdeljenjePredmet;
	
	

	

	public OcenaEntity(Integer idOcena,
			@NotNull(message = "Ocena mora biti unesena.") @Min(value = 1, message = "Ocena mora biti izmedju 1 i 5.") @Max(value = 5, message = "Ocena mora biti izmedju 1 i 5.") Integer visinaOcene,
			@NotNull(message = "Tip ocene mora da se unese.") ETipOcene tipOcene,
			@NotNull(message = "Datum kada je ocena unesena mora da bude postavljen.") Date datum,
			@Size(min = 2, max = 100, message = "Opis mora biti izmedju {min} i {max} karaktera dugacko.") String opisOcene,
			@NotNull(message = "Nastavnik koji je dodelio icenu mora da se unese.") @Size(min = 2, max = 30, message = "Nastavnik mora biti izmedju {min} i {max} karaktera dugacko.") String nastavnik,
			@NotNull(message = "Predmet iz kog je data ocena mora da se unese.") @Size(min = 2, max = 30, message = "Predmet mora biti izmedju {min} i {max} karaktera dugacko.") String predmet,
			UcenikEntity ucenik, Nastavnik_Odeljenje_PredmetEntity nastavnikOdeljenjePredmet) {
		super();
		this.idOcena = idOcena;
		this.visinaOcene = visinaOcene;
		this.tipOcene = tipOcene;
		this.datum = datum;
		this.opisOcene = opisOcene;
		this.nastavnik = nastavnik;
		this.predmet = predmet;
		this.ucenik = ucenik;
		this.nastavnikOdeljenjePredmet = nastavnikOdeljenjePredmet;
	}

	public OcenaEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdOcena() {
		return idOcena;
	}

	public void setIdOcena(Integer idOcena) {
		this.idOcena = idOcena;
	}

	public Integer getVisinaOcene() {
		return visinaOcene;
	}

	public void setVisinaOcene(Integer visinaOcene) {
		this.visinaOcene = visinaOcene;
	}

	public ETipOcene getTipOcene() {
		return tipOcene;
	}

	public void setTipOcene(ETipOcene tipOcene) {
		this.tipOcene = tipOcene;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getOpisOcene() {
		return opisOcene;
	}

	public void setOpisOcene(String opisOcene) {
		this.opisOcene = opisOcene;
	}

	public String getNastavnik() {
		return nastavnik;
	}

	public void setNastavnik(String nastavnik) {
		this.nastavnik = nastavnik;
	}

	public String getPredmet() {
		return predmet;
	}

	public void setPredmet(String predmet) {
		this.predmet = predmet;
	}

	public UcenikEntity getUcenik() {
		return ucenik;
	}

	public void setUcenik(UcenikEntity ucenik) {
		this.ucenik = ucenik;
	}

	public Nastavnik_Odeljenje_PredmetEntity getNastavnikOdeljenjePredmet() {
		return nastavnikOdeljenjePredmet;
	}

	public void setNastavnikOdeljenjePredmet(Nastavnik_Odeljenje_PredmetEntity nastavnikOdeljenjePredmet) {
		this.nastavnikOdeljenjePredmet = nastavnikOdeljenjePredmet;
	}
	
}
