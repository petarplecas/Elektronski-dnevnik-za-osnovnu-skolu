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
import com.iktpreobuka.elektronski_dnevnik_os.entities.NastavnikEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.Nastavnik_Odeljenje_PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.OcenaEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.UlogaEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.dto.OdeljenjePredmetDTO;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.AdminRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.NastavnikRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.Nastavnik_Odeljenje_PredmetRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.OcenaRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.OdeljenjeRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.PredmetRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.UcenikRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.UlogaRepository;
import com.iktpreobuka.elektronski_dnevnik_os.services.EncDao;
import com.iktpreobuka.elektronski_dnevnik_os.services.NastavnikDao;
import com.iktpreobuka.elektronski_dnevnik_os.services.OcenaDao;
import com.iktpreobuka.elektronski_dnevnik_os.services.Razred_SkolskaGodinaDao;

@RestController
@RequestMapping(path = "api/v1/nastavnik")
public class NastavnikController {

	@Autowired
	public NastavnikRepository nastavnikRepository;
	@Autowired
	public AdminRepository adminRepository;
	@Autowired
	public UlogaRepository ulogaRepository;
	@Autowired
	public PredmetRepository predmetRepository;
	@Autowired
	public UcenikRepository ucenikRepository;
	@Autowired
	public OdeljenjeRepository odeljenjeRepository;
	@Autowired
	public OcenaRepository ocenaRepository;
	@Autowired
	public Nastavnik_Odeljenje_PredmetRepository grupniRepository;
	@Autowired
	public NastavnikDao nastavnikDao;
	@Autowired
	public OcenaDao ocenaDao;
	@Autowired
	public EncDao encDao;
	@Autowired
	public Razred_SkolskaGodinaDao razredSkolskaGodinaDao;
	
	
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> getAllNastavnika() {
		List<NastavnikEntity> nastavnici = (List<NastavnikEntity>) nastavnikRepository.findAll();
		return new ResponseEntity<>(nastavnici, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/{idNastavnik}", method = RequestMethod.GET)
	public ResponseEntity<?> getNastavnika(@PathVariable Integer idNastavnik) {
		if (nastavnikRepository.findById(idNastavnik).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Nastavnik ne postoji u bazi."),
					HttpStatus.NOT_FOUND);
		}
		NastavnikEntity nastavnik = nastavnikRepository.findById(idNastavnik).get();
		return new ResponseEntity<>(nastavnik, HttpStatus.OK);
	}	

	// Unos novog nastavnika
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> unosNovogNastavnika(@Valid @RequestBody NastavnikEntity noviNastavnik,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		UlogaEntity uloga = ulogaRepository.findByUloga("ROLE_NASTAVNIKA");
		NastavnikEntity nastavnik = new NastavnikEntity();

		nastavnik.setIme(noviNastavnik.getIme());
		nastavnik.setPrezime(noviNastavnik.getPrezime());
		nastavnik.setKorisnickoIme(noviNastavnik.getKorisnickoIme());
		nastavnik.setLozinka(encDao.cryptPassword(noviNastavnik.getLozinka()));
		nastavnik.setUloga(uloga);

		if ((nastavnikRepository.findByKorisnickoIme(noviNastavnik.getKorisnickoIme()) != null) && !nastavnikRepository.findByKorisnickoIme(noviNastavnik.getKorisnickoIme()).equals(nastavnik)) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Korisnicko ime vec postoji."),
					HttpStatus.BAD_REQUEST);
		}

