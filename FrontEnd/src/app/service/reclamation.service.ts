import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ClaimUpdateDTO } from '../models/claim.dto';

@Injectable({
  providedIn: 'root',
})
export class ReclamationService {
  private baseUrl = 'http://localhost:7646/api/reclamations';
  constructor(private http: HttpClient) {}

  getAll(pageNumber: number, pageSize: number): Observable<any> {
    let params = new HttpParams();
    if (pageNumber !== 0)
      params = params.set('pageNumber', pageNumber.toString());
    if (pageSize !== 0) params = params.set('pageSize', pageSize.toString());

    return this.http.get(`${this.baseUrl}`, { params: params });
  }

  getById(idClaim: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${idClaim}`);
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
  update(body: any): Observable<any> {
    return this.http.put(`${this.baseUrl}`, body);
  }

  updateStatus(body: ClaimUpdateDTO): Observable<any> {
    return this.http.put(`${this.baseUrl}/status`, body);
  }
  getClaimStatistics(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/statistics`);
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
  getClaimStatisticsByUser(idUser: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/statistics/user/${idUser}`);
  }

  create(body: any, idUser: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/user/${idUser}`, body);
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

  getAllArchivied(pageNumber: number, pageSize: number): Observable<any> {
    let params = new HttpParams();
    if (pageNumber !== 0)
      params = params.set('pageNumber', pageNumber.toString());
    if (pageSize !== 0) params = params.set('pageSize', pageSize.toString());

    return this.http.get(`${this.baseUrl}/archived`, { params: params });
  }

  delete(idClaim: number) {
    return this.http.delete(`${this.baseUrl}/${idClaim}`);
  }
}
