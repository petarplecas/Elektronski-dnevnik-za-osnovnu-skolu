package com.iktpreobuka.elektronski_dnevnik_os.controllers;

import java.security.Principal;
import java.util.ArrayList;
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
import com.iktpreobuka.elektronski_dnevnik_os.entities.RoditeljEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.UlogaEntity;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.PredmetRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.RoditeljRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.UcenikRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.UlogaRepository;
import com.iktpreobuka.elektronski_dnevnik_os.services.EncDao;
import com.iktpreobuka.elektronski_dnevnik_os.services.OcenaDao;


@RestController
@RequestMapping(path = "api/v1/roditelj")
public class RoditeljController {

	@Autowired
	public RoditeljRepository roditeljRepository;
	@Autowired
	public UlogaRepository ulogaRepository;
	@Autowired
	public UcenikRepository ucenikRepository;
	@Autowired
	public PredmetRepository predmetRepository;
	@Autowired
	public OcenaDao ocenaDao;
	@Autowired
	public EncDao encDao;

	

	// Unos novog roditelja ROLE_ADMIN
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> unosNovogRoditelja(@Valid @RequestBody RoditeljEntity noviRoditelj, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		
		UlogaEntity uloga = ulogaRepository.findByUloga("ROLE_RODITELJA");
		RoditeljEntity roditelj = new RoditeljEntity();

		roditelj.setIme(noviRoditelj.getIme());
		roditelj.setPrezime(noviRoditelj.getPrezime());
		roditelj.setEmail(noviRoditelj.getEmail());
		roditelj.setKorisnickoIme(noviRoditelj.getKorisnickoIme());
		roditelj.setLozinka(encDao.cryptPassword(noviRoditelj.getLozinka()));
		roditelj.setUloga(uloga);
		// provera da li postoji korisnicko ime
		if ((roditeljRepository.findByKorisnickoIme(noviRoditelj.getKorisnickoIme()) != null) && !roditeljRepository.findByKorisnickoIme(noviRoditelj.getKorisnickoIme()).equals(roditelj)) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Korisnicko ime vec postoji."),
					HttpStatus.BAD_REQUEST);
		}
		// provera da li postoji email
		if ((roditeljRepository.findByEmail(noviRoditelj.getEmail()) != null) && !roditeljRepository.findByEmail(noviRoditelj.getEmail()).equals(roditelj)) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Email je vec koristen."), HttpStatus.BAD_REQUEST);
		}

		roditeljRepository.save(roditelj);
		return new ResponseEntity<>(roditelj, HttpStatus.OK);
	}

	// Izmena roditelja
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/{idRoditelja}/", method = RequestMethod.PUT)
	public ResponseEntity<?> izmenaRoditelja(@Valid @PathVariable Integer idRoditelja,
			@RequestBody RoditeljEntity noviRoditelj, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		// provera da li postoji u bazi
		if (roditeljRepository.findById(idRoditelja).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Roditelj ne postoji u bazi."),
					HttpStatus.NOT_FOUND);
		}
		RoditeljEntity roditelj = roditeljRepository.findById(idRoditelja).get();

		roditelj.setIme(noviRoditelj.getIme());
		roditelj.setPrezime(noviRoditelj.getPrezime());
		roditelj.setEmail(noviRoditelj.getEmail());
		roditelj.setKorisnickoIme(noviRoditelj.getKorisnickoIme());
		// provera da li postoji korisnicko ime
		if ((roditeljRepository.findByKorisnickoIme(noviRoditelj.getKorisnickoIme()) != null) && !roditeljRepository.findByKorisnickoIme(noviRoditelj.getKorisnickoIme()).equals(roditelj)) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Korisnicko ime vec postoji."),
					HttpStatus.BAD_REQUEST);
		}
		// provera da li postoji email
		if ((roditeljRepository.findByEmail(noviRoditelj.getEmail()) != null) && !roditeljRepository.findByEmail(noviRoditelj.getEmail()).equals(roditelj)) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Email je vec koristen."), HttpStatus.BAD_REQUEST);
		}

		roditeljRepository.save(roditelj);
		return new ResponseEntity<>(roditelj, HttpStatus.OK);
	}

	// Brisanje roditelja
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/{idRoditelj}/", method = RequestMethod.DELETE)
	public ResponseEntity<?> brisanjeRoditelja(@PathVariable Integer idRoditelj) {
		// provera da li postoji roditelj u bazi
		if (roditeljRepository.findById(idRoditelj).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Roditelj ne postoji u bazi."),
					HttpStatus.NOT_FOUND);
		}
		RoditeljEntity roditelj = roditeljRepository.findById(idRoditelj).get();
		roditeljRepository.deleteById(idRoditelj);
		return new ResponseEntity<>(roditelj, HttpStatus.OK);
	}
	
	// Dobavi roditelje
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> getRoditelje() {
		List<RoditeljEntity> roditelji = (List<RoditeljEntity>) roditeljRepository.findAll();
		
		return new ResponseEntity<>(roditelji, HttpStatus.OK);
	}
	
	// Dobavi roditelja
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/{idRoditelj}/", method = RequestMethod.GET)
	public ResponseEntity<?> getRoditelj(@PathVariable Integer idRoditelj) {
		if(roditeljRepository.findById(idRoditelj).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Roditelj ne postoji u bazi."),
					HttpStatus.NOT_FOUND);
		}
		
		RoditeljEntity roditelj = roditeljRepository.findById(idRoditelj).get();
		
		return new ResponseEntity<>(roditelj, HttpStatus.OK);
	}
	
	//deca roditelja
	@Secured("ROLE_RODITELJA")
	@RequestMapping(value = "/deca/", method = RequestMethod.GET)
	public ResponseEntity<?> decaRoditelja(Principal principal) {
		String username = principal.getName();
		RoditeljEntity roditelj = roditeljRepository.findByKorisnickoIme(username);
		
		List<UcenikEntity> ucenici = new ArrayList<>();
		ucenici = ucenikRepository.findByRoditelj_IdRoditelj(roditelj.getIdRoditelj());
		if(ucenici.isEmpty() == true) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Roditelj nije dodeljen ni jednom uceniku"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ucenici, HttpStatus.OK);
	}
	// TODO Napraviti pregled ocena svog deteta po id, sa proverom da li pripada kao
		// roditelj tom uceniku
	
	@Secured("ROLE_RODITELJA")
	@RequestMapping(value = "ocene/{idPredmet}/", method = RequestMethod.GET)
	public ResponseEntity<?> pregledSvihOcenaPoPredmetu(@PathVariable Integer idPredmet,
			Principal principal) {

		String username = principal.getName();
		RoditeljEntity roditelj = roditeljRepository.findByKorisnickoIme(username);

		// Provera da li postoji predmet
		if (predmetRepository.findById(idPredmet).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Id predmeta ne postoji."), HttpStatus.NOT_FOUND);
		}
		// Provera da li ima ocena
		if (ocenaDao.vracanjeOcenaPoPredmetuIRoditelju(idPredmet, roditelj.getIdRoditelj()).isEmpty() == true) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Ucenik nema ocena iz tog predmeta."),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ocenaDao.vracanjeOcenaPoPredmetuIRoditelju(idPredmet, roditelj.getIdRoditelj()),
				HttpStatus.OK);
	}
	// TODO Napraviti pregled ocena svog deteta po idUcenika i idPredmte
	// pojedinacnog
	@Secured("ROLE_RODITELJA")
	@RequestMapping(value = "sve/ocene/dece", method = RequestMethod.GET)
	public ResponseEntity<?> pregledSvihOcenaDece(Principal principal) {

		String username = principal.getName();
		RoditeljEntity roditelj = roditeljRepository.findByKorisnickoIme(username);
		
		// Provera da li ima ocena
		if (ocenaDao.vracanjeOcenaDecePoRoditelju(roditelj.getIdRoditelj()).isEmpty() == true) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Deca roditelja nisu ocenjena"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ocenaDao.vracanjeOcenaDecePoRoditelju(roditelj.getIdRoditelj()),
				HttpStatus.OK);
	}
	
	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
	}
}
