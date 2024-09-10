import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserListComponent } from './user-list/user-list.component';
import { routes } from './admin-manager-routings';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UsersStatComponent } from './users-stat/users-stat.component';
import { NgApexchartsModule } from 'ng-apexcharts';


@NgModule({
  declarations: [
    UserListComponent,
    UsersStatComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgApexchartsModule,
    RouterModule.forChild(routes)
  ]
})
export class AdminMangerModule { }
