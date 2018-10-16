import { Component, OnInit } from '@angular/core';
import { NazivPredmeta } from '../../models/NazivPredmeta';
import { Router } from '@angular/router';
import { AdminService } from '../../services/admin.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-add-naziv-predmeta',
  templateUrl: './add-naziv-predmeta.component.html',
  styleUrls: ['./add-naziv-predmeta.component.css']
})
export class AddNazivPredmetaComponent implements OnInit {

  naziv: NazivPredmeta;

  constructor(private router:Router,
              private adminService: AdminService,
              private location: Location) {
                this.naziv = new NazivPredmeta();
              }

  ngOnInit() {
  }

  addNazivPredmeta(naziv:string) {
    this.naziv.nazivPredmeta = naziv;

    this.adminService.addNazivPredmeta(this.naziv)
      .subscribe((naziv: NazivPredmeta) => {
        alert('Naziv predmeta je uspešno dodat.');
        this.router.navigate(['/admin/nazivPredmeta']);
      });
  }

  goBack() {
    this.location.back();
  }

}
