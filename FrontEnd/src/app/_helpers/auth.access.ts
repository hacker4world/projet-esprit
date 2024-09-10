import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { StorageService } from '../service/storage.service';

@Injectable({ providedIn: 'root' })
export class AdminGuard implements CanActivate {
    constructor(private router: Router, private userService: StorageService) { }

    canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {


        const roles = next.data['roles'] as string[];
        if (this.userService.getUser()) {
            const currentUserRole = this.userService.getUser().role;
            if (!roles || !roles.includes(currentUserRole)) {
                this.router.navigate(['/api/auth']);
                return false;
            }

            return true;
        }
        return false;
    }
}
