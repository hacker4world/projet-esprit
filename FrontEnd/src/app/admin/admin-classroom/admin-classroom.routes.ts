import { Routes } from "@angular/router";
import { AdminClassroomListComponent } from "./admin-classroom-list/admin-classroom-list.component";
import { AdminClassroomDetailsComponent } from "./admin-classroom-details/admin-classroom-details.component";
import { AdminClassroomCreateComponent } from "./admin-classroom-create/admin-classroom-create.component";
import { AdminClassroomModifyComponent } from "./admin-classroom-modify/admin-classroom-modify.component";
import { AdminClassroomAssignStudentComponent } from "./admin-classroom-assign-student/admin-classroom-assign-student.component";

export const routes: Routes = [

  {
    path: '',
    component : AdminClassroomListComponent
  },
  {
    path: 'create',
    component: AdminClassroomCreateComponent
  },
  {
    path: 'details/:id',
    component: AdminClassroomDetailsComponent,
  },
  {
    path: 'modify/:id',
    component: AdminClassroomModifyComponent,
  },
  {
    path: 'assignstudent/:id',
    component: AdminClassroomAssignStudentComponent
  }


];
