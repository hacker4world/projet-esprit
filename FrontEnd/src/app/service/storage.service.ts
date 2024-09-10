import { Injectable, PLATFORM_ID, Inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { CookieService } from 'ngx-cookie-service';
import { UserDTO } from '../models/user.dto';

const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class StorageService {
  constructor(@Inject(PLATFORM_ID) private platformId: Object,@Inject(CookieService) private cookieService: CookieService) {}

  clean(): void {
    if (isPlatformBrowser(this.platformId)) {
      window.sessionStorage.clear();
      this.cookieService.delete('jwtToken', '/');
    
    }
  }

  getJwtCookie() {
    return this.cookieService.get('jwtCookie');
  }
  public saveUser(user: UserDTO): void {
    if (isPlatformBrowser(this.platformId)) {
      window.sessionStorage.removeItem(USER_KEY);
      window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
    }
  }

  public getUser(): UserDTO {
    let user!: UserDTO ;
    if (isPlatformBrowser(this.platformId)) {
      const user = window.sessionStorage.getItem(USER_KEY);
      if (user) {
        return JSON.parse(user);
      }
    }
    return user;
  }

  // Set the JWT token in a cookie
  setToken(token: string): void {
    this.cookieService.set('jwtToken', token, { path: '/', secure: true, sameSite: 'Strict' });
  }

  // Get the JWT token from the cookie
  getToken(): string {
    return this.cookieService.get('jwtToken');
  }


  public isLoggedIn(): boolean {
    if (isPlatformBrowser(this.platformId)) {
      const user = window.sessionStorage.getItem(USER_KEY);
      return !!user;
    }
    return false;
  }
}
