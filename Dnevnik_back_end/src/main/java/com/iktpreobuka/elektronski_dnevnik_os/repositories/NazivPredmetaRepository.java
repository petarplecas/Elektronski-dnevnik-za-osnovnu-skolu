package com.iktpreobuka.elektronski_dnevnik_os.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik_os.entities.NazivPredmetaEntity;

public interface NazivPredmetaRepository extends CrudRepository<NazivPredmetaEntity, Integer> {
	NazivPredmetaEntity findByNazivPredmetaIgnoreCase(String nazivPredmeta);
}
