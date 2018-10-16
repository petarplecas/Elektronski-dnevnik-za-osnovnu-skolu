import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { Location } from '@angular/common';
import { Odeljenje } from '../../models/Odeljenje';
import { Roditelj } from '../../models/Roditelj';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-ucenik-admin',
  templateUrl: './add-ucenik-admin.component.html',
  styleUrls: ['./add-ucenik-admin.component.css']
})
export class AddUcenikAdminComponent implements OnInit {
  odeljenja: Odeljenje[];
  roditelji: Roditelj[];
  odeljenje;
  roditelj;
  ime;
  prezime;
  korisnickoIme;
  lozinka;

  constructor(private adminService: AdminService,
              private location: Location,
              private router:Router) { }

  ngOnInit() {
    this.adminService.getOdeljenja()
    .subscribe((odeljenja) => {this.odeljenja = odeljenja})
    this.adminService.getRoditelje()
    .subscribe((roditelji) => {this.roditelji = roditelji})
  }

  addUcenik(){
    const data = {
      ime: this.ime,
      prezime: this.prezime,
      korisnickoIme: this.korisnickoIme,
      lozinka: this.lozinka,
      odeljenja: this.odeljenje,
      roditelji: this.roditelj
    }

    this.adminService.addUcenik(data)
    .subscribe(() => {
      alert('Ucenik ' + data.ime + ' ' + data.prezime + ' je uspešno dodat.');
      this.router.navigate(['/admin/ucenici']);
    });
  }

  goBack() {
    this.location.back();
  }

}
