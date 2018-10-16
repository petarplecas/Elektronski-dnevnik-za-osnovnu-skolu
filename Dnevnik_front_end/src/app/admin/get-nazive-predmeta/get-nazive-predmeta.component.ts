import { Component, OnInit } from '@angular/core';
import { NazivPredmeta } from '../../models/NazivPredmeta';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-get-nazive-predmeta',
  templateUrl: './get-nazive-predmeta.component.html',
  styleUrls: ['./get-nazive-predmeta.component.css']
})
export class GetNazivePredmetaComponent implements OnInit {
  nazivi: NazivPredmeta[];

  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.getNaziviPredmeta();
  }

  getNaziviPredmeta(): void {
    this.adminService.getPredmetNazive()
    .subscribe(nazivi => this.nazivi = nazivi);
  }

  deleteNazivPredmeta(naziv: NazivPredmeta): void {
    this.nazivi = this.nazivi.filter(h => h!==naziv);
    this.adminService.deleteNazivPredmeta(naziv.idNazivPredmet).subscribe();
  }

}
