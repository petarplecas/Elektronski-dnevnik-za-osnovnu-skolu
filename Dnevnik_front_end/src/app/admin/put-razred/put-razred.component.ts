import { Component, OnInit } from '@angular/core';
import { Razred_SkolskaGodina } from '../../models/Razred_SkolskaGodina';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-put-razred',
  templateUrl: './put-razred.component.html',
  styleUrls: ['./put-razred.component.css']
})
export class PutRazredComponent implements OnInit {
  razred: Razred_SkolskaGodina;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private location: Location,
              private adminService: AdminService) { }

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.adminService.getRazred(id).subscribe(a => this.razred = a);
  }

  goBack() {
    this.location.back();
  }

  save(): void {
    this.adminService.updateRazred(this.razred)
        .subscribe(() => this.goBack());
  }

  updateRazred() {
    this.adminService.updateRazred(this.razred)
    .subscribe((razred: Razred_SkolskaGodina) =>  {
      alert('Razred je uspešno izmenjen!');
      this.router.navigate(['/admin/razred']);
    });
  }

}
