package com.iktpreobuka.elektronski_dnevnik_os.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik_os.entities.NastavnikEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.OcenaEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.enumerations.ETipOcene;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.NastavnikRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.OcenaRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.PredmetRepository;

@Service
public class OcenaDaoImp implements OcenaDao {

	@Autowired
	public OcenaRepository ocenaRepository;
	@Autowired
	public NastavnikRepository nastavnikRepository;
	@Autowired
	public PredmetRepository predmetRepository;

	@Override
	public List<OcenaEntity> vracanjeSvihRegularnihOcenaIzIPolugodistaUcenika(Integer idNastavnik, Integer idPredmet,
			Integer idUcenik) {

		NastavnikEntity nastavnik = nastavnikRepository.findById(idNastavnik).get();
		PredmetEntity predmet = predmetRepository.findById(idPredmet).get();
		List<OcenaEntity> oceneUcenika = new ArrayList<>();
		List<OcenaEntity> ocene = (List<OcenaEntity>) ocenaRepository.findAll();

		for (OcenaEntity ocena : ocene) {
			if (ocena.getTipOcene().equals(ETipOcene.TIP_IREDOVNA)
					&& ocena.getNastavnik().equals(nastavnik.getIme() + " " + nastavnik.getPrezime())
					&& ocena.getPredmet().equals(predmet.getNazivPredmeta().getNazivPredmeta())
					&& ocena.getUcenik().getIdUcenik().equals(idUcenik)
					&& ocena.getUcenik().getOdeljenje().getRazred().getIdRsg().equals(predmet.getRazred().getIdRsg())) {
				oceneUcenika.add(ocena);
			}
		}
		return oceneUcenika;
	}

	@Override
	public List<OcenaEntity> vracanjeSvihRegularnihOcenaIzIIPolugodistaUcenika(Integer idNastavnik, Integer idPredmet,
			Integer idUcenik) {

		NastavnikEntity nastavnik = nastavnikRepository.findById(idNastavnik).get();
		PredmetEntity predmet = predmetRepository.findById(idPredmet).get();
		List<OcenaEntity> oceneUcenika = new ArrayList<>();
		List<OcenaEntity> ocene = (List<OcenaEntity>) ocenaRepository.findAll();

		for (OcenaEntity ocena : ocene) {
			if (ocena.getTipOcene().equals(ETipOcene.TIP_IIREDOVNA)
					&& ocena.getNastavnik().equals(nastavnik.getIme() + " " + nastavnik.getPrezime())
					&& ocena.getPredmet().equals(predmet.getNazivPredmeta().getNazivPredmeta())
					&& ocena.getUcenik().getIdUcenik().equals(idUcenik)
					&& ocena.getUcenik().getOdeljenje().getRazred().getIdRsg().equals(predmet.getRazred().getIdRsg())) {
				oceneUcenika.add(ocena);
			}
		}
		return oceneUcenika;
	}

	@Override
	public OcenaEntity vracanjeOceneSaPolugodistaUcenika(Integer idNastavnik, Integer idPredmet, Integer idUcenik) {

		NastavnikEntity nastavnik = nastavnikRepository.findById(idNastavnik).get();
		PredmetEntity predmet = predmetRepository.findById(idPredmet).get();
		OcenaEntity ocenaUcenika = null;
		List<OcenaEntity> ocene = (List<OcenaEntity>) ocenaRepository.findAll();

		for (OcenaEntity ocena : ocene) {
			if (ocena.getTipOcene().equals(ETipOcene.TIP_POLUGODISNA)
					&& ocena.getNastavnik().equals(nastavnik.getIme() + " " + nastavnik.getPrezime())
					&& ocena.getPredmet().equals(predmet.getNazivPredmeta().getNazivPredmeta())
					&& ocena.getUcenik().getIdUcenik().equals(idUcenik)
					&& ocena.getUcenik().getOdeljenje().getRazred().getIdRsg().equals(predmet.getRazred().getIdRsg())) {
				ocenaUcenika = ocena;
			}
		}
		return ocenaUcenika;
	}

	@Override
	public OcenaEntity vracanjeZakljucneOceneUcenika(Integer idNastavnik, Integer idPredmet, Integer idUcenik) {

		NastavnikEntity nastavnik = nastavnikRepository.findById(idNastavnik).get();
		PredmetEntity predmet = predmetRepository.findById(idPredmet).get();
		OcenaEntity ocenaUcenika = null;
		List<OcenaEntity> ocene = (List<OcenaEntity>) ocenaRepository.findAll();

		for (OcenaEntity ocena : ocene) {
			if (ocena.getTipOcene().equals(ETipOcene.TIP_ZAKLJUCNA)
					&& ocena.getNastavnik().equals(nastavnik.getIme() + " " + nastavnik.getPrezime())
					&& ocena.getPredmet().equals(predmet.getNazivPredmeta().getNazivPredmeta())
					&& ocena.getUcenik().getIdUcenik().equals(idUcenik)
					&& ocena.getUcenik().getOdeljenje().getRazred().getIdRsg().equals(predmet.getRazred().getIdRsg())) {
				ocenaUcenika = ocena;
			}
		}
		return ocenaUcenika;
	}

