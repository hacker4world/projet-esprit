import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BookDTO } from '../models/book.dto';
import { CategoryDTO } from '../models/category.dto';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private baseUrl = 'http://localhost:7646/api/books/';
  constructor(private http: HttpClient) { }
  getAllCategory(): Observable<CategoryDTO[]>{
    return this.http.get<CategoryDTO[]>('http://localhost:7646/api/categories/');
  }
  getAllBooks(): Observable<BookDTO[]>{
    return this.http.get<BookDTO[]>(`${this.baseUrl}`);
  }
  getBooksEndDate(): Observable<BookDTO[]>{
    return this.http.get<BookDTO[]>(`${this.baseUrl}books-with-endDate`);
  }
  // addBook(book: BookDTO): Observable<BookDTO>{
  //   return this.http.post<BookDTO>(`${this.baseUrl}`, book, {
  //     headers: new HttpHeaders({
  //       'Content-Type': 'application/json'
  //     })
  //   });
  // }
  addBook(bookData: FormData): Observable<BookDTO> {
    return this.http.post<BookDTO>(`${this.baseUrl}add`, bookData);
  }
  updateBook(id: number, bookData: FormData): Observable<BookDTO> {
    const url = `${this.baseUrl}${id}`;
    return this.http.put<BookDTO>(url, bookData);
  }
  // updateBook(id:number ,book:BookDTO): Observable<BookDTO>{
  //   const url = `${this.baseUrl}+${id}`;
  //   return this.http.put<BookDTO>(url, book, {
  //     headers: new HttpHeaders({
  //       'Content-Type': 'application/json'
  //     })
  //   });
  // }
  deleteBook(id:number): Observable<void> {
    const url = `${this.baseUrl}${id}`;
    return this.http.delete<void>(url);
  }
  getAllBooksByTitle(title:any): Observable<BookDTO[]>{
    return this.http.get<BookDTO[]>(`${this.baseUrl}search/${title}`);
  }
  getBookById (id:number):Observable<BookDTO>{
    return this.http.get<BookDTO>(`${this.baseUrl}${id}`);
  }
  
  getBooksByCategoryId(categoryId: number): Observable<BookDTO[]> {
    if (categoryId === -1) { // Special case for "All Categories"
      return this.getAllBooks();
    }

    return this.http.get<BookDTO[]>(`${this.baseUrl}category/${categoryId}`);
  }
  getRecommendedBooks(): Observable<BookDTO[]> {
    return this.http.get<BookDTO[]>(`${this.baseUrl}recommended`);
  }
}
