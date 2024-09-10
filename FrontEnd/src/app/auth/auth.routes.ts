import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegesterComponent } from './regester/regester.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { VerifQrComponent } from './verif-qr/verif-qr.component';
export const routes: Routes = [

      {
        path: 'login',
        component:LoginComponent
      },
      {
        path: 'register',
        component:RegesterComponent
      },
      {
        path: 'forget-password',
        component:ForgotPasswordComponent
      },
      {
        path:'verify-qr',
        component:VerifQrComponent
      },
      { path: '', redirectTo: 'login', pathMatch: 'full' },
];
