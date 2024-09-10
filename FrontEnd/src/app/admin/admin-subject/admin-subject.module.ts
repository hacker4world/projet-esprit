import { CommonModule, NgFor } from "@angular/common";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule, NgModel, ReactiveFormsModule } from "@angular/forms";
import { RouterModule } from "@angular/router";
import { SideBarComponent } from "../../shared/side-bar/side-bar.component";
import { NgModule } from '@angular/core';
import { ClassroomService } from "../../service/classroom.service";
import { SubjectService } from "../../service/subject.service";
import { AdminSubjectDetailsComponent } from './admin-subject-details/admin-subject-details.component';
import { routes } from "./admin-subject.routes";
import { AdminSubjectCreateComponent } from './admin-subject-create/admin-subject-create.component';


@NgModule({
  declarations: [


    AdminSubjectDetailsComponent,
      AdminSubjectCreateComponent
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

export class AdminSubjectModule { }
