import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ReservationBookDTO } from '../models/reservation.dto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReserveBookService {
  private baseUrl = 'http://localhost:7646/api/reservation/';
  constructor(private http: HttpClient) { 

   }
   reserveBook(userId:number,bookId:number, reservation: ReservationBookDTO):Observable<ReservationBookDTO>
   {
    const url = `${this.baseUrl}?userId=${userId}&bookId=${bookId}`;
    return this.http.post<ReservationBookDTO>(url, reservation);
   }

   getPendingReservationsWithDetails(): Observable<ReservationBookDTO[]> {
    return this.http.get<ReservationBookDTO[]>(`${this.baseUrl}pending`);
  }

  acceptReservation(reservationId: number): Observable<ReservationBookDTO> {
    return this.http.put<ReservationBookDTO>(`${this.baseUrl}accept-reservation/${reservationId}`, {});
  }

  getAccptedBookByUser(userId:number):Observable<ReservationBookDTO[]>{
    const url = `${this.baseUrl}accepted/${userId}`;

    return this.http.get<ReservationBookDTO[]>(url);
  }
  returnBook(reservationId: number) : Observable<ReservationBookDTO> {
    return this.http.put<ReservationBookDTO>(`${this.baseUrl}${reservationId}/request-return`, {});
  }
  ApproveReturnBook(reservationId: number): Observable<ReservationBookDTO>{
    return this.http.put<ReservationBookDTO>(`${this.baseUrl}${reservationId}/approve-return`, {});
  }
  getReservationToday() : Observable<ReservationBookDTO[]> {
    return this.http.get<ReservationBookDTO[]>(`${this.baseUrl}today/not-returned`);
  }
  refuseReservation(reservationId: number):Observable<void> {
    const url = `${this.baseUrl}refuse-book/${reservationId}`;
    return this.http.delete<void>(url);
  }
}
