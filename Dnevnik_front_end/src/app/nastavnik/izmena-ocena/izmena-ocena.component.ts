import { Component, OnInit } from '@angular/core';
import { Ocena } from '../../models/Ocena';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { NastavnikService } from '../../services/nastavnik.service';

@Component({
  selector: 'app-izmena-ocena',
  templateUrl: './izmena-ocena.component.html',
  styleUrls: ['./izmena-ocena.component.css']
})
export class IzmenaOcenaComponent implements OnInit {

  ocena: Ocena

  constructor(private route: ActivatedRoute,
    private router: Router,
    private location: Location,
    private nastavnikService: NastavnikService) { }

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.nastavnikService.getOcena(id).subscribe(a => this.ocena = a);
  }

  updateOcena() {
    this.nastavnikService.updateOcena(this.ocena)
    .subscribe((ocena: Ocena) =>  {
      alert('Ocena ' + ocena.idOcena + ' je uspešno izmenjen!');
      this.router.navigate(['/nastavnik']);
    });
  }

  save(): void {
    this.nastavnikService.updateOcena(this.ocena)
       .subscribe(() => this.goBack());
  }

  goBack() {
    this.location.back();
 }

}
