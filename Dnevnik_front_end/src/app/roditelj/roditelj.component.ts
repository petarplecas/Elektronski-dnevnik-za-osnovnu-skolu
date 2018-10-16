import { Component, OnInit } from '@angular/core';
import { RoditeljService } from '../services/roditelj.service';
import { Ucenik } from '../models/Ucenik';
import { ActivatedRoute } from '@angular/router';
import { Predmet } from '../models/Predmet';

@Component({
  selector: 'app-roditelj',
  templateUrl: './roditelj.component.html',
  styleUrls: ['./roditelj.component.css']
})
export class RoditeljComponent implements OnInit {
  deca: Ucenik[];
  
  predmeti: Predmet[];
  constructor(private roditeljService: RoditeljService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.getDecu();
  
  }

  getDecu(): void {
    this.roditeljService.getDecu()
    .subscribe(deca => this.deca = deca);
  }

  
  
}
