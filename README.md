# Elektronski dnevnik je Web aplikacija za potrebe osnovne škole.

Aplikacija se sastoji iz dva dela:
  * Back-end deo - Dnevnik_back_end
  * Front-end deo - Dnevnik_front_end
  
Back-end deo je rađen u Spring radnom okruženju, front-end deo je rađen u Angular CLI.

Projekat omogućava, na početku da se korisnik uloguje u sistem kroz korisničko ime i lozinku (basic authentification).
Korisnik može biti:
  - Nastavnik
  - Učenik
  - Roditelj
  - Admin
  
Ako je korisnik Nastavnik, može da vidi sve ocene svih svojih predmeta za učenike i predmete kojima predaje i da ih menja, briše ili dodaje uz automatsko slanje mejla roditelju učenika.
Ako je korisnik Učenik, može da vidi sve svoje ocene.
Ako je korisnik Roditelj, može da vidi sve ocene svih učenika vezanih za sebe.
Ako je korisnik Admin, ima pristup svim podacima u sistemu.

Pokretanje aplikacije

Kreirati MySQL šemu:
  </br>
  <code> 
    CREATE SCHEMA `db_elektronski_dnevnik` DEFAULT CHARACTER SET latin1 COLLATE latin1_general_cs ;
  </code>

Kreirati MySQL korisnika i dozvoliti mu pristup:
  </br>
  <code> 
    CREATE USER 'springuser'@'localhost' IDENTIFIED BY 'ThePassword';
  </br>
    GRANT ALL ON db_elektronski_dnevnik.* TO 'springuser'@'localhost';
  </code>

Clone git repository

Prilikom pokretanja back-end servera inicijalizovaće se početna baza podataka.

<table class="tg">
  <tr>
    <th class="tg-0pky">Korisničko ime</th>
    <th class="tg-0pky">Lozinka</th>
    <th class="tg-0pky">Uloga</th>
  </tr>
   <tr>
    <td class="tg-0pky">admin
    <td class="tg-0pky">admin
    <td class="tg-0pky">Admin
  </tr>
  <tr>
    <td class="tg-0pky">jasmina
    <td class="tg-0pky">jasmina
    <td class="tg-0pky">Nastavnik
  </tr>
  <tr>
    <td class="tg-0pky">plecasp
    <td class="tg-0pky">plecasp
    <td class="tg-0pky">Učenik
  </tr>
  <tr>
    <td class="tg-0pky">plecasr
    <td class="tg-0pky">plecasr
    <td class="tg-0pky">Roditelj
  </tr>


