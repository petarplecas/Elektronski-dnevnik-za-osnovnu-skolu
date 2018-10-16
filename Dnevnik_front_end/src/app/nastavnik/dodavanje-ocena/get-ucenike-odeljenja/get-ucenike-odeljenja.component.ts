import { Component, OnInit } from '@angular/core';
import { Ucenik } from '../../../models/Ucenik';
import { NastavnikService } from '../../../services/nastavnik.service';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-get-ucenike-odeljenja',
  templateUrl: './get-ucenike-odeljenja.component.html',
  styleUrls: ['./get-ucenike-odeljenja.component.css']
})
export class GetUcenikeOdeljenjaComponent implements OnInit {

  ucenici: Ucenik[];
  constructor(private nastavnikService: NastavnikService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit() {
    this.getUcenikeOdeljenja();
  }

  idOdeljenja = +this.route.snapshot.paramMap.get('id');

  getUcenikeOdeljenja(): void {
    this.nastavnikService.getUcenikeOdeljenja(this.idOdeljenja)
    .subscribe((ucenici) => {
      this.ucenici = ucenici;
      console.log(ucenici);
      
    })
  }

  goBack() {
    this.location.back();
  }

  
}
