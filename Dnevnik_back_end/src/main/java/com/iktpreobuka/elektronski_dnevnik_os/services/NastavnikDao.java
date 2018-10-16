package com.iktpreobuka.elektronski_dnevnik_os.services;

import java.util.List;

import com.iktpreobuka.elektronski_dnevnik_os.entities.OcenaEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.UcenikEntity;

public interface NastavnikDao {

	public List<OcenaEntity> vracanjeSvihOcenaNastavnika(String korisnickoIme);
	public List<UcenikEntity> vracanjeSvihOcenjenihUcenika(String korisnickoIme);
	/*public List<String> vracanjeKorisnickihImena();*/
}
