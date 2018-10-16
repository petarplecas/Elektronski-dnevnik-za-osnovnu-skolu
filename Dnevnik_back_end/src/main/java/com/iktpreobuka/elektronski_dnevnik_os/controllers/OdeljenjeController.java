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
import com.iktpreobuka.elektronski_dnevnik_os.entities.Nastavnik_Odeljenje_PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.Razred_SkolskaGodinaEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.Nastavnik_Odeljenje_PredmetRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.OdeljenjeRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.Razred_SkolskaGodinaRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.UcenikRepository;

@RestController
@RequestMapping(path = "api/v1/odeljenje")
public class OdeljenjeController {

	@Autowired
	public Razred_SkolskaGodinaRepository razredRepository;
	@Autowired
	public OdeljenjeRepository odeljenjeRepository;
	@Autowired
	public UcenikRepository ucenikRepository;
	@Autowired
	public Nastavnik_Odeljenje_PredmetRepository vezaRepository;

	// Unos novog odeljenja
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/razred/{idRazred}/", method = RequestMethod.POST)
	public ResponseEntity<?> unosNovogOdeljenja(@Valid @RequestBody OdeljenjeEntity novoOdeljenje,
			@PathVariable Integer idRazred, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		if (razredRepository.findById(idRazred).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Id razreda ne postoji."), HttpStatus.NOT_FOUND);
		}

		Razred_SkolskaGodinaEntity razred = razredRepository.findById(idRazred).get();
		OdeljenjeEntity odeljenje = new OdeljenjeEntity();

		odeljenje.setOdeljenje(novoOdeljenje.getOdeljenje());
		odeljenje.setRazred(razred);

		List<OdeljenjeEntity> odeljenja = (List<OdeljenjeEntity>) odeljenjeRepository.findAll();

		for (OdeljenjeEntity odeljenjeEntity : odeljenja) {
			if (odeljenjeEntity.getOdeljenje() == odeljenje.getOdeljenje()
					&& odeljenjeEntity.getRazred().getIdRsg() == odeljenje.getRazred().getIdRsg()) {
				return new ResponseEntity<RESTError>(new RESTError(1, "Odeljenje vec postoji."),
						HttpStatus.BAD_REQUEST);
			}
		}
		odeljenjeRepository.save(odeljenje);
		return new ResponseEntity<>(odeljenje, HttpStatus.OK);
	}

	// Dobavi odeljenja
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> getOdeljenja() {

		return new ResponseEntity<>(odeljenjeRepository.findAll(), HttpStatus.OK);
	}

	// Izmena odeljenja
	// brisanje odeljenja u kojem nema ucenika i koji ne postoji u veynoj tabeli
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/{idOdeljenja}/", method = RequestMethod.DELETE)
	public ResponseEntity<?> brisanjeOdeljenja(@PathVariable Integer idOdeljenja) {

		// Provera da li postoji odeljenje u bazi
		if (odeljenjeRepository.findById(idOdeljenja).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Odeljenje ne postoji u bazi."),
					HttpStatus.NOT_FOUND);
		}
		OdeljenjeEntity odeljenje = odeljenjeRepository.findById(idOdeljenja).get();

		// Provera da li postoje ucenici u odeljenju
		List<UcenikEntity> ucenici = (List<UcenikEntity>) ucenikRepository.findByOdeljenje_IdOdeljenje(idOdeljenja);
		if (!ucenici.isEmpty()) {
			return new ResponseEntity<RESTError>(
					new RESTError(2, "U odeljenju postoje učenici, nije dozvoljeno brisanje"), HttpStatus.FORBIDDEN);
		}

		// Provera da li postoji odeljenje u veznoj tabeli
		List<Nastavnik_Odeljenje_PredmetEntity> veza = (List<Nastavnik_Odeljenje_PredmetEntity>) vezaRepository.findByOdeljenje_IdOdeljenje(idOdeljenja);
		if (!veza.isEmpty()) {
			return new ResponseEntity<RESTError>(
					new RESTError(2, "U veznoj tabeli postoji odeljenje, nije dozvoljeno brisanje"), HttpStatus.FORBIDDEN);
		}

		odeljenjeRepository.deleteById(idOdeljenja);
		return new ResponseEntity<>(odeljenje, HttpStatus.OK);
	}

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
	}
}
