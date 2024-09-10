import { CommonModule, NgFor } from "@angular/common";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule, NgModel, ReactiveFormsModule } from "@angular/forms";
import { RouterModule } from "@angular/router";
import { SideBarComponent } from "../../shared/side-bar/side-bar.component";
import { routes } from "./admin-classroom.routes";
import { NgModule } from '@angular/core';
import { AdminClassroomListComponent } from './admin-classroom-list/admin-classroom-list.component';
import { ClassroomService } from "../../service/classroom.service";
import { SubjectService } from "../../service/subject.service";
import { AdminClassroomCreateComponent } from './admin-classroom-create/admin-classroom-create.component';
import { AdminClassroomModifyComponent } from './admin-classroom-modify/admin-classroom-modify.component';
import { AdminClassroomAssignStudentComponent } from './admin-classroom-assign-student/admin-classroom-assign-student.component';

@NgModule({
  declarations: [
    AdminClassroomListComponent,
    AdminClassroomCreateComponent,
    AdminClassroomCreateComponent,
    AdminClassroomModifyComponent,
    AdminClassroomAssignStudentComponent,
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes),
    SideBarComponent,
    FormsModule,
    NgFor,
  ],
    exports: [
      RouterModule
    ],
    providers: [
      ClassroomService,
      SubjectService
    ]
})

export class AdminClassroomModule { }
