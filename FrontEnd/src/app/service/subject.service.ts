import { Injectable } from '@angular/core';
import { BaseVariables } from '../variables';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class SubjectService {

  baseEndpoint = `${BaseVariables.BackendBaseURI}/Subject`;

  constructor(private http: HttpClient) { }

  getAllSubjects(pageNumber: number, pageSize: number): Observable<any> {
    let params = new HttpParams();
    if (pageNumber !== 0)
      params = params.set('pageNumber', pageNumber.toString());
    if (pageSize !== 0)
      params = params.set('pageSize', pageSize.toString());
    return this.http.get<any>(`${this.baseEndpoint}?page=${pageNumber}&size=${pageSize}`, { params: params });
  }

  getAllSubjectsByClassroom(classroomId: number, pageNumber: number, pageSize: number): Observable<any> {
    let params = new HttpParams();
    if (pageNumber !== 0)
      params = params.set('pageNumber', pageNumber.toString());
    if (pageSize !== 0)
      params = params.set('pageSize', pageSize.toString());
    return this.http.get<any>(`${this.baseEndpoint}/ByClassroom/${classroomId}`, { params: params });
  }

  getSubjectById(id: number): Observable<any> {
    return this.http.get<any>(`${this.baseEndpoint}/${id}`);
  }

  post(request: any): Observable<any> {
    return this.http.post<any>(`${this.baseEndpoint}`, request);
  }

}
