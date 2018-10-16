import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import { AuthService } from '../auth.service'

@Injectable({
  providedIn: 'root'
})
export class RoditeljFilterService implements CanActivate {

  constructor(private router: Router, private authService: AuthService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.authService.role === 'ROLE_RODITELJA') {
      
      return true;
      
    }

    this.router.navigate(['/login'])
  }

}
