package com.iktpreobuka.elektronski_dnevnik_os.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik_os.entities.PredmetEntity;


public interface PredmetRepository extends CrudRepository<PredmetEntity, Integer> {
	
	PredmetEntity findByNazivPredmeta_IdNazivPredmet(Integer id);
	PredmetEntity findByRazred_IdRsg(Integer id);

}
