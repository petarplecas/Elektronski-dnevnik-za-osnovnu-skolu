package com.iktpreobuka.elektronski_dnevnik_os.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik_os.controllers.util.RESTError;
import com.iktpreobuka.elektronski_dnevnik_os.entities.Nastavnik_Odeljenje_PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.NastavnikRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.Nastavnik_Odeljenje_PredmetRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.PredmetRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.UcenikRepository;

@Service
public class Nastavnik_Odeljenje_PredmetDaoImp implements Nastavnik_Odeljenje_PredmetDao {

	@Autowired
	public Nastavnik_Odeljenje_PredmetRepository nastavnikOdeljenjePredmetRepository;
	@Autowired
	public UcenikRepository ucenikRepository;
	@Autowired
	public NastavnikRepository nastavnikRepository;
	@Autowired
	public PredmetRepository predmetRepository;

	@Override
	public ResponseEntity<?> proveraNastavnikaIPredmeta(Integer idNastavnik, Integer idPredmet, Integer idUcenik) {
		
		UcenikEntity ucenik = ucenikRepository.findById(idUcenik).get();

		List<Nastavnik_Odeljenje_PredmetEntity> nastavnikPredmet = (List<Nastavnik_Odeljenje_PredmetEntity>) nastavnikOdeljenjePredmetRepository
				.findAll();

		for (Nastavnik_Odeljenje_PredmetEntity nastavnikOdeljenjePredmetEntity : nastavnikPredmet) {

			if (nastavnikOdeljenjePredmetEntity.getNastavnik().getIdNastavnik().equals(idNastavnik)
					&& nastavnikOdeljenjePredmetEntity.getPredmet().getIdPredmet().equals(idPredmet)) {
				if (nastavnikOdeljenjePredmetEntity.getOdeljenje().getIdOdeljenje() == ucenik.getOdeljenje()
						.getIdOdeljenje()) {
					return null;
				}
				return new ResponseEntity<RESTError>(
						new RESTError(11, "Nastavnik ne predaje taj predmet tom odeljenju."),
						HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<RESTError>(new RESTError(11, "Nastavnik ne predaje taj predmet."),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<RESTError>(new RESTError(11, "Nastavnik sa tim id-em ne postoji."),
				HttpStatus.BAD_REQUEST);
		
	}
	
	@Override
	public Nastavnik_Odeljenje_PredmetEntity nadjiNastavnikOdeljenjePredmet(Integer idNastavnik, Integer idPredmet, Integer idUcenik) {
		List<Nastavnik_Odeljenje_PredmetEntity> nastavnikPredmet = (List<Nastavnik_Odeljenje_PredmetEntity>) nastavnikOdeljenjePredmetRepository.findAll();
		UcenikEntity ucenik = ucenikRepository.findById(idUcenik).get();
		Nastavnik_Odeljenje_PredmetEntity novi = new Nastavnik_Odeljenje_PredmetEntity();

		for (Nastavnik_Odeljenje_PredmetEntity nastavnikOdeljenjePredmetEntity : nastavnikPredmet) {

			if (nastavnikOdeljenjePredmetEntity.getNastavnik().getIdNastavnik().equals(idNastavnik)	&& nastavnikOdeljenjePredmetEntity.getPredmet().getIdPredmet().equals(idPredmet) && nastavnikOdeljenjePredmetEntity.getOdeljenje().getIdOdeljenje() == ucenik.getOdeljenje().getIdOdeljenje()) {
					novi.getIdNop();
			}
		}	
		return novi;
	}		
	
}
