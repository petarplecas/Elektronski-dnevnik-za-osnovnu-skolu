import { Component, OnInit } from '@angular/core';
import { Odeljenje } from '../../models/Odeljenje';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-get-odeljenja',
  templateUrl: './get-odeljenja.component.html',
  styleUrls: ['./get-odeljenja.component.css']
})
export class GetOdeljenjaComponent implements OnInit {

  odeljenja: Odeljenje[];
  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.getOdeljenja();
  }

  getOdeljenja(): void {
    this.adminService.getOdeljenja()
      .subscribe(odeljenja => this.odeljenja = odeljenja)
  }

  deleteOdeljenje(odeljenje: Odeljenje): void {
    this.odeljenja = this.odeljenja.filter(h => h!== odeljenje);
    this.adminService.deleteOdeljenje(odeljenje.idOdeljenje).subscribe();
  }
}
