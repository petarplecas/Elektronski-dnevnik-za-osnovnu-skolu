import { Component, OnInit } from '@angular/core';
import { Odeljenje } from '../../models/Odeljenje';
import { Nastavnik } from '../../models/Nastavnik';
import { Predmet } from '../../models/Predmet';
import { AdminService } from '../../services/admin.service';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-add-nas-ode',
  templateUrl: './add-nas-ode.component.html',
  styleUrls: ['./add-nas-ode.component.css']
})
export class AddNasOdeComponent implements OnInit {

  nastavnici: Nastavnik[];
  odeljenja: Odeljenje[];
  predmeti: Predmet[];
  nastavnik;
  odeljenje;
  predmet;
  constructor(private adminService: AdminService,
              private location: Location,
              private router:Router) { }

  ngOnInit() {
    this.adminService.getNastavnike()
    .subscribe((nastavnici) => {this.nastavnici = nastavnici})
    this.adminService.getOdeljenja()
    .subscribe((odeljenja) => {this.odeljenja = odeljenja})
    this.adminService.getPredmete()
    .subscribe((predmeti) => {this.predmeti = predmeti})
  }

  addNasOde(){
    const data = {
      nastavnici: this.nastavnik,
      odeljenja: this.odeljenje,
      predmeti: this.predmet
    }

    this.adminService.addNasOde(data)
      .subscribe(() => {
        this.router.navigate(['/admin/nasOdePre/']);
      });
  }

  goBack() {
    this.location.back();
  }

}
