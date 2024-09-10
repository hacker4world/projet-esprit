import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { routes } from './user-profile-routings';
import { ProfileComponent } from './profile/profile.component';
import { TestResComponent } from './test-res/test-res.component';
import { UserProfileFinalResultByStudentComponent } from './user-profile-final-result-by-student/user-profile-final-result-by-student.component';



@NgModule({
  declarations: [
    ProfileComponent,
    TestResComponent,
    UserProfileFinalResultByStudentComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes)
  ]
})
export class ProfileModule { }