		nastavnikRepository.save(nastavnik);
		return new ResponseEntity<>(nastavnik, HttpStatus.OK);
	}

	// Izmena nastavnika
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/{idNastavnik}/", method = RequestMethod.PUT)
	public ResponseEntity<?> izmenaNastavnika(@Valid @PathVariable Integer idNastavnik,
			@RequestBody NastavnikEntity noviNastavnik, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		// Provera da li postoji nastavnik u bazi
		if (nastavnikRepository.findById(idNastavnik).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Nastavnik ne postoji u bazi."),
					HttpStatus.NOT_FOUND);
		}
		NastavnikEntity nastavnik = nastavnikRepository.findById(idNastavnik).get();

		nastavnik.setIme(noviNastavnik.getIme());
		nastavnik.setPrezime(noviNastavnik.getPrezime());
		nastavnik.setKorisnickoIme(noviNastavnik.getKorisnickoIme());

		if ((nastavnikRepository.findByKorisnickoIme(noviNastavnik.getKorisnickoIme()) != null) && !nastavnikRepository.findByKorisnickoIme(noviNastavnik.getKorisnickoIme()).equals(nastavnik)) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Korisnicko ime vec postoji."),
					HttpStatus.BAD_REQUEST);
		}

		nastavnikRepository.save(nastavnik);
		return new ResponseEntity<>(nastavnik, HttpStatus.OK);
	}

	// brisanje nastavnika
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/{idNastavnik}/", method = RequestMethod.DELETE)
	public ResponseEntity<?> brisanjeNastavnika(@PathVariable Integer idNastavnik) {
		// Provera da li postoji nastavnik u bazi
		if (nastavnikRepository.findById(idNastavnik).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Nastavnik ne postoji u bazi."),
					HttpStatus.NOT_FOUND);
		}
		NastavnikEntity nastavnik = nastavnikRepository.findById(idNastavnik).get();
		
		// Brisanje ocena koje je nastavnik dodelio
		List<Nastavnik_Odeljenje_PredmetEntity> kombinacije = (List<Nastavnik_Odeljenje_PredmetEntity>) grupniRepository.findAll();
		List<OcenaEntity> ocene = (List<OcenaEntity>) ocenaRepository.findAll();
		
		for(Nastavnik_Odeljenje_PredmetEntity kombinacija: kombinacije) {
			if(kombinacija.getNastavnik().getIdNastavnik().equals(idNastavnik) == true) {
				grupniRepository.delete(kombinacija);
			}
		}
		
		for(OcenaEntity ocena: ocene) {
			if(ocena.getNastavnik().equals(nastavnik.getIme() + " " + nastavnik.getPrezime()) == true) {
				ocenaRepository.delete(ocena);
			}
		}
		
		if (!nastavnik.getNastavnikOdeljenjePredmet().isEmpty()) {
			return new ResponseEntity<RESTError>(
					new RESTError(4, "Brisanje nije dozvoljeno jer nastavnik predaje predmet trenutno."),
					HttpStatus.BAD_REQUEST);
		}
		nastavnikRepository.deleteById(idNastavnik);
		return new ResponseEntity<>("Uz nastavnika su obrisane sve ocene koje je uneo." + nastavnik, HttpStatus.OK);
	}
	//Nastavnik FRONT
	// pregled svih ocena koje je dao
	@Secured({ "ROLE_NASTAVNIKA", "ROLE_ADMINA" })
	@RequestMapping(value = "pregled/ocena", method = RequestMethod.GET)
	public ResponseEntity<?> pregledOcena(Principal principal) {

		String username = principal.getName();

		// Provera da li ima unetih ocena
		if (nastavnikDao.vracanjeSvihOcenaNastavnika(username).isEmpty()) {
			return new ResponseEntity<RESTError>(new RESTError(5, "Nastavnik nije uneo ni jednu ocenu"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(nastavnikDao.vracanjeSvihOcenaNastavnika(username), HttpStatus.OK);

	}
	//pregled ocenjenih ucenika api/v1/nastavnik/pregled/ucenika
	@Secured("ROLE_NASTAVNIKA")
	@RequestMapping(value = "/pregled/ucenika", method = RequestMethod.GET)
	public ResponseEntity<?> pregledUcenjenihUcenika(Principal principal) {

		String username = principal.getName();

		// Provera da li ima unetih ocena
		if (nastavnikDao.vracanjeSvihOcenaNastavnika(username).isEmpty()) {
			return new ResponseEntity<RESTError>(new RESTError(5, "Nastavnik nije uneo ni jednu ocenu"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(nastavnikDao.vracanjeSvihOcenjenihUcenika(username), HttpStatus.OK);

	}
	//vracanje ocena nastavnik po uceniku api/v1/nastavnik/ocene/{idUcenik}
	@Secured("ROLE_NASTAVNIKA")
	@RequestMapping(value = "/ocene/{idUcenik}", method = RequestMethod.GET)
	public ResponseEntity<?> pregledSvihOcenaPoUceniku(@PathVariable Integer idUcenik,
			Principal principal) {

		String username = principal.getName();
		NastavnikEntity nastavnik = nastavnikRepository.findByKorisnickoIme(username);

		// Provera da li postoji predmet
		if (ucenikRepository.findById(idUcenik).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Id predmeta ne postoji."), HttpStatus.NOT_FOUND);
		}
		// Provera da li ima ocena
		if (ocenaDao.vracanjeOcenaPoUcenikuINastavniku(idUcenik, nastavnik.getIdNastavnik()).isEmpty() == true) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Ucenik nema ocena iz tog predmeta."),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ocenaDao.vracanjeOcenaPoUcenikuINastavniku(idUcenik, nastavnik.getIdNastavnik()),
				HttpStatus.OK);
	}
	
	//vracanje svih odeljenja kojima predaje
	@Secured("ROLE_NASTAVNIKA")
	@RequestMapping(value = "/odeljenja", method = RequestMethod.GET)
	public ResponseEntity<?> pregledSvihOdeljenjaKojimaPredaje(Principal principal) {

		String username = principal.getName();
		NastavnikEntity nastavnik = nastavnikRepository.findByKorisnickoIme(username);
		List<OdeljenjePredmetDTO> odeljenja = new ArrayList<>();
		
		
		List<Nastavnik_Odeljenje_PredmetEntity> kombinacije = grupniRepository.findByNastavnik_IdNastavnik(nastavnik.getIdNastavnik());
		if(kombinacije.isEmpty() == true) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Nastavnik ne predaje ni jednom odeljenju."),
					HttpStatus.NOT_FOUND);
		}
		
		for(Nastavnik_Odeljenje_PredmetEntity kombinaciju: kombinacije) {
			OdeljenjePredmetDTO odeljenje = new OdeljenjePredmetDTO(kombinaciju.getOdeljenje().getIdOdeljenje() ,kombinaciju.getOdeljenje().getOdeljenje(), kombinaciju.getOdeljenje().getRazred() ,razredSkolskaGodinaDao.toRomanNumerals(Integer.parseInt(kombinaciju.getOdeljenje().getRazred().getRazred())), kombinaciju.getPredmet().getNazivPredmeta().getNazivPredmeta());
			odeljenja.add(odeljenje);
		}
		
		return new ResponseEntity<>(odeljenja,HttpStatus.OK);
	}
	//vracanje svih ucenika kojima predaje u odeljenju
	@Secured("ROLE_NASTAVNIKA")
	@RequestMapping(value = "/ucenici/odeljenje/{idOdeljenja}", method = RequestMethod.GET)
	public ResponseEntity<?> pregledSvihUcenikaKojimaPredaje(@PathVariable Integer idOdeljenja) {

		if(odeljenjeRepository.findById(idOdeljenja).isPresent() == false ) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Odeljenje ne postoji."),
					HttpStatus.NOT_FOUND);
		}		
		List<UcenikEntity> ucenici = ucenikRepository.findByOdeljenje_IdOdeljenje(idOdeljenja);
		
		return new ResponseEntity<>(ucenici,HttpStatus.OK);
	}
	
		//vracanje svih predmeta koje predaje
		@Secured("ROLE_NASTAVNIKA")
		@RequestMapping(value = "/moji/predmeti/", method = RequestMethod.GET)
		public ResponseEntity<?> mojiPredmeti(Principal principal) {

			String username = principal.getName();
			NastavnikEntity nastavnik = nastavnikRepository.findByKorisnickoIme(username);
			List<PredmetEntity> predmeti = new ArrayList<>();
			
			List<Nastavnik_Odeljenje_PredmetEntity> kombinacije = grupniRepository.findByNastavnik_IdNastavnik(nastavnik.getIdNastavnik());
			if(kombinacije.isEmpty() == true) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Nastavnik ne predaje ni jedan predmet."),
						HttpStatus.NOT_FOUND);
			}
			
			for(Nastavnik_Odeljenje_PredmetEntity kombinacija: kombinacije) {
				if(kombinacija.getNastavnik().getIdNastavnik().equals(nastavnik.getIdNastavnik())) {
					predmeti.add(predmetRepository.findById(kombinacija.getPredmet().getIdPredmet()).get());
					
				}
			}
			
			return new ResponseEntity<>(predmeti,HttpStatus.OK);
		}
	
		//vracanje svih predmeta koje predaje
		@Secured("ROLE_NASTAVNIKA")
		@RequestMapping(value = "/moji/predmeti/{idOdeljenja}", method = RequestMethod.GET)
		public ResponseEntity<?> mojiPredmeti(@PathVariable Integer idOdeljenja, Principal principal) {

			String username = principal.getName();
			NastavnikEntity nastavnik = nastavnikRepository.findByKorisnickoIme(username);
			
			OdeljenjeEntity odeljenje = odeljenjeRepository.findById(idOdeljenja).get();
			List<PredmetEntity> predmeti = new ArrayList<>();
			
			List<Nastavnik_Odeljenje_PredmetEntity> kombinacije = grupniRepository.findByNastavnik_IdNastavnikAndOdeljenje_idOdeljenje(nastavnik.getIdNastavnik(), odeljenje.getIdOdeljenje());
			if(kombinacije.isEmpty() == true) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Nastavnik ne predaje ni jedan predmet."),
						HttpStatus.NOT_FOUND);
			}
			
			for(Nastavnik_Odeljenje_PredmetEntity kombinacija: kombinacije) {
				if(kombinacija.getNastavnik().getIdNastavnik().equals(nastavnik.getIdNastavnik())) {
					predmeti.add(predmetRepository.findById(kombinacija.getPredmet().getIdPredmet()).get());
					
				}
			}
			
			return new ResponseEntity<>(predmeti,HttpStatus.OK);
		}


	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
	}
	


}
