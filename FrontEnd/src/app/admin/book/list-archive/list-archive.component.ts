import { Component, OnInit } from '@angular/core';
import { ReserveBookService } from '../../../service/reserve-book.service';
import { CategoryBookService } from '../../../service/category-book.service';
import { UserService } from '../../../service/user.service';
import { ReservationBookDTO } from '../../../models/reservation.dto';
import { UserDTO } from '../../../models/user.dto';
import { CategoryDTO } from '../../../models/category.dto';

@Component({
  selector: 'app-list-archive',
  templateUrl: './list-archive.component.html',
  styleUrls: ['./list-archive.component.css']
})
export class ListArchiveComponent implements OnInit{

  reservations: ReservationBookDTO[] = [];
  users: UserDTO[] = [];
  categories: CategoryDTO[] = [];

  constructor(private reservationService: ReserveBookService,private categoryService: CategoryBookService, 
    private userService:UserService
  ){}
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
      this.reservations =  data.filter(reservation => reservation.returned);
      console.log(data)
    },
  error => {
    console.error('error loading reservation', error);
  });
  }
}
