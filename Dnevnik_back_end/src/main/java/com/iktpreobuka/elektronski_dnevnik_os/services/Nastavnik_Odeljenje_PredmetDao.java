package com.iktpreobuka.elektronski_dnevnik_os.services;

import org.springframework.http.ResponseEntity;

import com.iktpreobuka.elektronski_dnevnik_os.entities.Nastavnik_Odeljenje_PredmetEntity;

public interface Nastavnik_Odeljenje_PredmetDao {
	public ResponseEntity<?> proveraNastavnikaIPredmeta(Integer idNastavnik, Integer idPredmet, Integer idUcenik);
	public Nastavnik_Odeljenje_PredmetEntity nadjiNastavnikOdeljenjePredmet(Integer idNastavnik, Integer idPredmet, Integer idUcenik);
}
