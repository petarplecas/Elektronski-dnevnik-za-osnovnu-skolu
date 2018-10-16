package com.iktpreobuka.elektronski_dnevnik_os.controllers;

import java.security.Principal;
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
import com.iktpreobuka.elektronski_dnevnik_os.entities.OcenaEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.RoditeljEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.UlogaEntity;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.OcenaRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.OdeljenjeRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.PredmetRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.RoditeljRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.UcenikRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.UlogaRepository;
import com.iktpreobuka.elektronski_dnevnik_os.services.EncDao;
import com.iktpreobuka.elektronski_dnevnik_os.services.OcenaDao;

@RestController
@RequestMapping(path = "api/v1/ucenik")
public class UcenikController {

	@Autowired
	public UcenikRepository ucenikRepository;
	@Autowired
	public RoditeljRepository roditeljRepository;
	@Autowired
	public OdeljenjeRepository odeljenjeRepository;
	@Autowired
	public OcenaRepository ocenaRepository;
	@Autowired
	public PredmetRepository predmetRepository;
	@Autowired
	public OcenaDao ocenaDao;
	@Autowired
	public UlogaRepository ulogaRepository;
	@Autowired
	public EncDao encDao;
	
	//pregled svih ucenika
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/svi/", method = RequestMethod.GET)
	public ResponseEntity<?> getSveUcenikeAdminu() {

		return new ResponseEntity<>(ucenikRepository.findAll(), HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/svi/{idUcenik}", method = RequestMethod.GET)
	public ResponseEntity<?> getUcenikaAdminu(@PathVariable Integer idUcenik) {

		UcenikEntity ucenik = ucenikRepository.findById(idUcenik).get();
		return new ResponseEntity<>(ucenik, HttpStatus.OK);
	}

	// Unos novog ucenika
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> unosNovogUcenika(@Valid @RequestBody UcenikEntity noviUcenik, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		UlogaEntity uloga = ulogaRepository.findByUloga("ROLE_UCENIKA");
		UcenikEntity ucenik = new UcenikEntity();

		ucenik.setIme(noviUcenik.getIme());
		ucenik.setPrezime(noviUcenik.getPrezime());
		ucenik.setKorisnickoIme(noviUcenik.getKorisnickoIme());
		ucenik.setLozinka(encDao.cryptPassword(noviUcenik.getLozinka()));
		ucenik.setUloga(uloga);
		// Provera da li postoji korisnicko ime u bazi
		if ((ucenikRepository.findByKorisnickoIme(noviUcenik.getKorisnickoIme()) != null) && !ucenikRepository.findByKorisnickoIme(noviUcenik.getKorisnickoIme()).equals(ucenik)) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Korisnicko ime vec postoji."),
					HttpStatus.BAD_REQUEST);
		}

		ucenikRepository.save(ucenik);
		return new ResponseEntity<>(ucenik, HttpStatus.OK);
	}

	// Dodavanje roditelja uceniku
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/{idUcenik}/roditelj/{idRoditelj}/", method = RequestMethod.PUT)
	public ResponseEntity<?> dodavanjeRoditeljaUceniku(@PathVariable Integer idUcenik,
			@PathVariable Integer idRoditelj) {
		// Provera da li postoji ucenik u bazi
		if (ucenikRepository.findById(idUcenik).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Ucenik ne postoji u bazi."), HttpStatus.NOT_FOUND);
		}
		// Provera da li postoji roditelj u bazi
		if (roditeljRepository.findById(idRoditelj).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Roditelj ne postoji u bazi."), HttpStatus.NOT_FOUND);
		}
		UcenikEntity ucenik = ucenikRepository.findById(idUcenik).get();
		RoditeljEntity roditelj = roditeljRepository.findById(idRoditelj).get();
		ucenik.setRoditelj(roditelj);
		ucenikRepository.save(ucenik);
		return new ResponseEntity<>(ucenik, HttpStatus.OK);
	}

	// Dodavanje odeljenja uceniku
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/{idUcenik}/odeljenje/{idOdeljenja}/", method = RequestMethod.PUT)
	public ResponseEntity<?> dodavanjeOdeljenjaUceniku(@PathVariable Integer idUcenik,
			@PathVariable Integer idOdeljenja) {
		// Provera da li postoji ucenik u bazi
		if (ucenikRepository.findById(idUcenik).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Ucenik ne postoji u bazi."), HttpStatus.NOT_FOUND);
		}
		// Provera da li postoji odeljenje u bazi
		if (odeljenjeRepository.findById(idOdeljenja).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Odeljenje ne postoji u bazi."),
					HttpStatus.NOT_FOUND);
		}
		UcenikEntity ucenik = ucenikRepository.findById(idUcenik).get();
		OdeljenjeEntity odeljenje = odeljenjeRepository.findById(idOdeljenja).get();

