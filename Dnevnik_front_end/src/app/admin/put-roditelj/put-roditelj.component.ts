import { Component, OnInit } from '@angular/core';
import { Roditelj } from '../../models/Roditelj';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-put-roditelj',
  templateUrl: './put-roditelj.component.html',
  styleUrls: ['./put-roditelj.component.css']
})
export class PutRoditeljComponent implements OnInit {

  roditelj: Roditelj;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private location: Location,
              private adminService: AdminService) {
  }

  ngOnInit() {  
    const id = +this.route.snapshot.paramMap.get('id');
    this.adminService.getRoditelj(id).subscribe(a => this.roditelj = a);
  }

  goBack() {
     this.location.back();
  }

  save(): void {
    this.adminService.updateRoditelj(this.roditelj)
       .subscribe(() => this.goBack());
  }
  
  updateRoditelj() {
    this.adminService.updateRoditelj(this.roditelj)
    .subscribe((roditelj: Roditelj) =>  {
      alert('Roditelj ' + roditelj.ime + ' ' + roditelj.prezime + ' je uspešno izmenjen!');
      this.router.navigate(['/admin']);
    });
  }
}
