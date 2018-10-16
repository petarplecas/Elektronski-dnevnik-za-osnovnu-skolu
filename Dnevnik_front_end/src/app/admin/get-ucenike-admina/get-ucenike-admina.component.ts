import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { Ucenik } from '../../models/Ucenik';

@Component({
  selector: 'app-get-ucenike-admina',
  templateUrl: './get-ucenike-admina.component.html',
  styleUrls: ['./get-ucenike-admina.component.css']
})
export class GetUcenikeAdminaComponent implements OnInit {
ucenici: Ucenik[];
  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.getUcenike();
  }

  getUcenike(): void {
    this.adminService.getUcenike()
      .subscribe(ucenici => this.ucenici = ucenici);
  }

  deleteUcenik(ucenik: Ucenik): void{
    this.ucenici = this.ucenici.filter(h => h!== ucenik);
    this.adminService.deleteUcenik(ucenik.idUcenik).subscribe();
    
     }
}
