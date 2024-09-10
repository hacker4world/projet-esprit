import { CommonModule, NgFor } from "@angular/common";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule, NgModel, ReactiveFormsModule } from "@angular/forms";
import { RouterModule } from "@angular/router";
import { SideBarComponent } from "../../shared/side-bar/side-bar.component";
import { routes } from "./admin-test-evalution.routes";
import { NgModule } from '@angular/core';
import { ClassroomService } from "../../service/classroom.service";
import { SubjectService } from "../../service/subject.service";
import { AdminTestEvalutionByStudentComponent } from "./admin-test-evalution-by-student/admin-test-evalution-by-student.component";
import { TestEvalutionService } from "../../service/test-evalution.service";
import { AdminTestEvalutionFinalResultComponent } from './admin-test-evalution-final-result/admin-test-evalution-final-result.component';
import { SubjectTestService } from "../../service/subject-test.service";
import { AdminTestEvalutionAddTestComponent } from './admin-test-evalution-add-test/admin-test-evalution-add-test.component';

@NgModule({
  declarations: [
    AdminTestEvalutionByStudentComponent,
    AdminTestEvalutionFinalResultComponent,
    AdminTestEvalutionAddTestComponent
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

export class AdminTestEvalutionModule { }
