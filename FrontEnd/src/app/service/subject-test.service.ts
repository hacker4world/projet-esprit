import { Injectable } from '@angular/core';
import { BaseVariables } from '../variables';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';


@Injectable({
  providedIn: 'root'
})
export class SubjectTestService {

  baseEndpoint = `${BaseVariables.BackendBaseURI}/SubjectTest`;

  constructor(private http: HttpClient,private cookieService :CookieService) { }

  getAllByClassRoom(classRoomId: number): Observable<any> {
    return this.http.get(`${this.baseEndpoint}/classroom/${classRoomId}`);
  }

  getById(id: number): Observable<any> {
    return this.http.get<any>(`${this.baseEndpoint}/${id}`);
  }

  update(request: any): Observable<any> {
    return this.http.put(this.baseEndpoint, request);
  }

  createSubjectTest(request: any): Observable<any> {
    return this.http.post(this.baseEndpoint, request);
  }

}
