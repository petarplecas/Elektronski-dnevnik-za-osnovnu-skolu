package com.iktpreobuka.elektronski_dnevnik_os.services;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronski_dnevnik_os.entities.OcenaEntity;

@Service
public class EmailDaoImp implements EmailDao {

	@Autowired
	public JavaMailSender emailSender;

	@Override
	public void sendTemplateMessage(OcenaEntity ocena) throws Exception {
		MimeMessage mail = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		helper.setTo(ocena.getUcenik().getRoditelj().getEmail());
		helper.setSubject("Ocena vaseg deteta.");
		String text = "<html><body>" + "<table style='border:2px solid black'>"
				+ "<tr><th>Ucenik</th><th>Predmet</th><th>Nastavnik</th><th>Tip ocene</th><th>Ocena</th><th>Datum ocenjivanja</th><th>Opis ocene</th></tr>"
				+ "<tr><td>" + ocena.getUcenik().getIme() + " " + ocena.getUcenik().getPrezime() + "</td>" + "<td>"
				+ ocena.getPredmet() + "</td>" + "<td>"
				+ ocena.getNastavnik() + "</td>" + "<td>"
				+ ocena.getTipOcene() + "</td>" + "<td>" + ocena.getVisinaOcene() + "</td>" + "<td>" + ocena.getDatum()
				+ "</td>" + "<td>" + ocena.getOpisOcene() + "</td></tr></table></body></html>";
		helper.setText(text, true);
		emailSender.send(mail);

	}
}
