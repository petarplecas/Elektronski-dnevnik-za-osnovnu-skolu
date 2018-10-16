package com.iktpreobuka.elektronski_dnevnik_os.controllers;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.iktpreobuka.elektronski_dnevnik_os.entities.NazivPredmetaEntity;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.NazivPredmetaRepository;

@RestController
@RequestMapping(path = "api/v1/nazivpredmeta")
public class NazivPredmetaContoller {
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	@Autowired
	public NazivPredmetaRepository nazivPredmetaRepository;
	
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> getPredmetNazive() {

		return new ResponseEntity<>(nazivPredmetaRepository.findAll(), HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/{idNaziv}/", method = RequestMethod.GET)
	public ResponseEntity<?> getNazivPredmeta(@PathVariable Integer idNaziv) {
		if (nazivPredmetaRepository.findById(idNaziv).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Naziv predmeta ne postoji u bazi."),
					HttpStatus.NOT_FOUND);
		}
		NazivPredmetaEntity naziv = nazivPredmetaRepository.findById(idNaziv).get();
		return new ResponseEntity<>(naziv, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> unosNovogNazivaPredmeta(@Valid @RequestBody NazivPredmetaEntity noviNaziv,
			BindingResult result) {
		
		logger.debug("This is a debug message");
		logger.info("This is an info message");
		logger.warn("This is a warn message");
		logger.error("This is an error message");
		
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		if (nazivPredmetaRepository.findByNazivPredmetaIgnoreCase(noviNaziv.getNazivPredmeta()) != null) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Predmet vec postoji."), HttpStatus.BAD_REQUEST);
		}
		nazivPredmetaRepository.save(noviNaziv);
		return new ResponseEntity<>(noviNaziv, HttpStatus.OK);
	}

	// Izmeni naziv predmeta
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/{idNaziv}/", method = RequestMethod.PUT)
	public ResponseEntity<?> izmenaNazivaPredmeta(@Valid @PathVariable Integer idNaziv,
			@RequestBody NazivPredmetaEntity noviNaziv, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		// Provera da li postoji id naziva u bazi
		if (nazivPredmetaRepository.findById(idNaziv).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Naziv predmeta ne postoji u bazi"),
					HttpStatus.NOT_FOUND);
		}
		NazivPredmetaEntity naziv = nazivPredmetaRepository.findById(idNaziv).get();
		naziv.setNazivPredmeta(noviNaziv.getNazivPredmeta());
		nazivPredmetaRepository.save(naziv);
		return new ResponseEntity<>(naziv, HttpStatus.OK);
	}

	// Brisanje naziva predmeta
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/{idNaziv}/", method = RequestMethod.DELETE)
	public ResponseEntity<?> brisanjeNazivaPredmeta(@PathVariable Integer idNaziv) {
		// Provera da li postoji id naziva u bazi
		if (nazivPredmetaRepository.findById(idNaziv).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Naziv predmeta ne postoji u bazi"),
					HttpStatus.NOT_FOUND);
		}
		NazivPredmetaEntity naziv = nazivPredmetaRepository.findById(idNaziv).get();
		nazivPredmetaRepository.deleteById(idNaziv);
		return new ResponseEntity<>(naziv, HttpStatus.OK);
	}

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
	}
}
