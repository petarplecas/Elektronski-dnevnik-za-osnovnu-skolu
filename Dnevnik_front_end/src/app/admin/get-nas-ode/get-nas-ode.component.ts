import { Component, OnInit } from '@angular/core';
import { Nas_Ode_pre } from '../../models/Nas_Ode_Pre';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-get-nas-ode',
  templateUrl: './get-nas-ode.component.html',
  styleUrls: ['./get-nas-ode.component.css']
})
export class GetNasOdeComponent implements OnInit {

  veze: Nas_Ode_pre[]
  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.getNasOde();
  }

  getNasOde(){
    this.adminService.getNasOde()
      .subscribe(veze => this.veze = veze);
  }

  deleteNasOde(veze: Nas_Ode_pre): void {
    this.veze = this.veze.filter(h => h!== veze);
    this.adminService.deleteNasOde(veze.idNop).subscribe();
  }
}
