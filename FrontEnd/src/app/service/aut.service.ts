import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StorageService } from './storage.service';

const AUTH_API = 'http://localhost:7646/api/auth/';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient, private auth: StorageService) {}
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' }),

    withCredentials: true,
    observe: 'response' as 'response',
  };
  login(username: string, password: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'signin',
      {
        username,
        password,
      },
      this.httpOptions
    );
  }

  forgetPassword(username: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'forget/' + username,
      {},
      this.httpOptions
    );
  }
  towWayAuth(userName: string, base64: any): Observable<any> {
    return this.http.post(
      AUTH_API + 'qrcode/' + userName,
      { data: base64 },
      { responseType: 'text' }
    );
  }

  register(
    firstName: string,
    lastName: string,
    role: string,
    password: string,
    email: string
  ): Observable<any> {
    return this.http.post(
      AUTH_API + 'signup',
      {
        firstName,
        lastName,
        password,
        email,
      },
      this.httpOptions
    );
  }

  logout(): Observable<any> {
    return this.http.post(AUTH_API + 'signout', {}, this.httpOptions);
  }
}
