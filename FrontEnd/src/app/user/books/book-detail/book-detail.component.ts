import { Component } from '@angular/core';
import { BookDTO } from '../../../models/book.dto';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from '../../../service/book.service';
import { CategoryDTO } from '../../../models/category.dto';
import { CategoryBookService } from '../../../service/category-book.service';


@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrl: './book-detail.component.css'
})
export class BookDetailComponent {
  book: BookDTO | undefined;
  category: CategoryDTO | undefined;
  constructor(private route:ActivatedRoute, private bookService:BookService, private categoryService:CategoryBookService,private router: Router){}
  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const bookId = Number(params.get('id'));
      this.bookService.getBookById(bookId).subscribe(book => {
        this.book = book;
        this.fetchCategory(book.categoryId);
      });
    });
  }
  fetchCategory(categoryId: number): void {
    this.categoryService.getCategoryById(categoryId).subscribe(category => {
      this.category = category;
    });
  }
  reserveBookDetail(bookId: number): void {
    this.router.navigate(['api/user/books/reserve-book', bookId]);
  }
}
