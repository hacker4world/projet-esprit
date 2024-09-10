import { Injectable } from '@angular/core';
import { BaseVariables } from '../variables';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';


@Injectable({
  providedIn: 'root'
})
export class TestEvalutionService {

  baseEndpoint = `${BaseVariables.BackendBaseURI}/TestEvalution`;

  constructor(private http: HttpClient,private cookieService :CookieService) { }


  calculateAvgGrade(userId: number): Observable<any> {
    return this.http.get(`${this.baseEndpoint}/calculateAvgGrade/${userId}`);
  }

  getMissingEvaluationsByUser(userId: number): Observable<any> {
    return this.http.get(`${this.baseEndpoint}/missing/${userId}`);
  }

  getAllByUser(userId: number): Observable<any> {
    return this.http.get(`${this.baseEndpoint}/AllByUser/${userId}`);
  }

  put(request: { id: number; note: number; subjectTestId: number }): Observable<any> {
    return this.http.put(`${this.baseEndpoint}`, request);
  }

  post(model: any): Observable<any> {
    return this.http.post(`${this.baseEndpoint}`, model);
  }

}
