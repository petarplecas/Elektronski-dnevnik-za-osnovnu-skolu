package com.iktpreobuka.elektronski_dnevnik_os.services;

import com.iktpreobuka.elektronski_dnevnik_os.entities.OcenaEntity;

public interface EmailDao {
	
	void sendTemplateMessage (OcenaEntity ocena) throws Exception;
}
