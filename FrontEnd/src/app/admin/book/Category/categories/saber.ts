// import { Component } from '@angular/core';
// import { BookDTO } from '../../../models/book.dto';
// import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { BookService } from '../../../service/book.service';
// import { CategoryBookService } from '../../../service/category-book.service';
// import { CategoryDTO } from '../../../models/category.dto';

// @Component({
//   selector: 'app-book-management-component',
//   templateUrl: './book-management-component.component.html',
//   styleUrl: './book-management-component.component.css'
// })
// export class BookManagementComponentComponent {
//   books: BookDTO[] = [];
//   categories: CategoryDTO[] = [];
//   bookForm !: FormGroup;
//   selectedBook: BookDTO | null = null;
//   isModalOpen = false;
//   previewUrl: string | ArrayBuffer | null =null  ;
//   selectedFile: File | null   = null;       
//   allowedFileTypes = ['image/jpeg', 'image/png','image/'];

//   constructor(
//     private fb: FormBuilder,
//     private bookService: BookService,
//     private categoryService: CategoryBookService
//   ) {
//     this.bookForm = this.fb.group({
//       id: [null],
//       title: ['', Validators.required],
//       author: ['', Validators.required],
//       description: ['', Validators.required],
//       categoryId: [''],

//     });
//   }
//   //image function 
//   onFileChange(event: any) {
//     this.selectedFile = event.target.files[0];
//     this.previewImage();
//   }
//   previewImage() {
//     if (this.selectedFile) {
//       const reader = new FileReader();
//       reader.onload = () => {
//         this.previewUrl = reader.result;
//       };
//       reader.readAsDataURL(this.selectedFile);
//     }
//   }
//   ngOnInit(): void {

//     this.loadCategories();
//     this.getAllBooks();
//   }
//   loadCategories() {
//     this.categoryService.getAllCategory().subscribe(categories => {
//       this.categories = categories;
//     });
//   }
//   getAllBooks() {
//     this.bookService.getAllBooks().subscribe(books => {
//       this.books = books
//     });
//   }

//   openAddModal() {
//     console.log('Opening Add Modal');
//     this.selectedBook = null;
//     this.bookForm.reset();
//     this.previewUrl = null;
//     this.selectedFile = null;
//     this.isModalOpen = true;
//   }

//   openEditModal(book: BookDTO) {
//     console.log('Opening Edit Modal for book:', book);
//     this.selectedBook = book;
//     this.bookForm.patchValue(book);
//     this.previewUrl = book.imageUrl;
//     this.isModalOpen = true;
//   }


//   closeModal() {
//     console.log('Closing Modal');
//     this.isModalOpen = false;
//   }
  
//   saveBook() {
//     if (this.bookForm.invalid) {
//       return;
//     }
  
//     const formData: FormData = new FormData();
  
//     // Check if selectedFile is not null before appending
//     if (this.selectedFile) {
//       formData.append('imageUrl', this.selectedFile);
//     }
  
//     formData.append('categoryId', this.bookForm.get('categoryId')?.value);
//     formData.append('title', this.bookForm.get('title')?.value);
//     formData.append('author', this.bookForm.get('author')?.value);
//     formData.append('description', this.bookForm.get('description')?.value);
  
//     this.bookService.addBook(formData).subscribe(res => {
//       if (res.id != null) {
//         this.books.push(res);
//         this.closeModal();
//       }
//     });
//   }

// }
