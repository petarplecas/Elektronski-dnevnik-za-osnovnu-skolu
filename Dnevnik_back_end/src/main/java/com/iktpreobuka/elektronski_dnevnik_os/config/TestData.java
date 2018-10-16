package com.iktpreobuka.elektronski_dnevnik_os.config;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.iktpreobuka.elektronski_dnevnik_os.entities.AdminEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.NastavnikEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.Nastavnik_Odeljenje_PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.NazivPredmetaEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.OcenaEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.OdeljenjeEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.PredmetEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.Razred_SkolskaGodinaEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.RoditeljEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.UcenikEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.UlogaEntity;
import com.iktpreobuka.elektronski_dnevnik_os.entities.enumerations.ETipOcene;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.AdminRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.NastavnikRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.Nastavnik_Odeljenje_PredmetRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.NazivPredmetaRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.OcenaRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.OdeljenjeRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.PredmetRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.Razred_SkolskaGodinaRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.RoditeljRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.UcenikRepository;
import com.iktpreobuka.elektronski_dnevnik_os.repositories.UlogaRepository;

@Component
public class TestData {

	@Autowired
	public AdminRepository adminRepository;

	@Autowired
	public Nastavnik_Odeljenje_PredmetRepository nasOdePreRepository;

	@Autowired
	public NastavnikRepository nastavnikRepository;

	@Autowired
	public NazivPredmetaRepository nazivPredmetaRepository;

	@Autowired
	public OcenaRepository ocenaRepository;

	@Autowired
	public OdeljenjeRepository odeljenjeRepository;

	@Autowired
	public PredmetRepository predmetRepository;

	@Autowired
	public Razred_SkolskaGodinaRepository razred_SkolskaGodinaRepository;

	@Autowired
	public RoditeljRepository roditeljRepository;

	@Autowired
	public UcenikRepository ucenikRepository;

	@Autowired
	public UlogaRepository ulogaRepository;

