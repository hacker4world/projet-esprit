import { Injectable } from '@angular/core';
import { BaseVariables } from '../variables';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class ClassroomService {

  baseEndpoint = `${BaseVariables.BackendBaseURI}/Classroom`;

  constructor(private http: HttpClient) { }

  getAllClassrooms(pageNumber: number, pageSize: number): Observable<any> {
    let params = new HttpParams();
    if (pageNumber !== 0)
      params = params.set('pageNumber', pageNumber.toString());
    if (pageSize !== 0)
      params = params.set('pageSize', pageSize.toString());

    return this.http.get(`${this.baseEndpoint}`, { params: params });
  }

  assignUserToClassroom(studentId: number, classroomId: number): Observable<void> {
    return this.http.put<void>(`${this.baseEndpoint}/assignUser/${studentId}/${classroomId}`, {});
  }

  getStudentsWithoutClassroom(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseEndpoint}/unassigned-students`);
  }

  searchClassrooms(groupName: string, pageNumber: number, pageSize: number): Observable<any> {
    let params = new HttpParams().set('groupName', groupName);
    if (pageNumber !== 0)
      params = params.set('pageNumber', pageNumber.toString());
    if (pageSize !== 0)
      params = params.set('pageSize', pageSize.toString());

    return this.http.get(`${this.baseEndpoint}/search`, { params: params });
  }

  getClassroomById(id: number): Observable<any> {
    return this.http.get<any>(`${this.baseEndpoint}/${id}`);
  }

  getUsersByClassroom(classroomId: number, pageNumber: number, pageSize: number): Observable<any> {
    let params = new HttpParams();
    if (pageNumber !== 0)
      params = params.set('pageNumber', pageNumber.toString());
    if (pageSize !== 0)
      params = params.set('pageSize', pageSize.toString());

    return this.http.get<any>(`${this.baseEndpoint}/user/${classroomId}`, { params: params });
  }

  post(request: any): Observable<any> {
    return this.http.post<any>(`${this.baseEndpoint}`, request);
  }

  put(request: any): Observable<any> {
    return this.http.put<any>(`${this.baseEndpoint}`, request);
  }

  removeStudentFromClassroom(studentId: number): Observable<void> {
    return this.http.put<void>(`${this.baseEndpoint}/removeStudent/${studentId}`, {});
  }
}
