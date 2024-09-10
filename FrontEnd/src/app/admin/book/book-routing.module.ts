import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookManagementComponentComponent } from './book-management-component/book-management-component.component';
import { CategoriesComponent } from './Category/categories/categories.component';
import { AddCategoryComponent } from './Category/add-category/add-category.component';
import { ReserveListComponent } from './reserve-list/reserve-list.component';
import { StatistiqueComponent } from './statistique/statistique.component';
import { ListArchiveComponent } from './list-archive/list-archive.component';

export const routes: Routes = [
  {path: 'books', component:BookManagementComponentComponent},
  {path:'categories', component:CategoriesComponent},
  {path:'categories/new', component:AddCategoryComponent},
  { path: 'categories/:id', component: AddCategoryComponent },
  { path: 'reservations', component: ReserveListComponent },
  { path: 'statistiques', component: StatistiqueComponent },
  { path: 'archives', component: ListArchiveComponent },
];



