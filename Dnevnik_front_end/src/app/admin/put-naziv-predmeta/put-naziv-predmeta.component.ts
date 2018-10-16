import { Component, OnInit } from '@angular/core';
import { NazivPredmeta } from '../../models/NazivPredmeta';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-put-naziv-predmeta',
  templateUrl: './put-naziv-predmeta.component.html',
  styleUrls: ['./put-naziv-predmeta.component.css']
})
export class PutNazivPredmetaComponent implements OnInit {
  naziv: NazivPredmeta;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private location: Location,
              private adminService: AdminService) { }

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.adminService.getNazivPredmeta(id).subscribe(a => this.naziv = a);
  }

  
  goBack() {
    this.location.back();
  }

  save(): void {
    this.adminService.updateNazivPredmeta(this.naziv)
        .subscribe(() => this.goBack());
  }

  updateNazivPredmeta() {
    this.adminService.updateNazivPredmeta(this.naziv)
    .subscribe((naziv: NazivPredmeta) =>  {
      alert('Naziv predmeta je uspešno izmenjen!');
      this.router.navigate(['/admin/nazivPredmeta']);
    });
  }

}
