package com.iktpreobuka.elektronski_dnevnik_os.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronski_dnevnik_os.controllers.util.RESTError;
import com.iktpreobuka.elektronski_dnevnik_os.entities.NastavnikEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.Nastavnik_Odeljenje_PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.NastavnikRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.Nastavnik_Odeljenje_PredmetRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.OdeljenjeRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.PredmetRepository;

@RestController
@RequestMapping(path = "api/v1/vezna")
public class Nastavnik_Odeljenje_PredmetController {

	@Autowired
	public NastavnikRepository nastavnikRepository;
	@Autowired
	public OdeljenjeRepository odeljenjeRepository;
	@Autowired
	public PredmetRepository predmetRepository;
	@Autowired
	public Nastavnik_Odeljenje_PredmetRepository nastavnikOdeljenjePredmetRepository;

	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/nastavnik/{idNastavnik}/odeljenje/{idOdeljenje}/predmet/{idPredmet}/", method = RequestMethod.POST)
	public ResponseEntity<?> unosNastavnikOdeljenjePredmet(@Valid @PathVariable Integer idNastavnik,
			@PathVariable Integer idOdeljenje, @PathVariable Integer idPredmet,
			@RequestBody Nastavnik_Odeljenje_PredmetEntity noviNOP, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}

		if (nastavnikRepository.findById(idNastavnik).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Id nastavnika ne postoji."), HttpStatus.NOT_FOUND);
		}
		if (odeljenjeRepository.findById(idOdeljenje).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Id odeljenja ne postoji."), HttpStatus.NOT_FOUND);
		}
		if (predmetRepository.findById(idPredmet).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Id predmeta ne postoji."), HttpStatus.NOT_FOUND);
		}

		NastavnikEntity nastavnik = nastavnikRepository.findById(idNastavnik).get();
		OdeljenjeEntity odeljenje = odeljenjeRepository.findById(idOdeljenje).get();
		PredmetEntity predmet = predmetRepository.findById(idPredmet).get();

		noviNOP.setNastavnik(nastavnik);
		noviNOP.setOdeljenje(odeljenje);
		noviNOP.setPredmet(predmet);

		if (nastavnikOdeljenjePredmetRepository.findByNastavnik_IdNastavnikAndOdeljenje_IdOdeljenjeAndPredmet_IdPredmet(
				noviNOP.getNastavnik().getIdNastavnik(), noviNOP.getOdeljenje().getIdOdeljenje(),
				noviNOP.getPredmet().getIdPredmet()) != null) {
			return new ResponseEntity<RESTError>(
					new RESTError(3, "Taj nastavnik vec predaje taj predmet tom odeljenju."), HttpStatus.BAD_REQUEST);
		}
		if (!odeljenje.getRazred().getIdRsg().equals(predmet.getRazred().getIdRsg())) {
			return new ResponseEntity<RESTError>(
					new RESTError(3, "Predmet ne moze da se predaje odeljenju, jer pripadaju razlicitim uzrastima."),
					HttpStatus.BAD_REQUEST);
		}
		nastavnikOdeljenjePredmetRepository.save(noviNOP);
		return new ResponseEntity<>(noviNOP, HttpStatus.OK);
	}

	// vracanje svih veza u tabeli
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> getVeze() {
		return new ResponseEntity<>(nastavnikOdeljenjePredmetRepository.findAll(), HttpStatus.OK);

	}

	// Vracanje jedne veze u tabeli
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/{idVeze}", method = RequestMethod.GET)
	public ResponseEntity<?> getVeza(@PathVariable Integer idVeze) {
		if (nastavnikOdeljenjePredmetRepository.findById(idVeze).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Ne postoji taj id veze."), HttpStatus.NOT_FOUND);
		}
		Nastavnik_Odeljenje_PredmetEntity veza = nastavnikOdeljenjePredmetRepository.findById(idVeze).get();
		return new ResponseEntity<>(veza, HttpStatus.OK);

	}

	// Brisanje veze u tabeli
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/{idVeze}/", method = RequestMethod.DELETE)
	public ResponseEntity<?> brisanjeVeze(@PathVariable Integer idVeze) {
		if (nastavnikOdeljenjePredmetRepository.findById(idVeze).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Ne postoji taj id veze."), HttpStatus.NOT_FOUND);
		}
		Nastavnik_Odeljenje_PredmetEntity veza = nastavnikOdeljenjePredmetRepository.findById(idVeze).get();
		nastavnikOdeljenjePredmetRepository.deleteById(idVeze);
		return new ResponseEntity<>(veza, HttpStatus.OK);

	}

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
	}
}
