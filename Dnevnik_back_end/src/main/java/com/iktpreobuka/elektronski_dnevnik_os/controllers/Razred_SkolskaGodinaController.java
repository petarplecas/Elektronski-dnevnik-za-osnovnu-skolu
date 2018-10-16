package com.iktpreobuka.elektronski_dnevnik_os.controllers;

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
import com.iktpreobuka.elektronski_dnevnik_os.entities.Razred_SkolskaGodinaEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.dto.Razred_SkolskaGodinaDto;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.Razred_SkolskaGodinaRepository;
import com.iktpreobuka.elektronski_dnevnik_os.services.Razred_SkolskaGodinaDao;

@RestController
@RequestMapping(path = "api/v1/admin/RazredSG")
public class Razred_SkolskaGodinaController {

	@Autowired
	public Razred_SkolskaGodinaRepository razred_SkolskaGodinaRepository;

	@Autowired
	public Razred_SkolskaGodinaDao razred_SkolskaGodinaDao;
	
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> getRazred_skolskaAll() {

		return new ResponseEntity<>(razred_SkolskaGodinaRepository.findAll(), HttpStatus.OK);
	}

	// Unos novog razreda i skolske godine
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> unosNovogRazreda_SkolskaGodina(@Valid @RequestBody Razred_SkolskaGodinaDto noviRazred,
			BindingResult result) {

		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		Razred_SkolskaGodinaEntity razred = new Razred_SkolskaGodinaEntity();
		razred.setRazred(razred_SkolskaGodinaDao.toRomanNumerals(noviRazred.getRazred()));
		razred.setSkolskaGodina(noviRazred.getSkolskaGodina());
		if (razred_SkolskaGodinaRepository.findByRazredAndSkolskaGodina(razred.getRazred(), razred.getSkolskaGodina()) != null) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Razred vec postoji"), HttpStatus.BAD_REQUEST);
		}
		razred_SkolskaGodinaRepository.save(razred);

		return new ResponseEntity<>(razred, HttpStatus.OK);
	}
	
	// Brisanje razreda
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/{id_razred}/", method = RequestMethod.DELETE)
	public ResponseEntity<?> brisanjeRazreda(@PathVariable(value = "id_razred") Integer id_razred) {

		if(razred_SkolskaGodinaRepository.findById(id_razred).isPresent() == false){
			return new ResponseEntity<RESTError>(new RESTError(2, "Id razreda ne postoji."),
					HttpStatus.NOT_FOUND);
		}
		
		Razred_SkolskaGodinaEntity razred = razred_SkolskaGodinaRepository.findById(id_razred).get();
		
		razred_SkolskaGodinaRepository.deleteById(id_razred);
		return new ResponseEntity<>(razred, HttpStatus.OK);
		
	}
	
	//get razred
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/{idRazred}/", method = RequestMethod.GET)
	public ResponseEntity<?> getRazred(@PathVariable Integer idRazred) {
		if (razred_SkolskaGodinaRepository.findById(idRazred).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Razred ne postoji u bazi."),
					HttpStatus.NOT_FOUND);
		}
		
		Razred_SkolskaGodinaEntity razred = razred_SkolskaGodinaRepository.findById(idRazred).get();
				
		return new ResponseEntity<>(razred, HttpStatus.OK);
	}
	
	// Izmena razreda
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/{idRazred}/", method = RequestMethod.PUT)
	public ResponseEntity<?> izmenaRazreda(@Valid @PathVariable Integer idRazred,
			@RequestBody Razred_SkolskaGodinaEntity noviRazred, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		// Provera da li postoji nastavnik u bazi
		if (razred_SkolskaGodinaRepository.findById(idRazred).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Razred ne postoji u bazi."),
					HttpStatus.NOT_FOUND);
		}
		Razred_SkolskaGodinaEntity razred = razred_SkolskaGodinaRepository.findById(idRazred).get();

		razred.setRazred(noviRazred.getRazred());
		razred.setSkolskaGodina(noviRazred.getSkolskaGodina());

		razred_SkolskaGodinaRepository.save(razred);
		return new ResponseEntity<>(razred, HttpStatus.OK);
	}

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
	}

}