	@Bean
	PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}

	@PostConstruct
	public void init() {

		UlogaEntity u = new UlogaEntity(1, "ROLE_ADMINA");
		ulogaRepository.save(u);

		u = new UlogaEntity(2, "ROLE_UCENIKA");
		ulogaRepository.save(u);

		u = new UlogaEntity(3, "ROLE_NASTAVNIKA");
		ulogaRepository.save(u);

		u = new UlogaEntity(4, "ROLE_RODITELJA");
		ulogaRepository.save(u);

		AdminEntity a = new AdminEntity(5, "AdminIme", "AdminPrezime", "admin", getEncoder().encode("admin"),
				ulogaRepository.findById(1).get());
		adminRepository.save(a);

		Razred_SkolskaGodinaEntity r = new Razred_SkolskaGodinaEntity(6, "1", "2017/18");
		razred_SkolskaGodinaRepository.save(r);

		r = new Razred_SkolskaGodinaEntity(7, "2", "2017/18");
		razred_SkolskaGodinaRepository.save(r);

		r = new Razred_SkolskaGodinaEntity(8, "3", "2017/18");
		razred_SkolskaGodinaRepository.save(r);

		r = new Razred_SkolskaGodinaEntity(9, "4", "2017/18");
		razred_SkolskaGodinaRepository.save(r);

		r = new Razred_SkolskaGodinaEntity(10, "5", "2017/18");
		razred_SkolskaGodinaRepository.save(r);

		r = new Razred_SkolskaGodinaEntity(11, "6", "2017/18");
		razred_SkolskaGodinaRepository.save(r);

		r = new Razred_SkolskaGodinaEntity(12, "7", "2017/18");
		razred_SkolskaGodinaRepository.save(r);

		r = new Razred_SkolskaGodinaEntity(13, "8", "2017/18");
		razred_SkolskaGodinaRepository.save(r);

		OdeljenjeEntity o = new OdeljenjeEntity(14, 1, razred_SkolskaGodinaRepository.findById(10).get());
		odeljenjeRepository.save(o);

		o = new OdeljenjeEntity(15, 2, razred_SkolskaGodinaRepository.findById(10).get());
		odeljenjeRepository.save(o);

		o = new OdeljenjeEntity(16, 1, razred_SkolskaGodinaRepository.findById(11).get());
		odeljenjeRepository.save(o);

		o = new OdeljenjeEntity(17, 2, razred_SkolskaGodinaRepository.findById(11).get());
		odeljenjeRepository.save(o);

		o = new OdeljenjeEntity(18, 1, razred_SkolskaGodinaRepository.findById(12).get());
		odeljenjeRepository.save(o);

		o = new OdeljenjeEntity(19, 2, razred_SkolskaGodinaRepository.findById(12).get());
		odeljenjeRepository.save(o);

		o = new OdeljenjeEntity(20, 1, razred_SkolskaGodinaRepository.findById(13).get());
		odeljenjeRepository.save(o);

		o = new OdeljenjeEntity(21, 2, razred_SkolskaGodinaRepository.findById(13).get());
		odeljenjeRepository.save(o);

		NazivPredmetaEntity n = new NazivPredmetaEntity(22, "Srpski jezik");
		nazivPredmetaRepository.save(n);

		n = new NazivPredmetaEntity(23, "Matematika");
		nazivPredmetaRepository.save(n);

		n = new NazivPredmetaEntity(24, "Muzičko");
		nazivPredmetaRepository.save(n);

		n = new NazivPredmetaEntity(25, "Fizičko");
		nazivPredmetaRepository.save(n);

		n = new NazivPredmetaEntity(26, "Likovno");
		nazivPredmetaRepository.save(n);

		n = new NazivPredmetaEntity(27, "Fizika");
		nazivPredmetaRepository.save(n);

		n = new NazivPredmetaEntity(28, "Hemija");
		nazivPredmetaRepository.save(n);

		n = new NazivPredmetaEntity(29, "Biologija");
		nazivPredmetaRepository.save(n);

		n = new NazivPredmetaEntity(30, "Geografija");
		nazivPredmetaRepository.save(n);

		n = new NazivPredmetaEntity(31, "Istorija");
		nazivPredmetaRepository.save(n);

		n = new NazivPredmetaEntity(32, "Informatika");
		nazivPredmetaRepository.save(n);

		PredmetEntity p = new PredmetEntity(33, 5, razred_SkolskaGodinaRepository.findById(13).get(),
				nazivPredmetaRepository.findById(22).get());
		predmetRepository.save(p);

		p = new PredmetEntity(34, 5, razred_SkolskaGodinaRepository.findById(12).get(),
				nazivPredmetaRepository.findById(22).get());
		predmetRepository.save(p);

		p = new PredmetEntity(35, 5, razred_SkolskaGodinaRepository.findById(11).get(),
				nazivPredmetaRepository.findById(22).get());
		predmetRepository.save(p);

		p = new PredmetEntity(36, 5, razred_SkolskaGodinaRepository.findById(10).get(),
				nazivPredmetaRepository.findById(22).get());
		predmetRepository.save(p);

		p = new PredmetEntity(37, 5, razred_SkolskaGodinaRepository.findById(13).get(),
				nazivPredmetaRepository.findById(23).get());
		predmetRepository.save(p);

		p = new PredmetEntity(38, 5, razred_SkolskaGodinaRepository.findById(12).get(),
				nazivPredmetaRepository.findById(23).get());
		predmetRepository.save(p);

		p = new PredmetEntity(39, 5, razred_SkolskaGodinaRepository.findById(11).get(),
				nazivPredmetaRepository.findById(23).get());
		predmetRepository.save(p);

		p = new PredmetEntity(40, 5, razred_SkolskaGodinaRepository.findById(10).get(),
				nazivPredmetaRepository.findById(23).get());
		predmetRepository.save(p);

		p = new PredmetEntity(41, 5, razred_SkolskaGodinaRepository.findById(13).get(),
				nazivPredmetaRepository.findById(24).get());
		predmetRepository.save(p);

		p = new PredmetEntity(42, 5, razred_SkolskaGodinaRepository.findById(12).get(),
				nazivPredmetaRepository.findById(24).get());
		predmetRepository.save(p);

		p = new PredmetEntity(43, 5, razred_SkolskaGodinaRepository.findById(11).get(),
				nazivPredmetaRepository.findById(24).get());
		predmetRepository.save(p);

		p = new PredmetEntity(44, 5, razred_SkolskaGodinaRepository.findById(10).get(),
				nazivPredmetaRepository.findById(24).get());
		predmetRepository.save(p);

		p = new PredmetEntity(45, 5, razred_SkolskaGodinaRepository.findById(13).get(),
				nazivPredmetaRepository.findById(25).get());
		predmetRepository.save(p);

		p = new PredmetEntity(46, 5, razred_SkolskaGodinaRepository.findById(12).get(),
				nazivPredmetaRepository.findById(25).get());
		predmetRepository.save(p);

		p = new PredmetEntity(47, 5, razred_SkolskaGodinaRepository.findById(11).get(),
				nazivPredmetaRepository.findById(25).get());
		predmetRepository.save(p);

		p = new PredmetEntity(48, 5, razred_SkolskaGodinaRepository.findById(10).get(),
				nazivPredmetaRepository.findById(25).get());
		predmetRepository.save(p);

		p = new PredmetEntity(49, 5, razred_SkolskaGodinaRepository.findById(11).get(),
				nazivPredmetaRepository.findById(26).get());
		predmetRepository.save(p);

		p = new PredmetEntity(50, 5, razred_SkolskaGodinaRepository.findById(10).get(),
				nazivPredmetaRepository.findById(26).get());
		predmetRepository.save(p);

		p = new PredmetEntity(51, 5, razred_SkolskaGodinaRepository.findById(13).get(),
				nazivPredmetaRepository.findById(27).get());
		predmetRepository.save(p);

		p = new PredmetEntity(52, 5, razred_SkolskaGodinaRepository.findById(12).get(),
				nazivPredmetaRepository.findById(27).get());
		predmetRepository.save(p);

		p = new PredmetEntity(53, 5, razred_SkolskaGodinaRepository.findById(13).get(),
				nazivPredmetaRepository.findById(28).get());
		predmetRepository.save(p);

		p = new PredmetEntity(54, 5, razred_SkolskaGodinaRepository.findById(12).get(),
				nazivPredmetaRepository.findById(28).get());
		predmetRepository.save(p);

		p = new PredmetEntity(55, 5, razred_SkolskaGodinaRepository.findById(13).get(),
				nazivPredmetaRepository.findById(29).get());
		predmetRepository.save(p);

		p = new PredmetEntity(56, 5, razred_SkolskaGodinaRepository.findById(12).get(),
				nazivPredmetaRepository.findById(29).get());
		predmetRepository.save(p);

		p = new PredmetEntity(57, 5, razred_SkolskaGodinaRepository.findById(11).get(),
				nazivPredmetaRepository.findById(29).get());
		predmetRepository.save(p);

		p = new PredmetEntity(58, 5, razred_SkolskaGodinaRepository.findById(10).get(),
				nazivPredmetaRepository.findById(29).get());
		predmetRepository.save(p);

		p = new PredmetEntity(59, 5, razred_SkolskaGodinaRepository.findById(13).get(),
				nazivPredmetaRepository.findById(30).get());
		predmetRepository.save(p);

		p = new PredmetEntity(60, 5, razred_SkolskaGodinaRepository.findById(12).get(),
				nazivPredmetaRepository.findById(30).get());
		predmetRepository.save(p);

		p = new PredmetEntity(61, 5, razred_SkolskaGodinaRepository.findById(11).get(),
				nazivPredmetaRepository.findById(30).get());
		predmetRepository.save(p);

		p = new PredmetEntity(62, 5, razred_SkolskaGodinaRepository.findById(10).get(),
				nazivPredmetaRepository.findById(30).get());
		predmetRepository.save(p);

		p = new PredmetEntity(63, 5, razred_SkolskaGodinaRepository.findById(13).get(),
				nazivPredmetaRepository.findById(31).get());
		predmetRepository.save(p);

		p = new PredmetEntity(64, 5, razred_SkolskaGodinaRepository.findById(12).get(),
				nazivPredmetaRepository.findById(31).get());
		predmetRepository.save(p);

		p = new PredmetEntity(65, 5, razred_SkolskaGodinaRepository.findById(11).get(),
				nazivPredmetaRepository.findById(31).get());
		predmetRepository.save(p);

		p = new PredmetEntity(66, 5, razred_SkolskaGodinaRepository.findById(10).get(),
				nazivPredmetaRepository.findById(31).get());
		predmetRepository.save(p);

		p = new PredmetEntity(67, 5, razred_SkolskaGodinaRepository.findById(13).get(),
				nazivPredmetaRepository.findById(32).get());
		predmetRepository.save(p);

		p = new PredmetEntity(68, 5, razred_SkolskaGodinaRepository.findById(12).get(),
				nazivPredmetaRepository.findById(32).get());
		predmetRepository.save(p);

		p = new PredmetEntity(69, 5, razred_SkolskaGodinaRepository.findById(11).get(),
				nazivPredmetaRepository.findById(32).get());
		predmetRepository.save(p);

		p = new PredmetEntity(70, 5, razred_SkolskaGodinaRepository.findById(10).get(),
				nazivPredmetaRepository.findById(32).get());
		predmetRepository.save(p);

		NastavnikEntity na = new NastavnikEntity(71, "Skocko", "Skockic", "skocko", getEncoder().encode("skocko"),
				ulogaRepository.findById(3).get());
		nastavnikRepository.save(na);

		na = new NastavnikEntity(72, "Marica", "Maricic", "marica", getEncoder().encode("marica"),
				ulogaRepository.findById(3).get());
		nastavnikRepository.save(na);

		na = new NastavnikEntity(73, "Snezana", "Snezanic", "snezana", getEncoder().encode("snezana"),
				ulogaRepository.findById(3).get());
		nastavnikRepository.save(na);

		na = new NastavnikEntity(74, "Vlado", "Vladimirovic", "vlado", getEncoder().encode("vlado"),
				ulogaRepository.findById(3).get());
		nastavnikRepository.save(na);

		na = new NastavnikEntity(75, "Jasmina", "Jasminic", "jasmina", getEncoder().encode("jasmina"),
				ulogaRepository.findById(3).get());
		nastavnikRepository.save(na);

		na = new NastavnikEntity(76, "Milja", "Miljacic", "milja", getEncoder().encode("milja"),
				ulogaRepository.findById(3).get());
		nastavnikRepository.save(na);

		na = new NastavnikEntity(77, "Vesna", "Vesnic", "vesna", getEncoder().encode("vesna"),
				ulogaRepository.findById(3).get());
		nastavnikRepository.save(na);

		Nastavnik_Odeljenje_PredmetEntity nop = new Nastavnik_Odeljenje_PredmetEntity(78,
				nastavnikRepository.findById(71).get(), odeljenjeRepository.findById(20).get(),
				predmetRepository.findById(33).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(79, nastavnikRepository.findById(71).get(),
				odeljenjeRepository.findById(18).get(), predmetRepository.findById(34).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(80, nastavnikRepository.findById(71).get(),
				odeljenjeRepository.findById(16).get(), predmetRepository.findById(35).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(81, nastavnikRepository.findById(71).get(),
				odeljenjeRepository.findById(14).get(), predmetRepository.findById(36).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(82, nastavnikRepository.findById(72).get(),
				odeljenjeRepository.findById(21).get(), predmetRepository.findById(33).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(83, nastavnikRepository.findById(72).get(),
				odeljenjeRepository.findById(19).get(), predmetRepository.findById(34).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(84, nastavnikRepository.findById(72).get(),
				odeljenjeRepository.findById(17).get(), predmetRepository.findById(35).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(85, nastavnikRepository.findById(72).get(),
				odeljenjeRepository.findById(15).get(), predmetRepository.findById(36).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(86, nastavnikRepository.findById(73).get(),
				odeljenjeRepository.findById(20).get(), predmetRepository.findById(55).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(87, nastavnikRepository.findById(73).get(),
				odeljenjeRepository.findById(18).get(), predmetRepository.findById(56).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(88, nastavnikRepository.findById(73).get(),
				odeljenjeRepository.findById(19).get(), predmetRepository.findById(56).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(89, nastavnikRepository.findById(73).get(),
				odeljenjeRepository.findById(21).get(), predmetRepository.findById(55).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(90, nastavnikRepository.findById(73).get(),
				odeljenjeRepository.findById(16).get(), predmetRepository.findById(57).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(91, nastavnikRepository.findById(73).get(),
				odeljenjeRepository.findById(17).get(), predmetRepository.findById(57).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(92, nastavnikRepository.findById(73).get(),
				odeljenjeRepository.findById(14).get(), predmetRepository.findById(58).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(93, nastavnikRepository.findById(73).get(),
				odeljenjeRepository.findById(15).get(), predmetRepository.findById(58).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(94, nastavnikRepository.findById(73).get(),
				odeljenjeRepository.findById(20).get(), predmetRepository.findById(53).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(95, nastavnikRepository.findById(73).get(),
				odeljenjeRepository.findById(21).get(), predmetRepository.findById(53).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(96, nastavnikRepository.findById(73).get(),
				odeljenjeRepository.findById(18).get(), predmetRepository.findById(54).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(97, nastavnikRepository.findById(73).get(),
				odeljenjeRepository.findById(19).get(), predmetRepository.findById(54).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(98, nastavnikRepository.findById(74).get(),
				odeljenjeRepository.findById(20).get(), predmetRepository.findById(37).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(99, nastavnikRepository.findById(74).get(),
				odeljenjeRepository.findById(21).get(), predmetRepository.findById(37).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(100, nastavnikRepository.findById(74).get(),
				odeljenjeRepository.findById(18).get(), predmetRepository.findById(38).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(101, nastavnikRepository.findById(74).get(),
				odeljenjeRepository.findById(19).get(), predmetRepository.findById(38).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(102, nastavnikRepository.findById(74).get(),
				odeljenjeRepository.findById(16).get(), predmetRepository.findById(39).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(103, nastavnikRepository.findById(74).get(),
				odeljenjeRepository.findById(17).get(), predmetRepository.findById(39).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(104, nastavnikRepository.findById(74).get(),
				odeljenjeRepository.findById(14).get(), predmetRepository.findById(40).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(105, nastavnikRepository.findById(74).get(),
				odeljenjeRepository.findById(15).get(), predmetRepository.findById(40).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(106, nastavnikRepository.findById(74).get(),
				odeljenjeRepository.findById(20).get(), predmetRepository.findById(51).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(107, nastavnikRepository.findById(74).get(),
				odeljenjeRepository.findById(21).get(), predmetRepository.findById(51).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(108, nastavnikRepository.findById(74).get(),
				odeljenjeRepository.findById(18).get(), predmetRepository.findById(52).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(109, nastavnikRepository.findById(74).get(),
				odeljenjeRepository.findById(19).get(), predmetRepository.findById(52).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(110, nastavnikRepository.findById(75).get(),
				odeljenjeRepository.findById(20).get(), predmetRepository.findById(67).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(111, nastavnikRepository.findById(75).get(),
				odeljenjeRepository.findById(21).get(), predmetRepository.findById(67).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(112, nastavnikRepository.findById(75).get(),
				odeljenjeRepository.findById(18).get(), predmetRepository.findById(68).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(113, nastavnikRepository.findById(75).get(),
				odeljenjeRepository.findById(19).get(), predmetRepository.findById(68).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(114, nastavnikRepository.findById(75).get(),
				odeljenjeRepository.findById(16).get(), predmetRepository.findById(69).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(115, nastavnikRepository.findById(75).get(),
				odeljenjeRepository.findById(17).get(), predmetRepository.findById(69).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(116, nastavnikRepository.findById(75).get(),
				odeljenjeRepository.findById(14).get(), predmetRepository.findById(70).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(117, nastavnikRepository.findById(75).get(),
				odeljenjeRepository.findById(15).get(), predmetRepository.findById(70).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(118, nastavnikRepository.findById(75).get(),
				odeljenjeRepository.findById(20).get(), predmetRepository.findById(45).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(119, nastavnikRepository.findById(75).get(),
				odeljenjeRepository.findById(21).get(), predmetRepository.findById(45).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(120, nastavnikRepository.findById(75).get(),
				odeljenjeRepository.findById(18).get(), predmetRepository.findById(46).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(121, nastavnikRepository.findById(75).get(),
				odeljenjeRepository.findById(19).get(), predmetRepository.findById(46).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(122, nastavnikRepository.findById(75).get(),
				odeljenjeRepository.findById(16).get(), predmetRepository.findById(47).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(123, nastavnikRepository.findById(75).get(),
				odeljenjeRepository.findById(17).get(), predmetRepository.findById(47).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(124, nastavnikRepository.findById(75).get(),
				odeljenjeRepository.findById(14).get(), predmetRepository.findById(48).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(125, nastavnikRepository.findById(75).get(),
				odeljenjeRepository.findById(15).get(), predmetRepository.findById(48).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(126, nastavnikRepository.findById(76).get(),
				odeljenjeRepository.findById(20).get(), predmetRepository.findById(63).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(127, nastavnikRepository.findById(76).get(),
				odeljenjeRepository.findById(21).get(), predmetRepository.findById(63).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(128, nastavnikRepository.findById(76).get(),
				odeljenjeRepository.findById(18).get(), predmetRepository.findById(64).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(129, nastavnikRepository.findById(76).get(),
				odeljenjeRepository.findById(19).get(), predmetRepository.findById(64).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(130, nastavnikRepository.findById(76).get(),
				odeljenjeRepository.findById(16).get(), predmetRepository.findById(65).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(131, nastavnikRepository.findById(76).get(),
				odeljenjeRepository.findById(17).get(), predmetRepository.findById(65).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(132, nastavnikRepository.findById(76).get(),
				odeljenjeRepository.findById(14).get(), predmetRepository.findById(66).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(133, nastavnikRepository.findById(76).get(),
				odeljenjeRepository.findById(15).get(), predmetRepository.findById(66).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(134, nastavnikRepository.findById(76).get(),
				odeljenjeRepository.findById(20).get(), predmetRepository.findById(59).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(135, nastavnikRepository.findById(76).get(),
				odeljenjeRepository.findById(21).get(), predmetRepository.findById(59).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(136, nastavnikRepository.findById(76).get(),
				odeljenjeRepository.findById(18).get(), predmetRepository.findById(60).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(137, nastavnikRepository.findById(76).get(),
				odeljenjeRepository.findById(19).get(), predmetRepository.findById(60).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(138, nastavnikRepository.findById(76).get(),
				odeljenjeRepository.findById(16).get(), predmetRepository.findById(61).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(139, nastavnikRepository.findById(76).get(),
				odeljenjeRepository.findById(17).get(), predmetRepository.findById(61).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(140, nastavnikRepository.findById(76).get(),
				odeljenjeRepository.findById(14).get(), predmetRepository.findById(62).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(141, nastavnikRepository.findById(76).get(),
				odeljenjeRepository.findById(15).get(), predmetRepository.findById(62).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(142, nastavnikRepository.findById(77).get(),
				odeljenjeRepository.findById(20).get(), predmetRepository.findById(41).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(143, nastavnikRepository.findById(77).get(),
				odeljenjeRepository.findById(21).get(), predmetRepository.findById(41).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(144, nastavnikRepository.findById(77).get(),
				odeljenjeRepository.findById(18).get(), predmetRepository.findById(42).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(145, nastavnikRepository.findById(77).get(),
				odeljenjeRepository.findById(19).get(), predmetRepository.findById(42).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(146, nastavnikRepository.findById(77).get(),
				odeljenjeRepository.findById(16).get(), predmetRepository.findById(43).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(147, nastavnikRepository.findById(77).get(),
				odeljenjeRepository.findById(17).get(), predmetRepository.findById(43).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(148, nastavnikRepository.findById(77).get(),
				odeljenjeRepository.findById(14).get(), predmetRepository.findById(44).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(149, nastavnikRepository.findById(77).get(),
				odeljenjeRepository.findById(15).get(), predmetRepository.findById(44).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(150, nastavnikRepository.findById(77).get(),
				odeljenjeRepository.findById(16).get(), predmetRepository.findById(49).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(151, nastavnikRepository.findById(77).get(),
				odeljenjeRepository.findById(17).get(), predmetRepository.findById(49).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(152, nastavnikRepository.findById(77).get(),
				odeljenjeRepository.findById(14).get(), predmetRepository.findById(50).get());
		nasOdePreRepository.save(nop);

		nop = new Nastavnik_Odeljenje_PredmetEntity(153, nastavnikRepository.findById(77).get(),
				odeljenjeRepository.findById(15).get(), predmetRepository.findById(50).get());
		nasOdePreRepository.save(nop);

		RoditeljEntity ro = new RoditeljEntity(154, "Dragan", "Rodic", "draganrodic@example.com", "rodicd",
				getEncoder().encode("rodicd"), ulogaRepository.findById(4).get());
		roditeljRepository.save(ro);

		ro = new RoditeljEntity(155, "Miomir", "Rajic", "miomirradjic@example.com", "rajicm",
				getEncoder().encode("rajicm"), ulogaRepository.findById(4).get());
		roditeljRepository.save(ro);

		ro = new RoditeljEntity(156, "Velinka", "Drakul", "velinkadrakul@example.com", "drakulv",
				getEncoder().encode("drakulv"), ulogaRepository.findById(4).get());
		roditeljRepository.save(ro);

		ro = new RoditeljEntity(157, "Gordana", "Kostic", "gordanakostic@example.com", "kosticg",
				getEncoder().encode("kosticg"), ulogaRepository.findById(4).get());
		roditeljRepository.save(ro);

		ro = new RoditeljEntity(158, "Goran", "Sojic", "goransojic@example.com", "sojicg",
				getEncoder().encode("sojicg"), ulogaRepository.findById(4).get());
		roditeljRepository.save(ro);

		ro = new RoditeljEntity(159, "Vladimir", "Dimitrieski", "vladimirdimitrieski@example.com", "dimitrieskiv",
				getEncoder().encode("dimitrieskiv"), ulogaRepository.findById(4).get());
		roditeljRepository.save(ro);

		ro = new RoditeljEntity(160, "Marija", "Plecas", "marijaplecas@example.com", "plecasm",
				getEncoder().encode("plecasm"), ulogaRepository.findById(4).get());
		roditeljRepository.save(ro);

		UcenikEntity uc = new UcenikEntity(161, "Nemanja", "Rodic", "rodicn", getEncoder().encode("rodicn"), odeljenjeRepository.findById(16).get(), roditeljRepository.findById(154).get(), ulogaRepository.findById(2).get());
		ucenikRepository.save(uc);
		
		uc = new UcenikEntity(162, "Djordje", "Rajic", "rajicdj", getEncoder().encode("rajicdj"), odeljenjeRepository.findById(16).get(), roditeljRepository.findById(155).get(), ulogaRepository.findById(2).get());
		ucenikRepository.save(uc);
		
		uc = new UcenikEntity(163, "Milica", "Drakul", "drakulm", getEncoder().encode("drakulm"), odeljenjeRepository.findById(18).get(), roditeljRepository.findById(156).get(), ulogaRepository.findById(2).get());
		ucenikRepository.save(uc);
		
		uc = new UcenikEntity(164, "Snezana", "Kostic", "kostics", getEncoder().encode("kostics"), odeljenjeRepository.findById(18).get(), roditeljRepository.findById(157).get(), ulogaRepository.findById(2).get());
		ucenikRepository.save(uc);
		
		uc = new UcenikEntity(165, "Mladen", "Sojic", "sojicm", getEncoder().encode("sojicm"), odeljenjeRepository.findById(21).get(), roditeljRepository.findById(158).get(), ulogaRepository.findById(2).get());
		ucenikRepository.save(uc);
		
		uc = new UcenikEntity(166, "Milan", "Dimitrieski", "dimitrieskim", getEncoder().encode("dimitrieskim"), odeljenjeRepository.findById(21).get(), roditeljRepository.findById(159).get(), ulogaRepository.findById(2).get());
		ucenikRepository.save(uc);
		
		uc = new UcenikEntity(167, "Petar", "Plecas", "plecasp", getEncoder().encode("plecasp"), odeljenjeRepository.findById(16).get(), roditeljRepository.findById(160).get(), ulogaRepository.findById(2).get());
		ucenikRepository.save(uc);
		
		uc = new UcenikEntity(168, "Milana", "Plecas", "plecasm", getEncoder().encode("plecasm"), odeljenjeRepository.findById(21).get(), roditeljRepository.findById(160).get(), ulogaRepository.findById(2).get());
		ucenikRepository.save(uc);

	}
}
