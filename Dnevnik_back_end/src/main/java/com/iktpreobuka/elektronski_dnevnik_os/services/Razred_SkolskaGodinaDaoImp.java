package com.iktpreobuka.elektronski_dnevnik_os.services;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
@Service
public class Razred_SkolskaGodinaDaoImp implements Razred_SkolskaGodinaDao {
	@Override
	public String toRomanNumerals(Integer Int) {
	    LinkedHashMap<String, Integer> roman_numerals = new LinkedHashMap<String, Integer>();
	    roman_numerals.put("V", 5);
	    roman_numerals.put("IV", 4);
	    roman_numerals.put("I", 1);
	    String res = "";
	    for(Map.Entry<String, Integer> entry : roman_numerals.entrySet()){
	      Integer matches = Int/entry.getValue();
	      res += repeat(entry.getKey(), matches);
	      Int = Int % entry.getValue();
	    }
	    return res;
	  }
	
	  public static String repeat(String s, int n) {
	    if(s == null) {
	        return null;
	    }
	    
	    final StringBuilder sb = new StringBuilder();
	    for(int i = 0; i < n; i++) {
	        sb.append(s);
	    }
	    return sb.toString();
	  }
	
}
