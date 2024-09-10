import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { RegesterComponent } from './regester/regester.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { routes } from './auth.routes';
import { HttpClientModule, HttpHeaders } from '@angular/common/http';
import { AuthService } from '../service/aut.service';
import { StorageService } from '../service/storage.service';
import { QRCodeModule } from 'angularx-qrcode';
import { VerifQrComponent } from './verif-qr/verif-qr.component';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';

@NgModule({
  declarations: [
    LoginComponent,
    RegesterComponent,
    ForgotPasswordComponent,
    VerifQrComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    QRCodeModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes),
    SweetAlert2Module.forRoot(),
  ],
  providers: [AuthService, StorageService],
  exports: [RouterModule],
})
export class AuthModule {}
