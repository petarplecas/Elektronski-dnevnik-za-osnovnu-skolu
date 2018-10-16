import { Component, OnInit } from '@angular/core';
import { NastavnikService } from '../../services/nastavnik.service';
import { Ucenik } from '../../models/Ucenik';

@Component({
  selector: 'app-get-ucenike',
  templateUrl: './get-ucenike.component.html',
  styleUrls: ['./get-ucenike.component.css']
})
export class GetUcenikeComponent implements OnInit {

  ucenici: Ucenik[];

  constructor(private nastavnikService: NastavnikService) { }

  ngOnInit() {
    this.getPredmete();
  }


  getPredmete(): void {
    this.nastavnikService.getUcenike()
    .subscribe(ucenici => this.ucenici = ucenici);
  }
}
