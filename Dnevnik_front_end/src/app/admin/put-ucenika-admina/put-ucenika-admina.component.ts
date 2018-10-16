import { Component, OnInit } from '@angular/core';
import { Ucenik } from '../../models/Ucenik';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-put-ucenika-admina',
  templateUrl: './put-ucenika-admina.component.html',
  styleUrls: ['./put-ucenika-admina.component.css']
})
export class PutUcenikaAdminaComponent implements OnInit {

  ucenik: Ucenik;
  constructor(private route: ActivatedRoute,
    private router: Router,
    private location: Location,
    private adminService: AdminService) { }

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('id');
    this.adminService.getUcenik(id).subscribe(a => this.ucenik = a);
  }

  goBack() {
    this.location.back();
 }

 save(): void {
   this.adminService.updateUcenik(this.ucenik)
      .subscribe(() => this.goBack());
 }
 
 updateUcenik() {
   this.adminService.updateUcenik(this.ucenik)
   .subscribe((ucenik: Ucenik) =>  {
     alert('UCenik ' + ucenik.ime + ' ' + ucenik.prezime + ' je uspešno izmenjen!');
     this.router.navigate(['/admin']);
   });
 }
}
