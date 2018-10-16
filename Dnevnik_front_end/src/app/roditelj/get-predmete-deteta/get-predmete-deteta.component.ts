import { Component, OnInit } from '@angular/core';
import { Predmet } from '../../models/Predmet';
import { RoditeljService } from '../../services/roditelj.service';
import { ActivatedRoute } from '@angular/router';
import { Ucenik } from '../../models/Ucenik';

@Component({
  selector: 'app-get-predmete-deteta',
  templateUrl: './get-predmete-deteta.component.html',
  styleUrls: ['./get-predmete-deteta.component.css']
})
export class GetPredmeteDetetaComponent implements OnInit {

  predmeti: Predmet[];
  constructor(private roditeljService: RoditeljService,
              private route: ActivatedRoute) { }

ngOnInit() {
  const id = +this.route.snapshot.paramMap.get('id');
  this.roditeljService.getPredmeteDeteta(id)
  .subscribe(a => this.predmeti = a);
  }
}

  

