package com.iktpreobuka.elektronski_dnevnik_os.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik_os.entities.NastavnikEntity;

public interface NastavnikRepository extends CrudRepository<NastavnikEntity, Integer> {
	
	NastavnikEntity findByKorisnickoIme(String korisnickoIme);
	
}
