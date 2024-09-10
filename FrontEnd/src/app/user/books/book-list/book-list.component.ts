import { Component, OnInit } from '@angular/core';
import { BookDTO } from '../../../models/book.dto';
import { CategoryDTO } from '../../../models/category.dto';
import { BookService } from '../../../service/book.service';
import { CategoryBookService } from '../../../service/category-book.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrl: './book-list.component.css'
})
export class BookListComponent implements OnInit {
  books: BookDTO[] = [];
  categories: CategoryDTO[] = [];
  remainingDays ?: number;
  selectedCategoryId?: number  = -1;
  constructor(
    
    private bookService: BookService,
    private categoryService: CategoryBookService,
    private router: Router
  ) {

    }

    ngOnInit(): void {
      
      this.loadCategories();
      this.getAllBooks();
    }
    loadCategories() {
      this.categoryService.getAllCategory().subscribe(categories => {
        this.categories = categories;
      });
    }
    getCategoryNameById(categoryId: number): string {
      const category = this.categories.find(cat => cat.id === categoryId);
      return category ? category.categoryName : 'Unknown Category';
    }

    getAllBooks() {
      this.bookService.getBooksEndDate().subscribe(books => {
          this.books = books.map(book => {
              book.remainingDays = this.calculateRemainingDays(book.endDate);
              return book;
          });
      });
  }
  getBooksByCategory() {
    if (this.selectedCategoryId === undefined) {
      this.getAllBooks();
    } else {
      this.bookService.getBooksByCategoryId(this.selectedCategoryId).subscribe(books => {
        this.books = books.map(book => {
          book.remainingDays = this.calculateRemainingDays(book.endDate);
          return book;
        });
      });
    }
  }
  calculateRemainingDays(endDate: string): number | undefined {
    if (!endDate) {
        return undefined;
    }
    const end = new Date(endDate);
    const today = new Date();
    const timeDiff = end.getTime() - today.getTime();
    return Math.ceil(timeDiff / (1000 * 3600 * 24));
}

    showBookDetails(bookId: number): void {
      this.router.navigate(['api/user/books/book-details', bookId]);
    }
    reserveBookDetail(bookId: number): void {
      this.router.navigate(['api/user/books/reserve-book', bookId]);
    }
}
