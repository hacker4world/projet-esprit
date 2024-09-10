import { CommonModule, NgFor } from "@angular/common";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule, NgModel, ReactiveFormsModule } from "@angular/forms";
import { RouterModule } from "@angular/router";
import { SideBarComponent } from "../../shared/side-bar/side-bar.component";
import { routes } from "./admin-subject-test.routes";
import { NgModule } from '@angular/core';
import { ClassroomService } from "../../service/classroom.service";
import { SubjectService } from "../../service/subject.service";
import { TestEvalutionService } from "../../service/test-evalution.service";
import { SubjectTestService } from "../../service/subject-test.service";
import { AdminSubjectTestModifyComponent } from './admin-subject-test-modify/admin-subject-test-modify.component';
import { AdminSubjectTestAddComponent } from './admin-subject-test-add/admin-subject-test-add.component';



@NgModule({
  declarations: [
  
    AdminSubjectTestModifyComponent,
       AdminSubjectTestAddComponent
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
      SubjectService,
      TestEvalutionService,
      SubjectTestService
    ]
})


export class AdminSubjectTestModule { }
