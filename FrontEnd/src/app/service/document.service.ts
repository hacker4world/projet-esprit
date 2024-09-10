import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DocumentUpdateDTO } from '../models/document.dto';

@Injectable({
  providedIn: 'root',
})
export class DocumentService {
  private baseUrl = 'http://localhost:7646/api/documents';

  constructor(private http: HttpClient) {}

  getAll(pageNumber: number, pageSize: number): Observable<any> {
    let params = new HttpParams();
    if (pageNumber !== 0)
      params = params.set('pageNumber', pageNumber.toString());
    if (pageSize !== 0) params = params.set('pageSize', pageSize.toString());

    return this.http.get(`${this.baseUrl}`, { params: params });
  }

  updateStatus(body: DocumentUpdateDTO): Observable<any> {
    return this.http.put(`${this.baseUrl}/updateStatus`, body);
  }

  filterByStatus(
    status: string,
    pageNumber: number,
    pageSize: number
  ): Observable<any> {
    let params = new HttpParams().set('status', status);
    if (pageNumber !== 0)
      params = params.set('pageNumber', pageNumber.toString());
    if (pageSize !== 0) params = params.set('pageSize', pageSize.toString());

    return this.http.get(`${this.baseUrl}/filter/status`, { params: params });
  }

  filterByType(
    type: string,
    pageNumber: number,
    pageSize: number
  ): Observable<any> {
    let params = new HttpParams().set('type', type);
    if (pageNumber !== 0)
      params = params.set('pageNumber', pageNumber.toString());
    if (pageSize !== 0) params = params.set('pageSize', pageSize.toString());

    return this.http.get(`${this.baseUrl}/filter/type`, { params: params });
  }

  filterByUserAndStatus(
    status: string,
    pageNumber: number,
    pageSize: number,
    idUser: number
  ): Observable<any> {
    let params = new HttpParams().set('status', status);
    if (pageNumber !== 0)
      params = params.set('pageNumber', pageNumber.toString());
    if (pageSize !== 0) params = params.set('pageSize', pageSize.toString());

    return this.http.get(`${this.baseUrl}/filter/status/${idUser}`, {
      params: params,
    });
  }

  filterByUserAndType(
    type: string,
    pageNumber: number,
    pageSize: number,
    idUser: number
  ): Observable<any> {
    let params = new HttpParams().set('type', type);
    if (pageNumber !== 0)
      params = params.set('pageNumber', pageNumber.toString());
    if (pageSize !== 0) params = params.set('pageSize', pageSize.toString());

    return this.http.get(`${this.baseUrl}/filter/type/${idUser}`, {
      params: params,
    });
  }

  getAllByUser(
    pageNumber: number,
    pageSize: number,
    idUser: number
  ): Observable<any> {
    let params = new HttpParams();
    if (pageNumber !== 0)
      params = params.set('pageNumber', pageNumber.toString());
    if (pageSize !== 0) params = params.set('pageSize', pageSize.toString());

    return this.http.get(`${this.baseUrl}/user/${idUser}`, { params: params });
  }

  create(body: any, idUser: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/user/${idUser}`, body);
  }

  getStatisticsByUser(idUser: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/statistics/user/${idUser}`);
  }

  getStatistics(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/statistics`);
  }

  getAllArchivied(pageNumber: number, pageSize: number): Observable<any> {
    let params = new HttpParams();
    if (pageNumber !== 0)
      params = params.set('pageNumber', pageNumber.toString());
    if (pageSize !== 0) params = params.set('pageSize', pageSize.toString());

    return this.http.get(`${this.baseUrl}/archived`, { params: params });
  }

  delete(idDocument: number) {
    return this.http.delete(`${this.baseUrl}/${idDocument}`);
  }
}
