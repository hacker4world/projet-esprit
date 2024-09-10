import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { routes } from './books-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { BookListComponent } from './book-list/book-list.component';
import { ReserveFormComponent } from './reserve-form/reserve-form.component';
import { BookDetailComponent } from './book-detail/book-detail.component';
import { AcceptedBookForOneUserComponent } from './accepted-book-for-one-user/accepted-book-for-one-user.component';


@NgModule({
  declarations: [
    BookListComponent,
    ReserveFormComponent,
    BookDetailComponent,
    AcceptedBookForOneUserComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule.forChild(routes),
    RouterModule
  ]
})
export class BooksModule { }