		ucenik.setOdeljenje(odeljenje);
		ucenikRepository.save(ucenik);
		return new ResponseEntity<>(ucenik, HttpStatus.OK);
	}

	// Unos novog ucenika sa automatskim dodeljivanjem roditelja i odeljenja
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/odeljenje/{idOdeljenje}/roditelj/{idRoditelj}/", method = RequestMethod.POST)
	public ResponseEntity<?> unosCelokupnoNovogUcenika(@Valid @PathVariable Integer idOdeljenje,
			@PathVariable Integer idRoditelj, @RequestBody UcenikEntity noviUcenik, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		// provera da li postoji odeljenje u bazi
		if (odeljenjeRepository.findById(idOdeljenje).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Odeljenje ne postoji u bazi."),
					HttpStatus.NOT_FOUND);
		}
		// provera da li postoji roditelj u bazi
		if (roditeljRepository.findById(idRoditelj).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Roditelj ne postoji u bazi."), HttpStatus.NOT_FOUND);
		}
		OdeljenjeEntity odeljenje = odeljenjeRepository.findById(idOdeljenje).get();
		RoditeljEntity roditelj = roditeljRepository.findById(idRoditelj).get();

		UlogaEntity uloga = ulogaRepository.findByUloga("ROLE_UCENIKA");
		UcenikEntity ucenik = new UcenikEntity();

		ucenik.setIme(noviUcenik.getIme());
		ucenik.setPrezime(noviUcenik.getPrezime());
		ucenik.setKorisnickoIme(noviUcenik.getKorisnickoIme());
		ucenik.setLozinka(encDao.cryptPassword(noviUcenik.getLozinka()));
		ucenik.setOdeljenje(odeljenje);
		ucenik.setRoditelj(roditelj);
		ucenik.setUloga(uloga);
		if ((ucenikRepository.findByKorisnickoIme(noviUcenik.getKorisnickoIme()) != null) && !ucenikRepository.findByKorisnickoIme(noviUcenik.getKorisnickoIme()).equals(ucenik)) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Korisnicko ime vec postoji."),
					HttpStatus.BAD_REQUEST);
		}
		
		
		ucenikRepository.save(ucenik);
		return new ResponseEntity<>(ucenik, HttpStatus.OK);
	}

	// izmena unosa ucenika(ime, prezime, korisnicko ime(sa proverom da li
	// postoji u bazi))
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/{idUcenik}/", method = RequestMethod.PUT)
	public ResponseEntity<?> izmenaUcenika(@Valid @PathVariable Integer idUcenik, @RequestBody UcenikEntity noviUcenik,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		// Provera da li postoji ucenik u bazi
		if (ucenikRepository.findById(idUcenik).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Ucenik ne postoji u bazi."), HttpStatus.NOT_FOUND);
		}
		UcenikEntity ucenik = ucenikRepository.findById(idUcenik).get();
		ucenik.setIme(noviUcenik.getIme());
		ucenik.setPrezime(noviUcenik.getPrezime());
		ucenik.setKorisnickoIme(noviUcenik.getKorisnickoIme());
		// Provera da li postoji korisnicko ime u bazi
		if ((ucenikRepository.findByKorisnickoIme(noviUcenik.getKorisnickoIme()) != null) && !ucenikRepository.findByKorisnickoIme(noviUcenik.getKorisnickoIme()).equals(ucenik)) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Korisnicko ime vec postoji."),
					HttpStatus.BAD_REQUEST);
		}
		ucenikRepository.save(ucenik);
		return new ResponseEntity<>(ucenik, HttpStatus.OK);
	}

	// brisanje ucenika uz uslov da ne pripada odeljenju sto automatski znaci i da
	// nema ocenu unesenu zbog uslova u dodeli ocene
	// TODO Probati da napravis proveru po skolskoj godini i iz tog uslova dozvoliti
	// brisanje
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/{idUcenik}/", method = RequestMethod.DELETE)
	public ResponseEntity<?> brisanjeUcenika(@PathVariable Integer idUcenik) {

		// Provera da li postoji ucenik u bazi
		if (ucenikRepository.findById(idUcenik).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Ucenik ne postoji u bazi."), HttpStatus.NOT_FOUND);
		}
		UcenikEntity ucenik = ucenikRepository.findById(idUcenik).get();
		// Brisanje ocena ucenika prvo
		List<OcenaEntity> ocene = (List<OcenaEntity>) ocenaRepository.findAll();
		for(OcenaEntity ocena: ocene) {
			if(ocena.getUcenik().getIdUcenik().equals(idUcenik) == true) {
				ocenaRepository.delete(ocena);
			}
		}

		ucenikRepository.deleteById(idUcenik);
		return new ResponseEntity<>(ucenik, HttpStatus.OK);
	}

	// Pregled svih svojih ocena po predmetu - I polugodiste
	@Secured("ROLE_UCENIKA")
	@RequestMapping(value = "redovna/prvo/polugodiste/predmet/{idPredmet}/", method = RequestMethod.GET)
	public ResponseEntity<?> pregledSvihRegularnihOcenaPoPredmetuUcenikaIPolugodiste(@PathVariable Integer idPredmet,
			Principal principal) {

		String username = principal.getName();

		// Provera da li postoji predmet
		if (predmetRepository.findById(idPredmet).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Id predmeta ne postoji."), HttpStatus.NOT_FOUND);
		}
		// Provera da li ima ocena
		if (ocenaDao.vracanjeRegularnihOcenaUcenikaPoPredmetuIPolugodiste(idPredmet, username).isEmpty()) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Ucenik nema ocena iz tog predmeta."),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ocenaDao.vracanjeRegularnihOcenaUcenikaPoPredmetuIPolugodiste(idPredmet, username),
				HttpStatus.OK);
	}

	// Pregled svih svojih ocena po predmetu - II polugodiste
	@Secured("ROLE_UCENIKA")
	@RequestMapping(value = "redovna/drugo/polugodiste/predmet/{idPredmet}/", method = RequestMethod.GET)
	public ResponseEntity<?> pregledSvihRegularnihOcenaPoPredmetuUcenikaIIPolugodiste(@PathVariable Integer idPredmet,
			Principal principal) {

		String username = principal.getName();

		// Provera da li postoji predmet
		if (predmetRepository.findById(idPredmet).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Id predmeta ne postoji."), HttpStatus.NOT_FOUND);
		}
		// Provera da li ima ocena
		if (ocenaDao.vracanjeRegularnihOcenaUcenikaPoPredmetuIIPolugodite(idPredmet, username).isEmpty()) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Ucenik nema ocena iz tog predmeta."),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ocenaDao.vracanjeRegularnihOcenaUcenikaPoPredmetuIIPolugodite(idPredmet, username),
				HttpStatus.OK);
	}

	// Pregled polugodisne ocene
	@Secured("ROLE_UCENIKA")
	@RequestMapping(value = "polugodina/ocena/predmet/{idPredmet}/", method = RequestMethod.GET)
	public ResponseEntity<?> pregledPolugodisneOcenePoPredmetuUcenika(@PathVariable Integer idPredmet,
			Principal principal) {

		String username = principal.getName();

		// Provera da li postoji predmet
		if (predmetRepository.findById(idPredmet).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Id predmeta ne postoji."), HttpStatus.NOT_FOUND);
		}
		// Provera da li ima ocena
		if (ocenaDao.vracanjePolugodisneOceneUcenikaPoPredmetu(idPredmet, username) == null) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Ucenik nema zakljucenu ocenu na polugodistu."),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ocenaDao.vracanjePolugodisneOceneUcenikaPoPredmetu(idPredmet, username),
				HttpStatus.OK);
	}

	// Pregled zakljucne ocene
	@RequestMapping(value = "zakljucna/ocena/predmet/{idPredmet}/", method = RequestMethod.GET)
	public ResponseEntity<?> pregledZakljucneOcenePoPredmetuUcenika(@PathVariable Integer idPredmet,
			Principal principal) {

		String username = principal.getName();

		// Provera da li postoji predmet
		if (predmetRepository.findById(idPredmet).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Id predmeta ne postoji."), HttpStatus.NOT_FOUND);
		}
		// Provera da li ima ocena
		if (ocenaDao.vracanjeZakljucneOceneUcenikaPoPredmetu(idPredmet, username) == null) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Ucenik nema zakljucenu ocenu."),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ocenaDao.vracanjeZakljucneOceneUcenikaPoPredmetu(idPredmet, username),
				HttpStatus.OK);
	}
	
	
	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
	}
}
