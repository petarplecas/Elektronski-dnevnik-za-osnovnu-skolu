import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Nastavnik } from '../../models/Nastavnik';
import { Location } from '@angular/common';

import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-add-nastavnik',
  templateUrl: './add-nastavnik.component.html',
  styleUrls: ['./add-nastavnik.component.css']
})
export class AddNastavnikComponent implements OnInit {
  nastavnik: Nastavnik;

  constructor(private router:Router,
              private adminService: AdminService,
              private location: Location) { 
                this.nastavnik = new Nastavnik();
              }

  ngOnInit() {
  }

  addNastavnik(ime:string, prezime:string, korisnickoIme:string, lozinka:string) {
    this.nastavnik.ime = ime;
    this.nastavnik.prezime = prezime;
    this.nastavnik.korisnickoIme = korisnickoIme;
    this.nastavnik.lozinka = lozinka;

    this.adminService.addNastavnik(this.nastavnik)
      .subscribe((nastavnik: Nastavnik) => {
        alert('Nastavnik ' + nastavnik.ime + ' ' + nastavnik.prezime + ' je uspešno dodat.');
        this.router.navigate(['/admin']);
      });
  }

  goBack() {
    this.location.back();
  }
}
