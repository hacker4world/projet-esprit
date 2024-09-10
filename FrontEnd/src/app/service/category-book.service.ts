import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CategoryDTO } from '../models/category.dto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryBookService {
  private baseUrl = 'http://localhost:7646/api/categories/';

  constructor(private http: HttpClient) { }

  getAllCategory(): Observable<CategoryDTO[]>{
    return this.http.get<CategoryDTO[]>(`${this.baseUrl}`);
  }

  addCategory(category: CategoryDTO): Observable<CategoryDTO>{
    return this.http.post<CategoryDTO>(`${this.baseUrl}`, category, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }

  getCategoryById(id:number): Observable<CategoryDTO>{
    const url = `${this.baseUrl}${id}`;
    return this.http.get<CategoryDTO>(url);
  }
 

  updateCategory(id:number, category: CategoryDTO): Observable<CategoryDTO>
  {
    const url = `${this.baseUrl}+${id}`;
    return this.http.put<CategoryDTO>(url, category, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }

  deleteCategory(id:number): Observable<void> {
    const url = `${this.baseUrl}+${id}`;
    return this.http.delete<void>(url);
  }
  
}
