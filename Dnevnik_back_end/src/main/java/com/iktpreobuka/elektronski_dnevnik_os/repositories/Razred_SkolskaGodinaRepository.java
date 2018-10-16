package com.iktpreobuka.elektronski_dnevnik_os.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik_os.entities.Razred_SkolskaGodinaEntity;

public interface Razred_SkolskaGodinaRepository extends CrudRepository<Razred_SkolskaGodinaEntity, Integer> {
	Razred_SkolskaGodinaEntity findByRazredAndSkolskaGodina(String razred, String skolskaGodina);
}
