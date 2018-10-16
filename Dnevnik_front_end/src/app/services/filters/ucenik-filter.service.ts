import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import { AuthService } from '../auth.service'

@Injectable({
  providedIn: 'root'
})
export class UcenikFilterService implements CanActivate {

  constructor(private router: Router, private authService: AuthService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authService.role === 'ROLE_UCENIKA') {
  
      return true;
      
    }

    this.router.navigate(['/login'])
  }

}
