package com.iktpreobuka.elektronski_dnevnik_os.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik_os.entities.OcenaEntity;

public interface OcenaRepository extends CrudRepository<OcenaEntity, Integer> {
	List<OcenaEntity> findByUcenik_IdUcenik(Integer id);
}
