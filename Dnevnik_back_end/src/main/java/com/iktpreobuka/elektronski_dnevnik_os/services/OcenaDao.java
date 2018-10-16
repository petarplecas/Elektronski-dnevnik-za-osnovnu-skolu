package com.iktpreobuka.elektronski_dnevnik_os.services;

import java.util.List;

import com.iktpreobuka.elektronski_dnevnik_os.entities.OcenaEntity;

public interface OcenaDao {
	
	public List<OcenaEntity> vracanjeSvihRegularnihOcenaIzIPolugodistaUcenika(Integer idNastavnik, Integer idPredmet, Integer idUcenik);
	public List<OcenaEntity> vracanjeSvihRegularnihOcenaIzIIPolugodistaUcenika(Integer idNastavnik, Integer idPredmet, Integer idUcenik);
	public OcenaEntity vracanjeOceneSaPolugodistaUcenika(Integer idNastavnik, Integer idPredmet, Integer idUcenik);
	public OcenaEntity vracanjeZakljucneOceneUcenika(Integer idNastavnik, Integer idPredmet, Integer idUcenik);
	public List<OcenaEntity> vracanjeRegularnihOcenaUcenikaPoPredmetuIPolugodiste(Integer idPredmet, String korisnickoIme);
	public List<OcenaEntity> vracanjeRegularnihOcenaUcenikaPoPredmetuIIPolugodite(Integer idPredmet, String korisnickoIme);
	public OcenaEntity vracanjePolugodisneOceneUcenikaPoPredmetu(Integer idPredmet, String korisnickoIme);
	public OcenaEntity vracanjeZakljucneOceneUcenikaPoPredmetu(Integer idPredmet, String korisnickoIme);
	public List<OcenaEntity> vracanjeOcenaPoPredmetuIRoditelju(Integer idPredmet, Integer idRoditelj);
	public List<OcenaEntity> vracanjeOcenaPoUcenikuINastavniku(Integer idUcenik, Integer idNastavnik);
	public List<OcenaEntity> vracanjeOcenaDecePoRoditelju(Integer idRoditelj);
}
