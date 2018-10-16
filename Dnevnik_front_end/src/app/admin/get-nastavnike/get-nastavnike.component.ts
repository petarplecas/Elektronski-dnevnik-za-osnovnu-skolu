import { Component, OnInit } from '@angular/core';
import { Nastavnik } from '../../models/Nastavnik';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-get-nastavnike',
  templateUrl: './get-nastavnike.component.html',
  styleUrls: ['./get-nastavnike.component.css']
})
export class GetNastavnikeComponent implements OnInit {

  nastavnici: Nastavnik[];
  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.getNastavnike();
  }

  getNastavnike(): void {
    this.adminService.getNastavnike()
      .subscribe(nastavnici => this.nastavnici = nastavnici);
  }

  deleteNastavnik(nastavnik: Nastavnik): void{
    this.nastavnici = this.nastavnici.filter(h => h!== nastavnik);
    this.adminService.deleteNastavnik(nastavnik.idNastavnik).subscribe();
    
     }

}
