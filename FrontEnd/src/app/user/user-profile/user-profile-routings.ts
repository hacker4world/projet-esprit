import { Routes } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { UserProfileFinalResultByStudentComponent } from './user-profile-final-result-by-student/user-profile-final-result-by-student.component';
export const routes: Routes = [

    {
      path: 'profile',
      component: ProfileComponent,
    },
    {
      path: 'finalReuslt/:id',
      component: UserProfileFinalResultByStudentComponent
    }
];
