import { Component, OnInit } from '@angular/core';
import { Roditelj } from '../../models/Roditelj';
import { Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-add-roditelj',
  templateUrl: './add-roditelj.component.html',
  styleUrls: ['./add-roditelj.component.css']
})
export class AddRoditeljComponent implements OnInit {

  roditelj: Roditelj;

  constructor(private router:Router,
              private adminService: AdminService,
              private location: Location) { 
                this.roditelj = new Roditelj();
              }

  ngOnInit() {
  }

  addRoditelj(ime:string, prezime:string, email:string, korisnickoIme:string, lozinka:string) {
    this.roditelj.ime = ime;
    this.roditelj.prezime = prezime;
    this.roditelj.email = email;
    this.roditelj.korisnickoIme = korisnickoIme;
    this.roditelj.lozinka = lozinka;

    this.adminService.addRoditelj(this.roditelj)
      .subscribe((roditelj: Roditelj) => {
        alert('Roditelj ' + roditelj.ime + ' ' + roditelj.prezime + ' je uspešno dodat.');
        this.router.navigate(['/admin']);
      });
  }

  goBack() {
    this.location.back();
  }
}
