import { Component } from '@angular/core';
import { ReservationBookDTO } from '../../../models/reservation.dto';
import { ReserveBookService } from '../../../service/reserve-book.service';
import { StorageService } from '../../../service/storage.service';
import { CategoryDTO } from '../../../models/category.dto';
import { CategoryBookService } from '../../../service/category-book.service';

@Component({
  selector: 'app-accepted-book-for-one-user',
  templateUrl: './accepted-book-for-one-user.component.html',
  styleUrls: ['./accepted-book-for-one-user.component.css']
})
export class AcceptedBookForOneUserComponent {
  reservations: ReservationBookDTO[] = [];
  categories: CategoryDTO[] = [];
  currentUserId:any;
  categoriesLoaded: boolean = false;
  constructor(private reservationService: ReserveBookService, 
    private storage: StorageService, private categoryService: CategoryBookService){}

  ngOnInit(): void {
    this.loadCategories();
    this.loadAcceptedReservations();
    
  }
  
  loadCategories() {
    this.categoryService.getAllCategory().subscribe(categories => {
      this.categories = categories;
      this.categoriesLoaded = true;
    });
  }
  loadAcceptedReservations(): void {
    const userId =  this.currentUserId = this.storage.getUser().id
    console.log(userId);
    
    this.reservationService.getAccptedBookByUser(userId!).subscribe(
      data => {
        this.reservations = data;
        console.log(data); // Vérifiez les données dans la console
      },
      error => {
        console.error('Erreur lors du chargement des réservations acceptées', error);
      }
    );
  }
  
  getCategoryNameById(categoryId: number | undefined): string {
    if (categoryId === undefined) {
      return 'Unknown Category';
    }
    const category = this.categories.find(cat => cat.id === categoryId);
    return category ? category.categoryName : 'Unknown Category';
  }
  requestBook(reservationId?: number):void{
    if (reservationId != null) {
      this.reservationService.returnBook(reservationId).subscribe(
        () => {
          this.loadAcceptedReservations(); // Refresh the list after accepting
        },
        error => {
          console.error('Error request returnn book', error);
        }
      );
    }
  }
}