	@Override
	public List<OcenaEntity> vracanjeRegularnihOcenaUcenikaPoPredmetuIPolugodiste(Integer idPredmet,
			String korisnickoIme) {
		PredmetEntity predmet = predmetRepository.findById(idPredmet).get();

		List<OcenaEntity> oceneUcenika = new ArrayList<>();

		List<OcenaEntity> ocene = (List<OcenaEntity>) ocenaRepository.findAll();
		for (OcenaEntity ocena : ocene) {
			if (ocena.getUcenik().getOdeljenje().getRazred().getIdRsg().equals(predmet.getRazred().getIdRsg())
					&& ocena.getUcenik().getKorisnickoIme().equals(korisnickoIme)
					&& ocena.getTipOcene().equals(ETipOcene.TIP_IREDOVNA)
					&& predmet.getNazivPredmeta().getNazivPredmeta().equals(ocena.getPredmet())) {
				oceneUcenika.add(ocena);
			}
		}

		return oceneUcenika;
	}

	@Override
	public List<OcenaEntity> vracanjeRegularnihOcenaUcenikaPoPredmetuIIPolugodite(Integer idPredmet,
			String korisnickoIme) {
		PredmetEntity predmet = predmetRepository.findById(idPredmet).get();

		List<OcenaEntity> oceneUcenika = new ArrayList<>();

		List<OcenaEntity> ocene = (List<OcenaEntity>) ocenaRepository.findAll();
		for (OcenaEntity ocena : ocene) {
			if (ocena.getUcenik().getOdeljenje().getRazred().getIdRsg().equals(predmet.getRazred().getIdRsg())
					&& ocena.getUcenik().getKorisnickoIme().equals(korisnickoIme)
					&& ocena.getTipOcene().equals(ETipOcene.TIP_IIREDOVNA)
					&& predmet.getNazivPredmeta().getNazivPredmeta().equals(ocena.getPredmet())) {
				oceneUcenika.add(ocena);
			}
		}

		return oceneUcenika;
	}

	@Override
	public OcenaEntity vracanjePolugodisneOceneUcenikaPoPredmetu(Integer idPredmet, String korisnickoIme) {
		PredmetEntity predmet = predmetRepository.findById(idPredmet).get();
		OcenaEntity ocenaUcenika = null;
		List<OcenaEntity> ocene = (List<OcenaEntity>) ocenaRepository.findAll();
		for (OcenaEntity ocena : ocene) {
			if (ocena.getUcenik().getOdeljenje().getRazred().getIdRsg().equals(predmet.getRazred().getIdRsg())
					&& ocena.getUcenik().getKorisnickoIme().equals(korisnickoIme)
					&& ocena.getTipOcene().equals(ETipOcene.TIP_POLUGODISNA)
					&& predmet.getNazivPredmeta().getNazivPredmeta().equals(ocena.getPredmet())) {
				ocenaUcenika = ocena;
			}
		}
		return ocenaUcenika;
	}

	@Override
	public OcenaEntity vracanjeZakljucneOceneUcenikaPoPredmetu(Integer idPredmet, String korisnickoIme) {
		PredmetEntity predmet = predmetRepository.findById(idPredmet).get();
		OcenaEntity ocenaUcenika = null;
		List<OcenaEntity> ocene = (List<OcenaEntity>) ocenaRepository.findAll();
		for (OcenaEntity ocena : ocene) {
			if (ocena.getUcenik().getOdeljenje().getRazred().getIdRsg().equals(predmet.getRazred().getIdRsg())
					&& ocena.getUcenik().getKorisnickoIme().equals(korisnickoIme)
					&& ocena.getTipOcene().equals(ETipOcene.TIP_ZAKLJUCNA)
					&& predmet.getNazivPredmeta().getNazivPredmeta().equals(ocena.getPredmet())) {
				ocenaUcenika = ocena;
			}
		}
		return ocenaUcenika;
	}

	@Override
	public List<OcenaEntity> vracanjeOcenaPoPredmetuIRoditelju(Integer idPredmet, Integer idRoditelj) {
		PredmetEntity predmet = predmetRepository.findById(idPredmet).get();

		List<OcenaEntity> oceneUcenika = new ArrayList<>();

		List<OcenaEntity> ocene = (List<OcenaEntity>) ocenaRepository.findAll();
		for (OcenaEntity ocena : ocene) {
			if (ocena.getUcenik().getOdeljenje().getRazred().getIdRsg().equals(predmet.getRazred().getIdRsg())
					&& ocena.getUcenik().getRoditelj().getIdRoditelj().equals(idRoditelj)
					&& predmet.getNazivPredmeta().getNazivPredmeta().equals(ocena.getPredmet())) {
				oceneUcenika.add(ocena);
			}
		}

		return oceneUcenika;
	}

	@Override
	public List<OcenaEntity> vracanjeOcenaDecePoRoditelju(Integer idRoditelj) {

		List<OcenaEntity> oceneUcenika = new ArrayList<>();

		List<OcenaEntity> ocene = (List<OcenaEntity>) ocenaRepository.findAll();
		for (OcenaEntity ocena : ocene) {
			if (ocena.getUcenik().getRoditelj().getIdRoditelj().equals(idRoditelj)) {
				oceneUcenika.add(ocena);
			}
		}
		return oceneUcenika;
	}

	@Override
	public List<OcenaEntity> vracanjeOcenaPoUcenikuINastavniku(Integer idUcenik, Integer idNastavnik) {

		NastavnikEntity nastavnik = nastavnikRepository.findById(idNastavnik).get();

		List<OcenaEntity> oceneUcenika = new ArrayList<>();

		List<OcenaEntity> ocene = (List<OcenaEntity>) ocenaRepository.findAll();
		for (OcenaEntity ocena : ocene) {
			if (ocena.getUcenik().getIdUcenik().equals(idUcenik)
					&& ocena.getNastavnik().equals(nastavnik.getIme() + " " + nastavnik.getPrezime())) {
				oceneUcenika.add(ocena);
			}
		}

		return oceneUcenika;

	}

}
