import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { NazivPredmeta } from '../../models/NazivPredmeta';
import { Razred_SkolskaGodina } from '../../models/Razred_SkolskaGodina';
import { Location } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-predmet',
  templateUrl: './add-predmet.component.html',
  styleUrls: ['./add-predmet.component.css']
})
export class AddPredmetComponent implements OnInit {
  predmeti: NazivPredmeta[];
  razredi: Razred_SkolskaGodina[];
  predmet;
  razred;
  fond;

  constructor(private adminService: AdminService,
              private location: Location,
              private router:Router) { }

  ngOnInit() {
    this.adminService.getPredmetNazive()
    .subscribe((predmeti)=> {this.predmeti = predmeti}) 
    this.adminService.getRazredi()
    .subscribe((razredi) => {this.razredi = razredi})
  }

  addPredmet(){
    const data = {
      fond: this.fond,
      predmeti: this.predmet,
      razredi: this.razred
    }

    this.adminService.addPredmet(data)
    .subscribe(() => {
      alert('Predmet je uspešno dodat.');
      this.router.navigate(['/admin/predmeti']);
    });
  }

  goBack() {
    this.location.back();
 }

}
