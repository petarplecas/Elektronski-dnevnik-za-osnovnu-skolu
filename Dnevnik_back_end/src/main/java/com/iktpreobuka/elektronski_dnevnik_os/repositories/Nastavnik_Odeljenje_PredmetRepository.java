package com.iktpreobuka.elektronski_dnevnik_os.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronski_dnevnik_os.entities.Nastavnik_Odeljenje_PredmetEntity;


public interface Nastavnik_Odeljenje_PredmetRepository extends CrudRepository<Nastavnik_Odeljenje_PredmetEntity, Integer> {
	List<Nastavnik_Odeljenje_PredmetEntity> findByNastavnik_IdNastavnik(Integer id);
	List<Nastavnik_Odeljenje_PredmetEntity> findByOdeljenje_IdOdeljenje(Integer id);
	List<Nastavnik_Odeljenje_PredmetEntity> findByPredmet_IdPredmet(Integer id);
	List<Nastavnik_Odeljenje_PredmetEntity> findByOdeljenje_Razred_IdRsg(Integer id);
	List<Nastavnik_Odeljenje_PredmetEntity> findByPredmet_Razred_IdRsg(Integer id);
	List<Nastavnik_Odeljenje_PredmetEntity> findByNastavnik_IdNastavnikAndPredmet_IdPredmet(Integer idNastavnik, Integer idPredmet);
	List<Nastavnik_Odeljenje_PredmetEntity> findByNastavnik_IdNastavnikAndOdeljenje_idOdeljenje(Integer idNastavnik, Integer idOdeljenje);
	Nastavnik_Odeljenje_PredmetEntity findByNastavnik_IdNastavnikAndOdeljenje_IdOdeljenjeAndPredmet_IdPredmet(Integer idNastavnik, Integer idOdeljenje, Integer idPredmet);
	Nastavnik_Odeljenje_PredmetEntity findByOdeljenje_IdOdeljenjeAndPredmet_IdPredmet(Integer idOdeljenje, Integer idPredmet);
}
