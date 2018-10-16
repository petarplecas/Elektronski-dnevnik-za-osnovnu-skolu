package com.iktpreobuka.elektronski_dnevnik_os.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik_os.entities.RoditeljEntity;

public interface RoditeljRepository extends CrudRepository<RoditeljEntity, Integer> {
	
	RoditeljEntity findByKorisnickoIme(String korisnickoIme);
	RoditeljEntity findByEmail(String email);
}
