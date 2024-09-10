import { Inject, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Offer } from '../models/offer';
import { CookieService } from 'ngx-cookie-service';


@Injectable({
  providedIn: 'root'
})
export class OfferService {
  private apiUrl = 'http://localhost:7646/api/offres'; // Update with your actual API endpoint
  private jwtCookie =this.cookieService.get('jwtCookie');
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `${this.jwtCookie}` // Replace with correct cookie name
  });
  constructor(private http: HttpClient,
    @Inject(CookieService) private cookieService :CookieService,

  ) {}

  createOffer(offer: any): Observable<any> {
    console.log(this.jwtCookie,"ggggggggggggggggggggggggggggggggggggggggg");
    
    return this.http.post<any>(this.apiUrl, offer,{ headers: this.headers });
  }

  getOffers(): Observable<Offer[]> {
    return this.http.get<Offer[]>(this.apiUrl, { headers: this.headers });
  }
  updateOffer(id: number, updatedOffer: Offer): Observable<Offer> {
    return this.http.put<Offer>(`${this.apiUrl}/${id}`, updatedOffer,{ headers: this.headers });
  }
  getOfferById(id: number) : Observable<Offer>{
    return this.http.get<Offer>(`${this.apiUrl}/${id}`, { headers: this.headers });
  }
  deleteByOfferID(id: Number) : Observable<Offer>{
    return this.http.delete<Offer>(`${this.apiUrl}/${id}`, { headers: this.headers });
  }
 
}
