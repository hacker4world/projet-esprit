import { NgModule } from '@angular/core';
import { CommonModule, NgFor } from '@angular/common';
import { AdminPanelComponent } from './admin-panel.component';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { SideBarComponent } from '../shared/side-bar/side-bar.component';
import { routes } from './admin-routings';
import { HttpClientModule } from '@angular/common/http';
import { UserService } from '../service/user.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ClassroomService } from '../service/classroom.service';
import { SubjectService } from '../service/subject.service';
import { AuthService } from '../service/aut.service';
import { DocumentService } from '../service/document.service';
import { ReclamationService } from '../service/reclamation.service';
import { NgApexchartsModule } from 'ng-apexcharts';

@NgModule({
  declarations: [AdminPanelComponent ],
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    NgApexchartsModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes),
    SideBarComponent,
    NgFor,
  ],
  exports: [RouterModule],
  providers: [
    UserService,
    ClassroomService,
    SubjectService,
    AuthService,
    DocumentService,
    ReclamationService,
  ],
})
export class AdminModule {}
