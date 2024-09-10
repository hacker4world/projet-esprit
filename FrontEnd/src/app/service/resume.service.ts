import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { resumeDTO } from '../models/resume.dto';
import { StorageService } from './storage.service';
import { CookieService } from 'ngx-cookie-service';


@Injectable({
  providedIn: 'root'
})
export class ResumeService {
  private apiUrl = 'http://localhost:7646/api/resumes'; // Replace with your actual API endpoint
  private jwtCookie =this.cookieService.get('jwtCookie');
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `${this.jwtCookie}` // Replace with correct cookie name
  });

  constructor(private auth: StorageService,
    private cookieService :CookieService,
    private http: HttpClient
  ){ }

  // Get all resumes
  getAllResumes(): Observable<resumeDTO[]> {
    return this.http.get<resumeDTO[]>(this.apiUrl, { headers: this.headers })
      .pipe(
        retry(1), // Optional: Retry request once in case of network errors
        catchError(this.handleError)
      );
  }

  // Get resume by ID
  getResumeById(id: number): Observable<resumeDTO> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<resumeDTO>(url, { headers: this.headers })
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  // Create a new resume
  createResume(id:number,resumeDTO: resumeDTO): Observable<resumeDTO> {
    return this.http.post<resumeDTO>(this.apiUrl+'/'+id, resumeDTO, { headers: this.headers })
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  // Update a resume
  updateResume(id: number,idUser:number, resumeDTO: resumeDTO): Observable<resumeDTO> {
    const url = `${this.apiUrl}/${id}/${idUser}`;
    return this.http.put<resumeDTO>(url, resumeDTO, { headers: this.headers })
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  // Delete a resume
  deleteResume(id: number): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url, { headers: this.headers })
      .pipe(
        retry(1),
        catchError(this.handleError)
      );
  }

  // Error handling
  private handleError(error: any) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Client-side or network error occurred. Handle it accordingly.
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(errorMessage);
  }
}
