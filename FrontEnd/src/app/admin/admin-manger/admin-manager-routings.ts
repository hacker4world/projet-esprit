import { Routes } from '@angular/router';
import { UserListComponent } from './user-list/user-list.component';
import { UsersStatComponent } from './users-stat/users-stat.component';

export const routes: Routes = [
  {
    path: "users-list",
    component: UserListComponent
  },
  {
    path: "users-stats",
    component: UsersStatComponent
  },


];
