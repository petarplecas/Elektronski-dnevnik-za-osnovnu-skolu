package com.iktpreobuka.elektronski_dnevnik_os.controllers;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronski_dnevnik_os.controllers.util.RESTError;
import com.iktpreobuka.elektronski_dnevnik_os.entities.AdminEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.UlogaEntity;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.AdminRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.UlogaRepository;
import com.iktpreobuka.elektronski_dnevnik_os.services.EncDao;

@RestController
@RequestMapping(path = "api/v1/admin")
public class AdminController {
	
	@Autowired
	public AdminRepository adminRepository;
	@Autowired
	public UlogaRepository ulogaRepository;
	@Autowired
	public EncDao encDao;

	// Unos novog admina
	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> unosNovogNastavnika(@Valid @RequestBody AdminEntity noviAdmin,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}
		UlogaEntity uloga = ulogaRepository.findByUloga("ULOGA_ADMINA");
		AdminEntity admin = new AdminEntity();
		
		admin.setIme(noviAdmin.getIme());
		admin.setPrezime(noviAdmin.getPrezime());
		admin.setKorisnickoIme(encDao.cryptPassword(noviAdmin.getKorisnickoIme()));
		
		admin.setLozinka(noviAdmin.getLozinka());
		admin.setUloga(uloga);
		
		if(adminRepository.findByKorisnickoIme(noviAdmin.getKorisnickoIme()) != null) {
			return new ResponseEntity<RESTError>(new RESTError(1, "Korisnicko ime vec postoji."), HttpStatus.BAD_REQUEST);
		}
		
		adminRepository.save(admin);
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}
	
	
	//TODO Izmeni admina
	//TODO Obrisi admina
	
	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
	}
}
