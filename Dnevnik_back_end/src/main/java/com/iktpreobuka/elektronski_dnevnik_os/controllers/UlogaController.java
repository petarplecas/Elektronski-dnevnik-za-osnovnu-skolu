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

import com.iktpreobuka.elektronski_dnevnik_os.entities.UlogaEntity;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.UlogaRepository;

@RestController
@RequestMapping(path = "api/v1/uloga")
public class UlogaController {

	@Autowired
	public UlogaRepository ulogaRepository;

	@Secured("ROLE_ADMINA")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> unosNoveUloge(@Valid @RequestBody UlogaEntity novaUloga, BindingResult result) {

		if (result.hasErrors()) {
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		}

		UlogaEntity uloga = new UlogaEntity();

		uloga.setUloga(novaUloga.getUloga());

		ulogaRepository.save(uloga);

		return new ResponseEntity<>(uloga, HttpStatus.OK);

	}

	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
	}

}
