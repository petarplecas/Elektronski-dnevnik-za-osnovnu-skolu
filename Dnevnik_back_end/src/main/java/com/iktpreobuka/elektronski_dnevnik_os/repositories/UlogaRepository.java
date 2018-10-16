package com.iktpreobuka.elektronski_dnevnik_os.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik_os.entities.UlogaEntity;

public interface UlogaRepository extends CrudRepository<UlogaEntity, Integer> {

	UlogaEntity findByUloga(String uloga);
}
