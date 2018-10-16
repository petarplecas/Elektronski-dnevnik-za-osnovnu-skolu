package com.iktpreobuka.elektronski_dnevnik_os.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik_os.entities.AdminEntity;

public interface AdminRepository extends CrudRepository<AdminEntity, Integer> {
	AdminEntity findByKorisnickoIme(String korisnickoIme);
}
