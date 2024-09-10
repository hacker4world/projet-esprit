import { Routes } from "@angular/router";
import { AdminSubjectTestModifyComponent } from "./admin-subject-test-modify/admin-subject-test-modify.component";
import { AdminSubjectTestAddComponent } from "./admin-subject-test-add/admin-subject-test-add.component";


export const routes: Routes = [
  {
    path: 'modify/:id',
    component: AdminSubjectTestModifyComponent
  },
  {
    path: 'add/:id',
    component: AdminSubjectTestAddComponent
  }
];
