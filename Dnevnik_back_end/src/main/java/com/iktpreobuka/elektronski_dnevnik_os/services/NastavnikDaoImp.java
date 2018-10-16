package com.iktpreobuka.elektronski_dnevnik_os.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik_os.entities.NastavnikEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.OcenaEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.NastavnikRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.OcenaRepository;

@Service
public class NastavnikDaoImp implements NastavnikDao {

	@Autowired
	public NastavnikRepository nastavnikRepository;
	@Autowired
	public OcenaRepository ocenaRepository;
	@PersistenceContext
	EntityManager em;

	@Override
	public List<OcenaEntity> vracanjeSvihOcenaNastavnika(String korisnickoIme) {

		NastavnikEntity nastavnik = nastavnikRepository.findByKorisnickoIme(korisnickoIme);
		List<OcenaEntity> oceneNastavnika = new ArrayList<>();
		List<OcenaEntity> ocene = (List<OcenaEntity>) ocenaRepository.findAll();

		for (OcenaEntity ocena : ocene) {
			if (ocena.getNastavnik().equals(nastavnik.getIme() + " " + nastavnik.getPrezime())) {
				oceneNastavnika.add(ocena);
			}
		}
		return oceneNastavnika;
	}
	
	@Override
	public List<UcenikEntity> vracanjeSvihOcenjenihUcenika(String korisnickoIme) {

		NastavnikEntity nastavnik = nastavnikRepository.findByKorisnickoIme(korisnickoIme);
		List<UcenikEntity> ocenjeniUcenici = new ArrayList<>();
		List<OcenaEntity> ocene = (List<OcenaEntity>) ocenaRepository.findAll();

		for (OcenaEntity ocena : ocene) {
			if (ocena.getNastavnik().equals(nastavnik.getIme() + " " + nastavnik.getPrezime())) {
				
				ocenjeniUcenici.add(ocena.getUcenik());
			}
		}
		
		return ocenjeniUcenici.stream()
			     .distinct()
			     .collect(Collectors.toList());
			
	}

}
