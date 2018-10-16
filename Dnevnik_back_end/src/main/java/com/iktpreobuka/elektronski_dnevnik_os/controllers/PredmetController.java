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
import com.iktpreobuka.elektronski_dnevnik_os.entities.Nastavnik_Odeljenje_PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.NazivPredmetaEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.Razred_SkolskaGodinaEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.RoditeljEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.Nastavnik_Odeljenje_PredmetRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.NazivPredmetaRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.PredmetRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.Razred_SkolskaGodinaRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.RoditeljRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.UcenikRepository;

@RestController
@RequestMapping(path = "api/v1/predmet")
public class PredmetController {

	@Autowired
	public NazivPredmetaRepository nazivPredmetaRepository;
	@Autowired
	public Razred_SkolskaGodinaRepository razredRepository;
	@Autowired
	public PredmetRepository predmetRepository;
	@Autowired
	public UcenikRepository ucenikRepository;
	@Autowired
	public Nastavnik_Odeljenje_PredmetRepository grupniRepository;
	@Autowired
	public RoditeljRepository roditeljRepository;
	
	

	// Unos novog predmeta
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/nazivPredmeta/{id_naziv}/razred/{id_razred}/", method = RequestMethod.POST)
	public ResponseEntity<?> unosNovogPredmeta(@Valid @PathVariable(value = "id_naziv") Integer id_naziv,
			@PathVariable(value = "id_razred") Integer id_razred, @RequestBody PredmetEntity noviPredmet,
			BindingResult result) {

		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}

