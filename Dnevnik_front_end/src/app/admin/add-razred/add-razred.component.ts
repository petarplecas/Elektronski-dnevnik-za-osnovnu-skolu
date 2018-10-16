import { Component, OnInit } from '@angular/core';
import { Razred_SkolskaGodina } from '../../models/Razred_SkolskaGodina';
import { Razred_SkolskaGodinaDTO } from '../../models/Razred_SkolskaGodinaDTO';
import { Router } from '../../../../node_modules/@angular/router';
import { AdminService } from '../../services/admin.service';
import { Location } from '../../../../node_modules/@angular/common';

@Component({
  selector: 'app-add-razred',
  templateUrl: './add-razred.component.html',
  styleUrls: ['./add-razred.component.css']
})
export class AddRazredComponent implements OnInit {
  razred: Razred_SkolskaGodinaDTO;
  
  constructor(private router:Router,
              private adminService: AdminService,
              private location: Location) { 
                this.razred = new Razred_SkolskaGodinaDTO();
              }

  ngOnInit() {
  }

  addRazred(razred:number, skolskaGodina:string) {
    this.razred.razred = razred;
    this.razred.skolskaGodina = skolskaGodina;

    this.adminService.addRazred(this.razred)
      .subscribe((razred: Razred_SkolskaGodinaDTO) => {
        alert('Razred je uspešno dodat.');
        this.router.navigate(['/admin/razred']);
      });
  }

  goBack() {
    this.location.back();
  }

}
