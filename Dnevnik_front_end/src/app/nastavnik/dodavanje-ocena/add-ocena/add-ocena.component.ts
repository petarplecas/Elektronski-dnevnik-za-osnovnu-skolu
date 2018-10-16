import { Component, OnInit } from '@angular/core';
import { NastavnikService } from '../../../services/nastavnik.service';
import { ActivatedRoute } from '@angular/router';
import { Predmet } from '../../../models/Predmet';
import { Location } from '@angular/common';

@Component({
  selector: 'app-add-ocena',
  templateUrl: './add-ocena.component.html',
  styleUrls: ['./add-ocena.component.css']
})
export class AddOcenaComponent implements OnInit {
  predmeti: Predmet[];
  ocenaTip ;
  visinaOcene;
  opisOcene;
  predmet;
  idOdeljenja;
  idUcenik;
  constructor(private nastavnikService: NastavnikService,
  private route: ActivatedRoute, private location: Location) { }

  ngOnInit() {
    this.idOdeljenja = +this.route.snapshot.paramMap.get('idOdeljenje');
    this.idUcenik = +this.route.snapshot.paramMap.get('idUcenik');
    this.nastavnikService.getPredmete(this.idOdeljenja)
    .subscribe((predmet) => { 
      this.predmeti = predmet
    });
  }

  addOcena(){
    const data = {
      visinaOcene: this.visinaOcene,
      opisOcene: this.opisOcene,
      ocenaTip: this.ocenaTip,
      idPredmet: this.predmet,
      idUcenik: this.idUcenik
    }

    this.nastavnikService.addOcena(data)
    .subscribe(() => {alert('Dodata ocena uceniku sa ID-em' + data.idUcenik)})
  };

  goBack() {
    this.location.back();
  }

}
