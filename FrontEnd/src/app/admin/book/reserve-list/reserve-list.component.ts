import { Component, OnInit } from '@angular/core';
import { ReservationBookDTO } from '../../../models/reservation.dto';
import { ReserveBookService } from '../../../service/reserve-book.service';
import { error } from 'console';
import { UserDTO } from '../../../models/user.dto';
import { CategoryDTO } from '../../../models/category.dto';
import { CategoryBookService } from '../../../service/category-book.service';
import { UserService } from '../../../service/user.service';

@Component({
  selector: 'app-reserve-list',
  templateUrl: './reserve-list.component.html',
  styleUrls: ['./reserve-list.component.css']
})
export class ReserveListComponent implements OnInit {
  reservations: ReservationBookDTO[] = [];
  users: UserDTO[] = [];
  categories: CategoryDTO[] = [];
  constructor(private reservationService: ReserveBookService,private categoryService: CategoryBookService, 
   private userService:UserService){}
  ngOnInit(): void {
    this.loadCategories();
    this. getAllUsers();
    this.loadReservation();
    console.log(this.loadReservation)
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
  getAllUsers(): void {
    this.userService.getAllUsers().subscribe(data => {
      this.users = data;
    });
  }
  getUserNameBy(userId: number): string {
    const user = this.users.find(cat => cat.id === userId);
    return user ? user.firstName : 'Unknown name';
  }
  loadReservation(): void {
    this.reservationService.getPendingReservationsWithDetails().subscribe(data=>{
      this.reservations = data.filter(reservation => !reservation.returned);
      console.log(data)
    },
  error => {
    console.error('error loading reservation', error);
  });
  }

  acceptReservation(reservationId?: number):void {
    if (reservationId != null) {
      this.reservationService.acceptReservation(reservationId).subscribe(
        () => {
          this.loadReservation(); // Refresh the list after accepting
        },
        error => {
          console.error('Error accepting reservation', error);
        }
      );
    }
  }
  acceptReturnBook(reservationId?: number): void {
    if (reservationId != null) {
      this.reservationService.ApproveReturnBook(reservationId).subscribe(
        () => {
          this.loadReservation(); // Refresh the list after accepting
        },
        error => {
          console.error('Error accepting reservation', error);
        }
      );
    }
  }
  refuseBook(reservation: ReservationBookDTO) {
    if (confirm(`Are you sure to refuse this reservation of book "${reservation.book?.title}"?`)) {
      this.reservationService.refuseReservation(reservation.id!).subscribe(() => {
        this.loadReservation;
      });
    }
  }
}
