package com.iktpreobuka.elektronski_dnevnik_os.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik_os.entities.OdeljenjeEntity;

public interface OdeljenjeRepository extends CrudRepository<OdeljenjeEntity, Integer> {
	
	OdeljenjeEntity findByOdeljenje(Integer odeljenje);
	OdeljenjeEntity findByRazred_IdRsg(Integer id);
}