		if (nazivPredmetaRepository.findById(id_naziv).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Id naziva predmeta ne postoji."),
					HttpStatus.NOT_FOUND);
		}

		if (razredRepository.findById(id_razred).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Id razreda ne postoji."), HttpStatus.NOT_FOUND);
		}

		NazivPredmetaEntity naziv = nazivPredmetaRepository.findById(id_naziv).get();
		noviPredmet.setNazivPredmeta(naziv);
		Razred_SkolskaGodinaEntity razred = razredRepository.findById(id_razred).get();
		noviPredmet.setRazred(razred);
		
		List<PredmetEntity> predmeti = (List<PredmetEntity>) predmetRepository.findAll();
		
		for(PredmetEntity predmetEntity : predmeti)
		if (predmetEntity.getNazivPredmeta().getIdNazivPredmet() == naziv.getIdNazivPredmet()
				&& predmetEntity.getRazred().getIdRsg() == razred.getIdRsg()) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Predmet vec postoji."), HttpStatus.BAD_REQUEST);
		}
		predmetRepository.save(noviPredmet);
		return new ResponseEntity<>(noviPredmet, HttpStatus.OK);
	}

	// Izmena predmeta
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/{idPredmet}/", method = RequestMethod.PUT)
	public ResponseEntity<?> izmenaPredmeta(@Valid @PathVariable Integer idPredmet,
			@RequestBody PredmetEntity noviPredmet, BindingResult result) {

		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		
		if(predmetRepository.findById(idPredmet).isPresent() == false){
			return new ResponseEntity<RESTError>(new RESTError(2, "Id predmeta ne postoji."),
					HttpStatus.NOT_FOUND);
		}
		
		PredmetEntity predmet = predmetRepository.findById(idPredmet).get();
		
		predmet.setNedeljniFondCasova(noviPredmet.getNedeljniFondCasova());
		
		predmetRepository.save(predmet);
		return new ResponseEntity<>(predmet, HttpStatus.OK);
		
	}
	
	// Brisanje predmeta, samo ako ne postoji u ni jednom razredu
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/{id_predmet}/", method = RequestMethod.DELETE)
	public ResponseEntity<?> brisanjePredmeta(@PathVariable(value = "id_predmet") Integer id_predmet) {

		if(predmetRepository.findById(id_predmet).isPresent() == false){
			return new ResponseEntity<RESTError>(new RESTError(2, "Id predmeta ne postoji."),
					HttpStatus.NOT_FOUND);
		}
		
		PredmetEntity predmet = predmetRepository.findById(id_predmet).get();
		
		if(!predmet.getNastavnikOdeljenjePredmet().isEmpty()) {
			return new ResponseEntity<RESTError>(
					new RESTError(4, "Brisanje nije dozvoljeno jer je predmet aktivan u odeljenju."),
					HttpStatus.BAD_REQUEST);
		}
		
		predmetRepository.deleteById(id_predmet);
		return new ResponseEntity<>(predmet, HttpStatus.OK);
		
	}
	//predmeti po odeljenju
	@Secured("ROLE_UCENIKA")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> getAllpredmete(Principal principal) {
		
		String username = principal.getName();
		UcenikEntity ucenik = ucenikRepository.findByKorisnickoIme(username);
		List<PredmetEntity> predmeti = new ArrayList<>();
		List<Nastavnik_Odeljenje_PredmetEntity> kombinacije = grupniRepository.findByOdeljenje_IdOdeljenje(ucenik.getOdeljenje().getIdOdeljenje());
		for(Nastavnik_Odeljenje_PredmetEntity kombinaciju: kombinacije) {
			PredmetEntity predmet = predmetRepository.findById(kombinaciju.getPredmet().getIdPredmet()).get();
			predmeti.add(predmet);
		}
		
		return new ResponseEntity<>(predmeti, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/admin/", method = RequestMethod.GET)
	public ResponseEntity<?> getAllpredmeteA() {
		
		return new ResponseEntity<>(predmetRepository.findAll(), HttpStatus.OK);
	}
	
	//predmeti po odeljenju sa prosledjivanjem ucenika
	@Secured("ROLE_RODITELJA")
	@RequestMapping(value = "/ucenik/{idUcenik}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllpredmeteUcenik(@PathVariable Integer idUcenik) {
		
		if(ucenikRepository.findById(idUcenik).isPresent() == false){
			return new ResponseEntity<RESTError>(new RESTError(2, "Id ucenika ne postoji."),
					HttpStatus.NOT_FOUND);
		}
		UcenikEntity ucenik = ucenikRepository.findById(idUcenik).get();
		
		List<PredmetEntity> predmeti = new ArrayList<>();
		List<Nastavnik_Odeljenje_PredmetEntity> kombinacije = grupniRepository.findByOdeljenje_IdOdeljenje(ucenik.getOdeljenje().getIdOdeljenje());
		for(Nastavnik_Odeljenje_PredmetEntity kombinaciju: kombinacije) {
			PredmetEntity predmet = predmetRepository.findById(kombinaciju.getPredmet().getIdPredmet()).get();
			predmeti.add(predmet);
		}
		
		return new ResponseEntity<>(predmeti, HttpStatus.OK);
	}
	//za front
	@Secured("ROLE_UCENIKA")
	@RequestMapping(value = "/predmet/{idPredmet}", method = RequestMethod.GET)
	public ResponseEntity<?> getPredmet(@PathVariable Integer idPredmet, Principal principal) {
		if (predmetRepository.findById(idPredmet).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Predmet ne postoji u bazi."),
					HttpStatus.NOT_FOUND);
		}
		
		String username = principal.getName();
		UcenikEntity ucenik = ucenikRepository.findByKorisnickoIme(username);
		Nastavnik_Odeljenje_PredmetEntity kombinacija = grupniRepository.findByOdeljenje_IdOdeljenjeAndPredmet_IdPredmet(ucenik.getOdeljenje().getIdOdeljenje(), idPredmet);
		
		PredmetEntity predmet = predmetRepository.findById(kombinacija.getPredmet().getIdPredmet()).get();
		
		
		return new ResponseEntity<>(predmet, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/predmet/admin/{idPredmet}/", method = RequestMethod.GET)
	public ResponseEntity<?> getPredmetA(@PathVariable Integer idPredmet) {
		if (predmetRepository.findById(idPredmet).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Predmet ne postoji u bazi."),
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(predmetRepository.findById(idPredmet), HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/predmet/", method = RequestMethod.GET)
	public ResponseEntity<?> getPredmete() {	
		return new ResponseEntity<>(predmetRepository.findAll(), HttpStatus.OK);
	}
	
	@Secured("ROLE_RODITELJA")
	@RequestMapping(value = "/roditelj/predmet/{idPredmet}", method = RequestMethod.GET)
	public ResponseEntity<?> getPredmetR(@PathVariable Integer idPredmet, Principal principal) {
		if (predmetRepository.findById(idPredmet).isPresent() == false) {
			return new ResponseEntity<RESTError>(new RESTError(2, "Predmet ne postoji u bazi."),
					HttpStatus.NOT_FOUND);
		}
		
		String username = principal.getName();
		RoditeljEntity roditelj = roditeljRepository.findByKorisnickoIme(username);
		List<UcenikEntity> ucenici = ucenikRepository.findByRoditelj_IdRoditelj(roditelj.getIdRoditelj());
		
		Nastavnik_Odeljenje_PredmetEntity kombinacija = grupniRepository.findByOdeljenje_IdOdeljenjeAndPredmet_IdPredmet(ucenici.get(0).getOdeljenje().getIdOdeljenje(), idPredmet);
		
		PredmetEntity predmet = predmetRepository.findById(kombinacija.getPredmet().getIdPredmet()).get();
		
		
		return new ResponseEntity<>(predmet, HttpStatus.OK);
	}
	
	
	// TODO Pregled svih predmeta jednog razreda (moze i ne mora)
	

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
	}
}
