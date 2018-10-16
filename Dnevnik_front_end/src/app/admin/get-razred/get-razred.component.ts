import { Component, OnInit } from '@angular/core';
import { Razred_SkolskaGodina } from '../../models/Razred_SkolskaGodina';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-get-razred',
  templateUrl: './get-razred.component.html',
  styleUrls: ['./get-razred.component.css']
})
export class GetRazredComponent implements OnInit {
  razredi: Razred_SkolskaGodina[];
  
  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.getRazredi();
  }

  getRazredi(): void {
    this.adminService.getRazredi()
    .subscribe(razredi => this.razredi = razredi);
  }

  deleteRazred(razred: Razred_SkolskaGodina): void {
    this.razredi = this.razredi.filter(h => h!==razred);
    this.adminService.deleteRazred(razred.idRsg).subscribe();
  }

}
