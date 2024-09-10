import { Routes } from "@angular/router";
import { AdminSubjectDetailsComponent } from "./admin-subject-details/admin-subject-details.component";
import { AdminSubjectCreateComponent } from "./admin-subject-create/admin-subject-create.component";


export const routes: Routes = [
  {
    path: 'details/:id',
    component: AdminSubjectDetailsComponent
  },
  {
    path: 'create/:id',
    component: AdminSubjectCreateComponent
  }
];
