package com.iktpreobuka.elektronski_dnevnik_os.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik_os.entities.UcenikEntity;

public interface UcenikRepository extends CrudRepository<UcenikEntity, Integer> {
	
	UcenikEntity findByKorisnickoIme(String korisnickoIme);
	List<UcenikEntity> findByRoditelj_IdRoditelj(Integer id);
	List<UcenikEntity> findByOdeljenje_IdOdeljenje(Integer id);
	
}
