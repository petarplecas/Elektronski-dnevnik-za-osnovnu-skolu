import { Component, OnInit } from '@angular/core';
import { Ocena } from '../../models/Ocena';
import { UcenikService } from '../../services/ucenik.service';
import { ActivatedRoute } from '@angular/router';
import { Predmet } from '../../models/Predmet';


@Component({
  selector: 'app-ocene',
  templateUrl: './ocene.component.html',
  styleUrls: ['./ocene.component.css']
})
export class OceneComponent implements OnInit {

  oceneI: Ocena[];
  oceneII: Ocena[];
  pol: Ocena;
  kraj: Ocena;
  predmet: Predmet;
  constructor(private ucenikService: UcenikService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    // const id = +this.route.snapshot.paramMap.get('id');
    // this.ucenikService.getPredmet(id).subscribe(a => this.predmet = a);
    this.getOceneI();
    this.getOceneII();
    this.getPolugodiste();
    this.getZakljucena();
  }
  idPredmet = +this.route.snapshot.paramMap.get('id');

  getOceneI(): void {
    this.ucenikService.getOceneI(this.idPredmet)
    .subscribe(oceneI => this.oceneI = oceneI);
  }
  getOceneII(): void {
    this.ucenikService.getOceneII(this.idPredmet)
    .subscribe(oceneII => this.oceneII = oceneII);
  }

  getPolugodiste(): void {
    this.ucenikService.getPolugodiste(this.idPredmet)
    .subscribe(pol => this.pol = pol);
  }

  getZakljucena(): void {
    this.ucenikService.getZakljucena(this.idPredmet)
    .subscribe(kraj => this.kraj = kraj);
  }

}
