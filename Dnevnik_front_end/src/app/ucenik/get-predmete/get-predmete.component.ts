import { Component, OnInit } from '@angular/core';
import { Predmet } from '../../models/Predmet';
import { UcenikService } from '../../services/ucenik.service';

@Component({
  selector: 'app-get-predmete',
  templateUrl: './get-predmete.component.html',
  styleUrls: ['./get-predmete.component.css']
})
export class GetPredmeteComponent implements OnInit {

  predmeti: Predmet[];
  constructor(private ucenikService: UcenikService) { }

  ngOnInit() {
    this.getPredmete();
  }

  getPredmete(): void {
    this.ucenikService.getPredmete()
    .subscribe(predmeti => this.predmeti = predmeti);
  }

}
