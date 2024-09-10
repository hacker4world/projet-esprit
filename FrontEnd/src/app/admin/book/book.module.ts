import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';


import { HttpClientModule } from '@angular/common/http';
import { CategoryBookService } from '../../service/category-book.service';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { routes } from './book-routing.module';
import { CategoriesComponent } from './Category/categories/categories.component';
import { BookService } from '../../service/book.service';
import { BookManagementComponentComponent } from './book-management-component/book-management-component.component';
import { ReserveListComponent } from './reserve-list/reserve-list.component';
import { StatistiqueComponent } from './statistique/statistique.component';
import { NgApexchartsModule } from 'ng-apexcharts';
import { ListArchiveComponent } from './list-archive/list-archive.component';





@NgModule({
  declarations: [CategoriesComponent, BookManagementComponentComponent, ReserveListComponent,ReserveListComponent, StatistiqueComponent, ListArchiveComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    NgApexchartsModule,
    RouterModule.forChild(routes),
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [CategoryBookService, BookService]

})
export class BookModule { }
