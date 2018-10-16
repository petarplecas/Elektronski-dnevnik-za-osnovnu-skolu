import { Component, OnInit } from '@angular/core';
import { Roditelj } from '../../models/Roditelj';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-get-roditelje',
  templateUrl: './get-roditelje.component.html',
  styleUrls: ['./get-roditelje.component.css']
})
export class GetRoditeljeComponent implements OnInit {

  roditelji: Roditelj[];
  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.getRoditelje();
  }

  getRoditelje(): void {
    this.adminService.getRoditelje()
      .subscribe(roditelji => this.roditelji = roditelji);
  }

  deleteRoditelj(roditelj: Roditelj): void{
    this.roditelji = this.roditelji.filter(h => h!== roditelj);
    this.adminService.deleteRoditelj(roditelj.idRoditelj).subscribe();
    
     }

}