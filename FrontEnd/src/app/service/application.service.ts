import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Application } from '../models/Application';
import { CookieService } from 'ngx-cookie-service';
import { UserDTO } from '../models/user.dto';
import { Offer } from '../models/offer';

@Injectable({
  providedIn: 'root',
})
export class ApplicationService {
  private apiUrl = 'http://localhost:7646/api/applications'; // Update with your actual API endpoint
  private jwtCookie = this.cookieService.get('jwtCookie');
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: `${this.jwtCookie}`, // Replace with correct cookie name
  });
  getApplicationsByUser(user: UserDTO): Observable<Application[]> {
    const params = new HttpParams().set('userID', user.id!.toString());
    return this.http.get<Application[]>(`${this.apiUrl}/MyApplications`, {
      headers: this.headers,
      params,
    });
  }
  getApplicationsByUserandOffer(
    user: UserDTO,
    offer: Offer
  ): Observable<Application[]> {
    const params = new HttpParams()
      .set('userID', user.id!.toString())
      .set('offerID', offer.id!.toString());
    return this.http.get<Application[]>(`${this.apiUrl}/applicationsByPK`, {
      headers: this.headers,
      params,
    });
  }
  apply(
    application: Application,
    Userid: Number,
    offerid: Number
  ): Observable<Application> {
    const params = new HttpParams()
      .set('userId', application.userId.toString())
      .set('offerId', application.offerId.toString());

    return this.http.post<any>(`${this.apiUrl}/apply`, application, {
      headers: this.headers,
      params,
    });
  }

  getApplicationsByOffer(offerId: number): Observable<Application[]> {
    const params = new HttpParams()
      .set('offerID', offerId!.toString());
    return this.http.get<Application[]>(`${this.apiUrl}/applicationsByOffer`,{
      headers: this.headers,
      params,
    });
  }

  markApplicationViewed(application: Application): Observable<any> {
    const params = new HttpParams()
    .set('userId', application.userId.toString())
    .set('offerId', application.offerId.toString());
    console.log(params);
    return this.http.put<any>(`${this.apiUrl}/updateIsViewed?userId=`+application.userId+`&offerId=`+application.offerId, {
      headers: this.headers,
    });
  }
  deleteByOfferID(id: Number) : Observable<Offer>{
    return this.http.delete<Offer>(`${this.apiUrl}/DeleteByOfferID?offerId=`+id, { headers: this.headers });
  }
  constructor(
    private http: HttpClient,
    @Inject(CookieService) private cookieService: CookieService
  ) {}
}
