import { Component, OnInit } from '@angular/core';
import { NastavnikService } from '../../services/nastavnik.service';
import { ActivatedRoute } from '@angular/router';
import { Ocena } from '../../models/Ocena';

@Component({
  selector: 'app-get-ocene-ucenika',
  templateUrl: './get-ocene-ucenika.component.html',
  styleUrls: ['./get-ocene-ucenika.component.css']
})
export class GetOceneUcenikaComponent implements OnInit {

  ocene: Ocena[];
  
  constructor(private nastavnikService: NastavnikService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.getOcene();
    
  }
  
  idUcenik = +this.route.snapshot.paramMap.get('id');

  getOcene(): void {
    this.nastavnikService.getOcene(this.idUcenik)
    .subscribe(ocene => this.ocene = ocene)
  }

  deleteOcenu(ocena: Ocena): void{
    this.ocene = this.ocene.filter(h => h!== ocena);
    this.nastavnikService.deleteOcenu(ocena.idOcena).subscribe();
    
  }
}
