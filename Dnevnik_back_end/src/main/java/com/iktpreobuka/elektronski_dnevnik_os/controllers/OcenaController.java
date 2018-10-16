package com.iktpreobuka.elektronski_dnevnik_os.controllers;

import java.security.Principal;
import java.util.Date;
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
import com.iktpreobuka.elektronski_dnevnik_os.entities.OcenaEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.enumerations.ETipOcene;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.NastavnikRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.Nastavnik_Odeljenje_PredmetRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.OcenaRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.OdeljenjeRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.PredmetRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.Razred_SkolskaGodinaRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.UcenikRepository;
import com.iktpreobuka.elektronski_dnevnik_os.services.EmailDao;
import com.iktpreobuka.elektronski_dnevnik_os.services.Nastavnik_Odeljenje_PredmetDao;
import com.iktpreobuka.elektronski_dnevnik_os.services.OcenaDao;

@RestController
@RequestMapping(path = "api/v1/ocena")
public class OcenaController {

	@Autowired
	public NastavnikRepository nastavnikRepository;
	@Autowired
	public PredmetRepository predmetRepository;
	@Autowired
	public Razred_SkolskaGodinaRepository razredRepository;
	@Autowired
	public OdeljenjeRepository odeljenjeRepository;
	@Autowired
	public UcenikRepository ucenikRepository;
	@Autowired
	public Nastavnik_Odeljenje_PredmetDao nasOdePreDao;
	@Autowired
	public OcenaRepository ocenaRepository;
	@Autowired
	public Nastavnik_Odeljenje_PredmetRepository nastavnikOdeljenjePredmetRepository;
	@Autowired
	public EmailDao emailDao;
	@Autowired
	public OcenaDao ocenaDao;
	
