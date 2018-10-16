import { Component, OnInit, Input } from '@angular/core';
import { Nastavnik } from '../../models/Nastavnik';
import { AdminService } from '../../services/admin.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-put-nastavnik',
  templateUrl: './put-nastavnik.component.html',
  styleUrls: ['./put-nastavnik.component.css']
})
export class PutNastavnikComponent implements OnInit {
  
  nastavnik: Nastavnik;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private location: Location,
              private adminService: AdminService) {
  }

  ngOnInit() {  
    const id = +this.route.snapshot.paramMap.get('id');
    this.adminService.getNastavnik(id).subscribe(a => this.nastavnik = a);
  }

  goBack() {
     this.location.back();
  }

  save(): void {
    this.adminService.updateNastavnik(this.nastavnik)
       .subscribe(() => this.goBack());
  }
  
  updateNastavnik() {
    this.adminService.updateNastavnik(this.nastavnik)
    .subscribe((nastavnik: Nastavnik) =>  {
      alert('Nastavnik ' + nastavnik.ime + ' ' + nastavnik.prezime + ' je uspešno izmenjen!');
      this.router.navigate(['/admin']);
    });
  }
}

  
  
