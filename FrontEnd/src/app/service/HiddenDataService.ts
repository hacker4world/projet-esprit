import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root' // Consider providers based on your sharing needs
})
export class HiddenDataService {
  private hiddenDataSubject = new BehaviorSubject<any>(null);
  hiddenData$: Observable<any> = this.hiddenDataSubject.asObservable();

  setHiddenData(data: any) {
    this.hiddenDataSubject.next(data);
  }

  getHiddenData(): Observable<any> {
    return this.hiddenData$;
  }
}
