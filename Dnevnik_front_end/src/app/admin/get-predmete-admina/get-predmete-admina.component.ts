import { Component, OnInit } from '@angular/core';
import { Predmet } from '../../models/Predmet';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-get-predmete-admina',
  templateUrl: './get-predmete-admina.component.html',
  styleUrls: ['./get-predmete-admina.component.css']
})
export class GetPredmeteAdminaComponent implements OnInit {
  
  predmeti: Predmet[];
  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.getPredmete();
  }

  getPredmete(): void {
    this.adminService.getPredmete()
      .subscribe(predmeti => this.predmeti = predmeti);
  }

  deletePredmet(predmet: Predmet): void{
    this.predmeti = this.predmeti.filter(h => h!== predmet);
    this.adminService.deletePredmet(predmet.idPredmet).subscribe();
    
     }

}
