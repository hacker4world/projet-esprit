import { Component, OnInit } from '@angular/core';
import { BookDTO } from '../../../models/book.dto';
import { BookService } from '../../../service/book.service';
import { ReservationBookDTO } from '../../../models/reservation.dto';
import { ReserveBookService } from '../../../service/reserve-book.service';
import {
  ChartComponent,
  ApexAxisChartSeries,
  ApexChart,
  ApexTitleSubtitle,
  ApexDataLabels,
  ApexPlotOptions,
  ApexLegend,
  ApexResponsive
} from 'ng-apexcharts';
import { UserService } from '../../../service/user.service';
import { UserDTO } from '../../../models/user.dto';
import { CategoryDTO } from '../../../models/category.dto';
import { CategoryBookService } from '../../../service/category-book.service';
export type ChartOptions = {
  series: ApexNonAxisChartSeries;
  chart: ApexChart;
  labels: string[];
  dataLabels: ApexDataLabels;
  colors: string[];
  legend: ApexLegend;
  plotOptions: ApexPlotOptions;
};
@Component({
  selector: 'app-statistique',
  templateUrl: './statistique.component.html',
  styleUrls: ['./statistique.component.css']
})
export class StatistiqueComponent implements OnInit{
  
  books: BookDTO[] = [];
  users: UserDTO[] = [];
  categories: CategoryDTO[] = [];
  reservations: ReservationBookDTO[] = [];
  reservationsToday : ReservationBookDTO[] = [];
  monthlyReservationCounts: number[] = new Array(12).fill(0);
  private chartInstance: ApexCharts | null = null;
  bookCount: number = 0;
  reservedBooksCount: number = 0;
  availableBooksCount: number = 0;
  nbTotalReservation :number  = 0
  reservationTraite : number =0;
  reservationNonTraite : number = 0;
  nbRequestReturnBook: number = 0;
  public chartOptions!: ChartOptions;
  constructor(
    private reservationService: ReserveBookService,
    private bookService: BookService,
    private userService: UserService,
    private categoryService : CategoryBookService
  ){
    
  }
  ngOnInit(): void {
    this.loadCategories();
    this. getAllUsers();
    this.getAllBooks();
    this.loadReservation();
    this.initializeChartOptions();
    this.loadReservationToday();
  }
  initializeChartOptions() {
    this.chartOptions = {
      series: [0, 0],
      chart: {
        type: 'donut'
      },
      labels: ['Treated Reservations', 'Non-Treated Reservations'],
      dataLabels: {
        enabled: true
      },
      colors: ['#00E396', '#FF4560'],
      legend: {
        position: 'bottom'
      },
      plotOptions: {
        pie: {
          donut: {
            labels: {
              show: true
            }
          }
        }
      }
    };
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
  getAllBooks() {
    this.bookService.getAllBooks().subscribe(books => {
      this.books = books;
      this.bookCount = books.length; // Met à jour le nombre de livres
      this.reservedBooksCount = books.filter(book => book.state).length;
      this.availableBooksCount = books.filter(book => !book.state).length;
    });
  
  }
  
  loadReservation(): void {
    this.reservationService.getPendingReservationsWithDetails().subscribe(data=>{
      this.reservations = data;
      console.log(data)
      this.nbTotalReservation = data.filter(reservation=> !reservation.returned).length;
      this.reservationTraite = data.filter(reservation =>reservation.approved && !reservation.returned).length;
      this.reservationNonTraite = data.filter(reservation => !reservation.approved && !reservation.returned).length;
      this.nbRequestReturnBook = data.filter(reservation => reservation.approved && reservation.requestReturn && !reservation.returned).length;
      this.chartOptions.series = [this.reservationTraite, this.reservationNonTraite];
      this.calculateMonthlyReservations();
      this.renderChart();
    });
  }
  calculateMonthlyReservations(): void {
    this.reservations.forEach(reservation => {
      const month = new Date(reservation.startDate!).getMonth();
      this.monthlyReservationCounts[month]++;
    });
  }
  renderChart(): void {
    const options = {
      chart: {
        type: 'line',
        height: 350
      },
      series: [{
        name: 'Reservations',
        data: this.monthlyReservationCounts
      }],
      xaxis: {
        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
      }
    };

    const chart = new ApexCharts(document.querySelector("#chart"), options);
    chart.render();
  }
  loadReservationToday(): void {
    this.reservationService.getReservationToday().subscribe(today=>{
      this.reservationsToday = today;
      console.log(today)
    },
  error => {
    console.error('error loading reservation today', error);
  });
  }
 
}
