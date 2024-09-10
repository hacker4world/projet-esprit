import { Inject, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { UserDTO } from '../models/user.dto'; 
import { StorageService } from './storage.service';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private auth: StorageService,
    @Inject(CookieService) private cookieService :CookieService,
    private http: HttpClient
  ){ }
  private jwtCookie =this.cookieService.get('jwtCookie');
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `${this.jwtCookie}` // Replace with correct cookie name
  });
  private baseUrl = 'http://localhost:7646/api/users';  // Replace with your actual base URL
  private filurl = 'http://localhost:7646/api/files/download';
  getAllUsers(): Observable<UserDTO[]> {
    console.log("document.cookie", this.cookieService.get('jwtCookie'));
    
    return this.http.get<UserDTO[]>(`${this.baseUrl}`, { headers: this.headers });
  }

  getUserById(id: number): Observable<UserDTO> {
    return this.http.get<UserDTO>(`${this.baseUrl}/${id}`);
  }


  downloadFile(fileName: string): Observable<Blob> {
    const url = `${this.filurl}/${fileName}`; // Replace with your actual URL
    const headers = new HttpHeaders({
      'Content-Type': 'application/octet-stream', // Indicate binary data
       'Authorization': `${this.jwtCookie}`
    });

    return this.http.get(url, { responseType: 'blob', headers })
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Client-side or network error occurred. Handle it accordingly.
      errorMessage = 'Error: ' + error.error.message;
    } else {
      // Backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(errorMessage);
  }
getUserImage(fileName:string){
  return this.http.get<Blob>(`${this.filurl}/${fileName}`, { headers: this.headers });
}
  createUser(user: UserDTO): Observable<UserDTO> {
    return this.http.post<UserDTO>(`${this.baseUrl}`, user);
  }

  updateUser(id: number, user: UserDTO): Observable<UserDTO> {
    return this.http.put<UserDTO>(`${this.baseUrl}/${id}`, user);
  }

  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

 
  updateProfilePicture(id: number, profilePicFile: File): Observable<UserDTO> {
    const formData = new FormData();
    formData.append('profilePicFile', profilePicFile, profilePicFile.name);
    return this.http.post<UserDTO>(`${this.baseUrl}/${id}/profilePic`, formData);
  } 

  updateResume(id: number, resumeFile: File): Observable<UserDTO> {
    const formData: FormData = new FormData();
    formData.append('resume', resumeFile, resumeFile.name);
    return this.http.post<UserDTO>(`${this.baseUrl}/${id}/resume`, formData);
  }
}
