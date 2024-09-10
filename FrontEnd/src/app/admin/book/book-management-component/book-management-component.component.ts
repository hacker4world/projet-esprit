import { Component } from '@angular/core';
import { BookDTO } from '../../../models/book.dto';
import { Form, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BookService } from '../../../service/book.service';
import { CategoryBookService } from '../../../service/category-book.service';
import { CategoryDTO } from '../../../models/category.dto';
import { title } from 'process';

@Component({
  selector: 'app-book-management-component',
  templateUrl: './book-management-component.component.html',
  styleUrl: './book-management-component.component.css'
})
export class BookManagementComponentComponent {
  books: BookDTO[] = [];
  categories: CategoryDTO[] = [];
  bookForm !: FormGroup;
  searchBookForm !: FormGroup;
  selectedBook: BookDTO | null = null;
  isModalOpen = false;
  previewUrl: string | ArrayBuffer | null = null;
  selectedFile: File | null = null;
  allowedFileTypes = ['image/jpeg', 'image/png','image/'];
  constructor(
    private fbs: FormBuilder,
    private fb: FormBuilder,
    private bookService: BookService,
    private categoryService: CategoryBookService
  ) {
    this.bookForm = this.fb.group({
      id: [null],
      title: ['', Validators.required],
      author: ['', Validators.required],
      description: ['', Validators.required],
      imageUrl: [''],
      nbPage: [null, Validators.required],
      categoryId: [''],

    });
  }
  ngOnInit(): void {
    this.searchBookForm = this.fbs.group({
      title: ['', Validators.required],
    });
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
    this.bookService.getAllBooks().subscribe(books => {
      this.books = books
    });
  }

  openAddModal() {
    console.log('Opening Add Modal');
    this.selectedBook = null;
    this.bookForm.reset();
    this.previewUrl = null;
    this.selectedFile = null;
    this.isModalOpen = true;
  }

  openEditModal(book: BookDTO) {
    console.log('Opening Edit Modal for book:', book);
    this.selectedBook = book;
    this.bookForm.patchValue(book);
    this.previewUrl = book.imageUrl ? 'assets/img/' + book.imageUrl : null;
    this.isModalOpen = true;
  }


  closeModal() {
    console.log('Closing Modal');
    this.isModalOpen = false;
  }
  //image function 
  onFileChange(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      const file = input.files[0];
      if (this.allowedFileTypes.includes(file.type)) {
        this.selectedFile = file;
        const reader = new FileReader();
        reader.onload = () => {
          this.previewUrl = reader.result;
        };
        reader.readAsDataURL(this.selectedFile);
      } else {
        alert('Only JPEG and PNG files are allowed.');
        input.value = '';  // Clear the file input
      }
    }
  }
  saveBook() {
    if (this.bookForm.invalid) {
      return;
    }

    const book = this.bookForm.value as BookDTO;
    const formData = new FormData();

    formData.append('title', book.title);
    formData.append('author', book.author);
    formData.append('description', book.description);
    formData.append('nbPage', book.nbPage.toString());
    formData.append('categoryId', book.categoryId.toString());

    if (this.selectedFile) {
      formData.append('imageUrl', this.selectedFile, this.selectedFile.name);
    } else {
      formData.append('imageUrl', book.imageUrl);
    }

    if (this.selectedBook) {
      this.bookService.updateBook(this.selectedBook.id, formData).subscribe(() => {
        this.getAllBooks();
        this.closeModal();
      });
    } else {
      this.bookService.addBook(formData).subscribe((response) => {
        this.books.push(response);
        location.reload();
        this.closeModal();
      });
    }
  }
  submitFormSearch(){
    this.books = [];
    const title = this.searchBookForm.get('title')!.value;
    this.bookService.getAllBooksByTitle(title).subscribe(books => {
      this.books = books
    });
  }
  // Delete a category
  deleteBook(book: BookDTO) {
    if (confirm(`Are you sure you want to delete the book "${book.title}"?`)) {
      this.bookService.deleteBook(book.id).subscribe(() => {
        this.getAllBooks();
      });
    }
  }
}
