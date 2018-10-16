import { Component, OnInit } from '@angular/core';
import { Razred_SkolskaGodina } from '../../models/Razred_SkolskaGodina';
import { AdminService } from '../../services/admin.service';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/operators';
import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { HttpErrorResponse } from '@angular/common/http';
import { error } from 'util';

@Component({
  selector: 'app-add-odeljenje',
  templateUrl: './add-odeljenje.component.html',
  styleUrls: ['./add-odeljenje.component.css']
})
export class AddOdeljenjeComponent implements OnInit {

  razredi: Razred_SkolskaGodina[];
  odeljenje;
  razred;
  constructor(private adminService: AdminService,
              private location: Location,
              private router:Router) { }

  ngOnInit() {
    this.adminService.getRazredi()
      .subscribe((razredi) => {this.razredi = razredi})
  }

  addOdeljenje(){
    const data = {
      odeljenje: this.odeljenje,
      razredi: this.razred
    }
    this.adminService.addOdeljenje(data)
    .subscribe(() => {
      alert()
      this.router.navigate(['/admin/odeljenja'])
    })
  }

  goBack() {
    this.location.back();
  }

}
