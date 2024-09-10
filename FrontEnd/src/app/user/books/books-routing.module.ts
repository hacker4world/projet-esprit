import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookListComponent } from './book-list/book-list.component';
import { ReserveFormComponent } from './reserve-form/reserve-form.component';
import { BookDetailComponent } from './book-detail/book-detail.component';
import { AcceptedBookForOneUserComponent } from './accepted-book-for-one-user/accepted-book-for-one-user.component';

export const routes: Routes = [
  {path: 'books-student', component:BookListComponent},
  {path: 'reserve-book/:id', component:ReserveFormComponent},
  {path:'book-details/:id', component:BookDetailComponent},
  {path:'accptedBookByuser', component:AcceptedBookForOneUserComponent}
];

