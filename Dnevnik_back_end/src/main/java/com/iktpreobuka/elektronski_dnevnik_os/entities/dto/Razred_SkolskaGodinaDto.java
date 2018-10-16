package com.iktpreobuka.elektronski_dnevnik_os.entities.dto;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Razred_SkolskaGodinaDto {
		
		@Column
		@NotNull(message = "Razred mora biti unesen.")
		@Min(value=1, message="Razred moze biti u opsegu izmedju 1 i 8.")
		@Max(value=8, message="Razred moze biti u opsegu izmedju 1 i 8.")
		private Integer razred;

		@Column
		@NotNull(message = "Skolska godina mora biti unesena.")
		@Pattern(regexp = "^20([1-9]{2})\\/([0-9]{2})$", message = "Skolska godina mora da bude unesena u formatu \"2017/18\".")  
		private String skolskaGodina;

		public Razred_SkolskaGodinaDto() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Integer getRazred() {
			return razred;
		}

		public void setRazred(Integer razred) {
			this.razred = razred;
		}

		public String getSkolskaGodina() {
			return skolskaGodina;
		}

		public void setSkolskaGodina(String skolskaGodina) {
			this.skolskaGodina = skolskaGodina;
		}
		
		
		
}
