import { Component, OnInit } from '@angular/core';
import { NastavnikService } from '../../services/nastavnik.service';
import { Odeljenje } from '../../models/Odeljenje';



@Component({
  selector: 'app-dodavanje-ocena',
  templateUrl: './dodavanje-ocena.component.html',
  styleUrls: ['./dodavanje-ocena.component.css']
})
export class DodavanjeOcenaComponent implements OnInit {
  odeljenja: Odeljenje[];
  constructor(private nastavnikService: NastavnikService) { }

  ngOnInit() {
    this.getOdeljenja();
  }


  getOdeljenja(): void {
    this.nastavnikService.getOdeljenja()
    .subscribe(odeljenja => this.odeljenja = odeljenja);
  }
}
