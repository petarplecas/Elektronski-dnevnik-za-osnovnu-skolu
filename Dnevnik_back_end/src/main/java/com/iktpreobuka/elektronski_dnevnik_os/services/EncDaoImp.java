package com.iktpreobuka.elektronski_dnevnik_os.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncDaoImp implements EncDao {

	public static String getPassEncoded(String pass) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.encode(pass);
	}
	
	public String cryptPassword(String sifra) {
		
		return getPassEncoded(sifra);
	}
}
