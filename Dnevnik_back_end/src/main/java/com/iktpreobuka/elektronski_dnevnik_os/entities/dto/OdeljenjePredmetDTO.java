package com.iktpreobuka.elektronski_dnevnik_os.entities.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.iktpreobuka.elektronski_dnevnik_os.entities.Razred_SkolskaGodinaEntity;

public class OdeljenjePredmetDTO {

	@Id
	@GeneratedValue
	private Integer idOdeljenje;
	
	@Column
	@NotNull(message ="Odeljenje mora biti definisano") 
	@Min(value=1, message="Odeljenje moze biti u opsegu izmedju 1 i 15.")
	@Max(value=15, message="Odeljenje moze biti u opsegu izmedju 1 i 15.")
	private Integer odeljenje;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "idRazred")
	private Razred_SkolskaGodinaEntity skolskaGodina;
	
	@NotNull(message ="Razred mora biti definisan") 
	private String razred;
	
	@NotNull(message ="Naziv predmeta mora biti definisan") 
	private String nazivPredmeta;

	public OdeljenjePredmetDTO(Integer idOdeljenje,
			@NotNull(message = "Odeljenje mora biti definisano") @Min(value = 1, message = "Odeljenje moze biti u opsegu izmedju 1 i 15.") @Max(value = 15, message = "Odeljenje moze biti u opsegu izmedju 1 i 15.") Integer odeljenje,
			Razred_SkolskaGodinaEntity skolskaGodina, @NotNull(message = "Razred mora biti definisan") String razred,
			@NotNull(message = "Naziv predmeta mora biti definisan") String nazivPredmeta) {
		super();
		this.idOdeljenje = idOdeljenje;
		this.odeljenje = odeljenje;
		this.skolskaGodina = skolskaGodina;
		this.razred = razred;
		this.nazivPredmeta = nazivPredmeta;
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

	public Razred_SkolskaGodinaEntity getSkolskaGodina() {
		return skolskaGodina;
	}

	public void setSkolskaGodina(Razred_SkolskaGodinaEntity skolskaGodina) {
		this.skolskaGodina = skolskaGodina;
	}

	public String getRazred() {
		return razred;
	}

	public void setRazred(String razred) {
		this.razred = razred;
	}

	public String getNazivPredmeta() {
		return nazivPredmeta;
	}

	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}

	
	
}