	@Secured("ROLE_NASTAVNIKA")
	@RequestMapping(value = "/get/{idOcena}", method = RequestMethod.GET)
	public ResponseEntity<?> getOcena(@PathVariable Integer idOcena) {
		if (ocenaRepository.findById(idOcena).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Ocena ne postoji u bazi."),
					HttpStatus.NOT_FOUND);
		}
		OcenaEntity ocena = ocenaRepository.findById(idOcena).get();
		return new ResponseEntity<>(ocena, HttpStatus.OK);
	}

	// Unos ocene u prvom polugodistu uz automatsko slanje maila
	@Secured("ROLE_NASTAVNIKA")
	@RequestMapping(value = "/redovna/pprvo/predmet/{idPredmet}/ucenik/{idUcenik}/", method = RequestMethod.POST)
	public ResponseEntity<?> unosOcenePrvoPolugodiste(@Valid @PathVariable Integer idPredmet,
			@PathVariable Integer idUcenik, @RequestBody OcenaEntity novaOcena, Principal principal, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		String username = principal.getName();
		
		// Provera da li postoje svi id-jevi
		if (predmetRepository.findById(idPredmet).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Id predmeta ne postoji."), HttpStatus.NOT_FOUND);
		}
		if (ucenikRepository.findById(idUcenik).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Id ucenika ne postoji."), HttpStatus.NOT_FOUND);
		}

	

		NastavnikEntity nastavnik = nastavnikRepository.findByKorisnickoIme(username);
		PredmetEntity predmet = predmetRepository.findById(idPredmet).get();
		UcenikEntity ucenik = ucenikRepository.findById(idUcenik).get();
		OdeljenjeEntity odeljenje = odeljenjeRepository.findById(ucenik.getOdeljenje().getIdOdeljenje()).get();
		OcenaEntity ocena = new OcenaEntity();
		
		//Provera da li nastavnik predaje uceniku
		if(nastavnikOdeljenjePredmetRepository.findByNastavnik_IdNastavnik(nastavnik.getIdNastavnik()).isEmpty()) {
			return new ResponseEntity<RESTError>(new RESTError(6, "Nastavnik ne predaje ni jedan predmet"),
					HttpStatus.BAD_REQUEST);
		}
		
		if(nastavnikOdeljenjePredmetRepository.findByNastavnik_IdNastavnikAndPredmet_IdPredmet(nastavnik.getIdNastavnik(), predmet.getIdPredmet()).isEmpty()) {
			return new ResponseEntity<RESTError>(new RESTError(6, "Nastavnik ne predaje taj predmet"),
					HttpStatus.BAD_REQUEST);
		}
		
		if(nastavnikOdeljenjePredmetRepository.findByNastavnik_IdNastavnikAndOdeljenje_IdOdeljenjeAndPredmet_IdPredmet(nastavnik.getIdNastavnik(), odeljenje.getIdOdeljenje(), predmet.getIdPredmet()) == null) {
			return new ResponseEntity<RESTError>(new RESTError(6, "Nastavnik ne predaje taj predmet tom uceniku."),
					HttpStatus.BAD_REQUEST);
			
			
		}
		
		ocena.setVisinaOcene(novaOcena.getVisinaOcene());
		ocena.setTipOcene(ETipOcene.TIP_IREDOVNA);
		ocena.setDatum(new Date());
		ocena.setOpisOcene(novaOcena.getOpisOcene());
		ocena.setNastavnik(nastavnik.getIme()+" "+nastavnik.getPrezime());
		ocena.setPredmet(predmet.getNazivPredmeta().getNazivPredmeta());
		ocena.setUcenik(ucenik);
		
		ocenaRepository.save(ocena);
		
		// slanje maila
		emailDao.sendTemplateMessage(ocena);
		return new ResponseEntity<>(ocena,HttpStatus.OK);
	}
			
			
		
				
	

	// Unos ocene u drugom polugodistu uz automatsko slanje maila
	@Secured("ROLE_NASTAVNIKA")
	@RequestMapping(value = "/redovna/pdrugo/predmet/{idPredmet}/ucenik/{idUcenik}/", method = RequestMethod.POST)
	public ResponseEntity<?> unosOceneDrugoPolugodiste(@Valid @PathVariable Integer idPredmet, @PathVariable Integer idUcenik, @RequestBody OcenaEntity novaOcena,
			BindingResult result, Principal principal) throws Exception {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		// Provera da li postoje svi id-jevi
		if (predmetRepository.findById(idPredmet).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Id predmeta ne postoji."), HttpStatus.NOT_FOUND);
		}
		if (ucenikRepository.findById(idUcenik).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Id ucenika ne postoji."), HttpStatus.NOT_FOUND);
		}
		
		String username = principal.getName();

		NastavnikEntity nastavnik = nastavnikRepository.findByKorisnickoIme(username);
		PredmetEntity predmet = predmetRepository.findById(idPredmet).get();
		UcenikEntity ucenik = ucenikRepository.findById(idUcenik).get();
		OdeljenjeEntity odeljenje = odeljenjeRepository.findById(ucenik.getOdeljenje().getIdOdeljenje()).get();
		OcenaEntity ocena = new OcenaEntity();
		
		//Provera da li nastavnik predaje uceniku
		if(nastavnikOdeljenjePredmetRepository.findByNastavnik_IdNastavnik(nastavnik.getIdNastavnik()).isEmpty()) {
			return new ResponseEntity<RESTError>(new RESTError(6, "Nastavnik ne predaje ni jedan predmet"),
					HttpStatus.BAD_REQUEST);
		}
		
		if(nastavnikOdeljenjePredmetRepository.findByNastavnik_IdNastavnikAndPredmet_IdPredmet(nastavnik.getIdNastavnik(), predmet.getIdPredmet()).isEmpty()) {
			return new ResponseEntity<RESTError>(new RESTError(6, "Nastavnik ne predaje taj predmet"),
					HttpStatus.BAD_REQUEST);
		}
		
		if(nastavnikOdeljenjePredmetRepository.findByNastavnik_IdNastavnikAndOdeljenje_IdOdeljenjeAndPredmet_IdPredmet(nastavnik.getIdNastavnik(), odeljenje.getIdOdeljenje(), predmet.getIdPredmet()) == null) {
			return new ResponseEntity<RESTError>(new RESTError(6, "Nastavnik ne predaje taj predmet tom uceniku."),
					HttpStatus.BAD_REQUEST);
		}

		ocena.setVisinaOcene(novaOcena.getVisinaOcene());
		ocena.setTipOcene(ETipOcene.TIP_IIREDOVNA);
		ocena.setDatum(new Date());
		ocena.setOpisOcene(novaOcena.getOpisOcene());
		ocena.setNastavnik(nastavnik.getIme() + " " + nastavnik.getPrezime());
		ocena.setPredmet(predmet.getNazivPredmeta().getNazivPredmeta());
		ocena.setUcenik(ucenik);
		ocenaRepository.save(ocena);

		// slanje maila
		emailDao.sendTemplateMessage(ocena);

		return new ResponseEntity<>(ocena, HttpStatus.OK);
	}

	// Unos ocene na kraju polugodista
	@Secured("ROLE_NASTAVNIKA")
	@RequestMapping(value = "/polugodisna/predmet/{idPredmet}/ucenik/{idUcenik}/", method = RequestMethod.POST)
	public ResponseEntity<?> unosOceneNaPolugodistu(@Valid @PathVariable Integer idPredmet, @PathVariable Integer idUcenik, @RequestBody OcenaEntity novaOcena,
			BindingResult result, Principal principal) throws Exception {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		// Provera da li postoje svi id-jevi
		if (predmetRepository.findById(idPredmet).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Id predmeta ne postoji."), HttpStatus.NOT_FOUND);
		}
		if (ucenikRepository.findById(idUcenik).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Id ucenika ne postoji."), HttpStatus.NOT_FOUND);
		}

		String username = principal.getName();

		NastavnikEntity nastavnik = nastavnikRepository.findByKorisnickoIme(username);
		PredmetEntity predmet = predmetRepository.findById(idPredmet).get();
		UcenikEntity ucenik = ucenikRepository.findById(idUcenik).get();
		OdeljenjeEntity odeljenje = odeljenjeRepository.findById(ucenik.getOdeljenje().getIdOdeljenje()).get();
		OcenaEntity ocena = new OcenaEntity();

		//Provera da li nastavnik predaje uceniku
		if(nastavnikOdeljenjePredmetRepository.findByNastavnik_IdNastavnik(nastavnik.getIdNastavnik()).isEmpty()) {
			return new ResponseEntity<RESTError>(new RESTError(6, "Nastavnik ne predaje ni jedan predmet"),
					HttpStatus.BAD_REQUEST);
		}
		if(nastavnikOdeljenjePredmetRepository.findByNastavnik_IdNastavnikAndPredmet_IdPredmet(nastavnik.getIdNastavnik(), predmet.getIdPredmet()).isEmpty()) {
			return new ResponseEntity<RESTError>(new RESTError(6, "Nastavnik ne predaje taj predmet"),
					HttpStatus.BAD_REQUEST);
		}
		if(nastavnikOdeljenjePredmetRepository.findByNastavnik_IdNastavnikAndOdeljenje_IdOdeljenjeAndPredmet_IdPredmet(nastavnik.getIdNastavnik(), odeljenje.getIdOdeljenje(), predmet.getIdPredmet()) == null) {
			return new ResponseEntity<RESTError>(new RESTError(6, "Nastavnik ne predaje taj predmet tom uceniku."),
					HttpStatus.BAD_REQUEST);
		}
		
		// Provera da li ucenik ima min 4 ocene iz tog predmeta da bi mogao da zakljuci
		// na polugodistu ocenu
		if (ocenaDao.vracanjeSvihRegularnihOcenaIzIPolugodistaUcenika(nastavnik.getIdNastavnik(), idPredmet, idUcenik).size() < 4) {
			return new ResponseEntity<RESTError>(new RESTError(7,
					"Nije dozvoljeno zakljuciti ocenu na polugodistu jer ucenik ima manje od 4 ocene iz tog predmeta do sada."),
					HttpStatus.BAD_REQUEST);
		}
		// Provera da li je ocena na polugodistu vec zakljucena
		if (ocenaDao.vracanjeOceneSaPolugodistaUcenika(nastavnik.getIdNastavnik(), idPredmet, idUcenik) != null) {
			return new ResponseEntity<RESTError>(
					new RESTError(7, "Ocena na polugodistu je vec zakljucena nije je moguce na ovaj nacin promeniti."),
					HttpStatus.BAD_REQUEST);
		}
		ocena.setVisinaOcene(novaOcena.getVisinaOcene());
		ocena.setTipOcene(ETipOcene.TIP_POLUGODISNA);
		ocena.setDatum(new Date());
		ocena.setOpisOcene(novaOcena.getOpisOcene());
		ocena.setNastavnik(nastavnik.getIme() + " " + nastavnik.getPrezime());
		ocena.setPredmet(predmet.getNazivPredmeta().getNazivPredmeta());
		ocena.setUcenik(ucenik);
		ocenaRepository.save(ocena);

		// slanje maila
		emailDao.sendTemplateMessage(ocena);

		return new ResponseEntity<>(ocena, HttpStatus.OK);
	}

	// Unos zakljucne ocene
	@Secured("ROLE_NASTAVNIKA")
	@RequestMapping(value = "/zakljucna/predmet/{idPredmet}/ucenik/{idUcenik}/", method = RequestMethod.POST)
	public ResponseEntity<?> unosZakljucneOcene(@Valid @PathVariable Integer idPredmet, @PathVariable Integer idUcenik, @RequestBody OcenaEntity novaOcena,
			BindingResult result, Principal principal) throws Exception {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		// Provera da li postoje svi id-jevi
		if (predmetRepository.findById(idPredmet).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Id predmeta ne postoji."), HttpStatus.NOT_FOUND);
		}
		if (ucenikRepository.findById(idUcenik).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Id ucenika ne postoji."), HttpStatus.NOT_FOUND);
		}

		String username = principal.getName();

		NastavnikEntity nastavnik = nastavnikRepository.findByKorisnickoIme(username);
		PredmetEntity predmet = predmetRepository.findById(idPredmet).get();
		UcenikEntity ucenik = ucenikRepository.findById(idUcenik).get();
		OdeljenjeEntity odeljenje = odeljenjeRepository.findById(ucenik.getOdeljenje().getIdOdeljenje()).get();
		OcenaEntity ocena = new OcenaEntity();

		// Provera da li nastavnik ima pravo da dodeli ocenu tom uceniku
		if(nastavnikOdeljenjePredmetRepository.findByNastavnik_IdNastavnik(nastavnik.getIdNastavnik()).isEmpty()) {
			return new ResponseEntity<RESTError>(new RESTError(6, "Nastavnik ne predaje ni jedan predmet"),
					HttpStatus.BAD_REQUEST);
		}
		if(nastavnikOdeljenjePredmetRepository.findByNastavnik_IdNastavnikAndPredmet_IdPredmet(nastavnik.getIdNastavnik(), predmet.getIdPredmet()).isEmpty()) {
			return new ResponseEntity<RESTError>(new RESTError(6, "Nastavnik ne predaje taj predmet"),
					HttpStatus.BAD_REQUEST);
		}
		if(nastavnikOdeljenjePredmetRepository.findByNastavnik_IdNastavnikAndOdeljenje_IdOdeljenjeAndPredmet_IdPredmet(nastavnik.getIdNastavnik(), odeljenje.getIdOdeljenje(), predmet.getIdPredmet()) == null) {
			return new ResponseEntity<RESTError>(new RESTError(6, "Nastavnik ne predaje taj predmet tom uceniku."),
					HttpStatus.BAD_REQUEST);
		}

		// Provera da li ucenik ima min 4 ocene iz tog predmeta u drugom polugodistu
		if (ocenaDao.vracanjeSvihRegularnihOcenaIzIIPolugodistaUcenika(nastavnik.getIdNastavnik(), idPredmet, idUcenik).size() < 4) {
			return new ResponseEntity<RESTError>(new RESTError(7,
					"Nije dozvoljeno zakljuciti ocenu jer ucenik ima manje od 4 ocene iz tog predmeta u drugom polugodistu."),
					HttpStatus.BAD_REQUEST);
		}
		// Provera da li je ocena na polugodistu zakljucena
		if (ocenaDao.vracanjeOceneSaPolugodistaUcenika(nastavnik.getIdNastavnik(), idPredmet, idUcenik) == null) {
			return new ResponseEntity<RESTError>(new RESTError(7,
					"Uceniku ocena na polugodistu nije zakljucena tako da ne moze da mu se zakljuci ocena iz ovog predmeta."),
					HttpStatus.BAD_REQUEST);
		}
		// Provera da li je ocena vec zakljucena
		if (ocenaDao.vracanjeZakljucneOceneUcenika(nastavnik.getIdNastavnik(), idPredmet, idUcenik) != null) {
			return new ResponseEntity<RESTError>(
					new RESTError(7, "Uceniku je vec zakljucena ocena nije je moguce na ovaj nacin promeniti."),
					HttpStatus.BAD_REQUEST);
		}
		ocena.setVisinaOcene(novaOcena.getVisinaOcene());
		ocena.setTipOcene(ETipOcene.TIP_ZAKLJUCNA);
		ocena.setDatum(new Date());
		ocena.setOpisOcene(novaOcena.getOpisOcene());
		ocena.setNastavnik(nastavnik.getIme() + " " + nastavnik.getPrezime());
		ocena.setPredmet(predmet.getNazivPredmeta().getNazivPredmeta());
		ocena.setUcenik(ucenik);
		ocenaRepository.save(ocena);

		// slanje maila
		//emailDao.sendTemplateMessage(ocena);

		return new ResponseEntity<>(ocena, HttpStatus.OK);
	}

	// Izmeni ocenu
	@Secured("ROLE_NASTAVNIKA")
	@RequestMapping(value = "/izmena/ocene/{idOcene}/", method = RequestMethod.PUT)
	public ResponseEntity<?> izmenaOcene(@Valid @PathVariable Integer idOcene, @RequestBody OcenaEntity novaOcena,
			Principal principal, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}

		// Provera da li postoji ocena
		if (ocenaRepository.findById(idOcene).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Ocena ne postoji u sistemu"), HttpStatus.NOT_FOUND);
		}

		String username = principal.getName();
		OcenaEntity ocena = ocenaRepository.findById(idOcene).get();
		NastavnikEntity nastavnik = nastavnikRepository.findByKorisnickoIme(username);

		// Provera da li nastvavnik predaje tom uceniku
		if (!ocena.getNastavnik().equals(nastavnik.getIme() + " " + nastavnik.getPrezime())) {
			return new ResponseEntity<RESTError>(new RESTError(7, "Nastavnik ne predaje uceniku."),
					HttpStatus.FORBIDDEN);
		}

		ocena.setVisinaOcene(novaOcena.getVisinaOcene());
		ocena.setOpisOcene(novaOcena.getOpisOcene());
		ocenaRepository.save(ocena);

		return new ResponseEntity<>(ocena, HttpStatus.OK);
	}

	// Obrisi ocenu
	@Secured("ROLE_NASTAVNIKA")
	@RequestMapping(value = "/brisanje/ocene/{idOcene}/", method = RequestMethod.DELETE)
	public ResponseEntity<?> brisanjeOcene(@PathVariable Integer idOcene, Principal principal) throws Exception {

		// Provera da li postoji ocena
		if (ocenaRepository.findById(idOcene).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Ocena ne postoji u sistemu"), HttpStatus.NOT_FOUND);
		}

		String username = principal.getName();
		OcenaEntity ocena = ocenaRepository.findById(idOcene).get();
		NastavnikEntity nastavnik = nastavnikRepository.findByKorisnickoIme(username);

		// Provera da li nastvavnik predaje tom uceniku
		if (!ocena.getNastavnik().equals(nastavnik.getIme() + " " + nastavnik.getPrezime())) {
			return new ResponseEntity<RESTError>(
					new RESTError(7, "Nastavnik ne predaje uceniku, nema pravo brisanja ocene."), HttpStatus.FORBIDDEN);
		}

		ocenaRepository.deleteById(idOcene);
		return new ResponseEntity<>(ocena, HttpStatus.OK);
	}

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
	}

}
