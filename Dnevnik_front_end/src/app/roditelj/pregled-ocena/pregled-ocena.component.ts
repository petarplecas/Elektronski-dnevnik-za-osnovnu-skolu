import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Predmet } from '../../models/Predmet';
import { Ocena } from '../../models/Ocena';
import { RoditeljService } from '../../services/roditelj.service';

@Component({
  selector: 'app-pregled-ocena',
  templateUrl: './pregled-ocena.component.html',
  styleUrls: ['./pregled-ocena.component.css']
})
export class PregledOcenaComponent implements OnInit {
  ocene: Ocena[];
  
  constructor(private roditeljService: RoditeljService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.getOcene();
    
  }
  
  idPredmet = +this.route.snapshot.paramMap.get('id');

  getOcene(): void {
    this.roditeljService.getOcene(this.idPredmet)
    .subscribe(ocene => this.ocene = ocene)
    
  }

}
