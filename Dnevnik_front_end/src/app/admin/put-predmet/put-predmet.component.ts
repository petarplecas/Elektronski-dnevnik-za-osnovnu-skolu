import { Component, OnInit } from '@angular/core';
import { Predmet } from '../../models/Predmet';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-put-predmet',
  templateUrl: './put-predmet.component.html',
  styleUrls: ['./put-predmet.component.css']
})
export class PutPredmetComponent implements OnInit {

  
  predmet: Predmet;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private location: Location,
              private adminService: AdminService) {
  }

  ngOnInit() {  
    const id = +this.route.snapshot.paramMap.get('id');
    this.adminService.getPredmet(id).subscribe(a => this.predmet = a);
  }

  goBack() {
     this.location.back();
  }

  save(): void {
    this.adminService.updatePredmet(this.predmet)
       .subscribe(() => this.goBack());
  }
  
  updatePredmet() {
    this.adminService.updatePredmet(this.predmet)
    .subscribe((predmet: Predmet) =>  {
      alert('Predmet ' + predmet.nazivPredmeta.nazivPredmeta + ' je uspešno izmenjen!');
      this.router.navigate(['/admin']);
    });
  }
}
