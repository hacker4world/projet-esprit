import { Component } from '@angular/core';
import { BookDTO } from '../../../models/book.dto';
import { CategoryDTO } from '../../../models/category.dto';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from '../../../service/book.service';
import { CategoryBookService } from '../../../service/category-book.service';
import { StorageService } from '../../../service/storage.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReservationBookDTO } from '../../../models/reservation.dto';
import { ReserveBookService } from '../../../service/reserve-book.service';

@Component({
  selector: 'app-reserve-form',
  
  templateUrl: './reserve-form.component.html',
  styleUrl: './reserve-form.component.css'
})
export class ReserveFormComponent {
  book: BookDTO | undefined;
  category: CategoryDTO | undefined;
  currentUserId:any;
  reserveForm!: FormGroup;
  errorMessage: string = '';
  constructor(private route:ActivatedRoute, 
    private bookService:BookService, 
    private categoryService:CategoryBookService,
    private reservationService: ReserveBookService,
    private storage:StorageService,
    private fb: FormBuilder,
    private router: Router )
  {
    this.reserveForm = this.fb.group({
      reason: ['', [Validators.required, Validators.minLength(10)]],
      endDate: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const bookId = Number(params.get('id'));
      this.bookService.getBookById(bookId).subscribe(book => {
        this.book = book;
        this.fetchCategory(book.categoryId);
      });
    });
    this.reserveForm.get('endDate')?.valueChanges.subscribe(() => {
      this.validateEndDate(this.reserveForm);
    });
  }
  fetchCategory(categoryId: number): void {
   
    this.categoryService.getCategoryById(categoryId).subscribe(category => {
      this.category = category;
    });
  }
  validateEndDate(formGroup: FormGroup) {
    const endDateControl = formGroup.get('endDate');
    if (!endDateControl || endDateControl.errors) {
      return;
    }

    const endDate = new Date(endDateControl.value);
    const startDate = new Date(); // Today's date

    if (endDate <= startDate) {
      endDateControl.setErrors({ endDateInvalid: true });
    } else {
      endDateControl.setErrors(null);
    }

    // Validate based on book's page count
    if (this.book && this.book.nbPage) {
      const daysDiff = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
      if (this.book.nbPage <= 150 && daysDiff > 15) {
        endDateControl.setErrors({ maxReservationPeriodExceeded: true });
      } else if (this.book.nbPage > 150 && daysDiff > 30) {
        endDateControl.setErrors({ maxReservationPeriodExceeded: true });
      }
    }
  }
  onSubmit(): void {
    
    const user =  this.currentUserId = this.storage.getUser().id
    console.log(user)

    if (this.reserveForm.invalid) {
      this.errorMessage = 'Please fill out the form correctly.';
      return;
    }

    const reservation: ReservationBookDTO = {
      reason: this.reserveForm.value.reason,
      endDate: this.reserveForm.value.endDate,
      bookId: this.book?.id!,
      userId: this.currentUserId!
    };

    this.reservationService.reserveBook(this.currentUserId!, this.book!.id!, reservation).subscribe(
      response => {

        console.log('Reservation successful', response);
        // Traitez la réussite de la réservation ici
        this.router.navigate(['/api/user/books/books-student']);
      },
      error => {
        console.error('Reservation failed', error);
        // Traitez l'échec de la réservation ici
      }
    );
  }
}
