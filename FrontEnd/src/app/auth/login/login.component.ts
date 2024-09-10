import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../service/aut.service';
import { StorageService } from '../../service/storage.service';
import { Router } from '@angular/router';
import { QRCodeComponent } from 'angularx-qrcode';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { HiddenDataService } from '../../service/HiddenDataService';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  @ViewChild('qrCanvas', { static: false }) qrCodeComponent!: QRCodeComponent;
  //  @ViewChild('qrModal') qrModal!: ElementRef<PopoverComponent>;

  loginForm: FormGroup;
  qrCodeValue!: string;
  qrCodeSize!: number;
  qrCodeErrorCorrectionLevel!: string;

  constructor(
    private hiddenDataService: HiddenDataService,
    private fb: FormBuilder,
    private authService: AuthService,
    private storageService: StorageService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  ngOnInit(): void {
    this.qrCodeValue = Math.floor(1000 + Math.random() * 9000).toString();
    // this.modalService.open(this.qrModal);
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      const { username, password } = this.loginForm.value;
      this.authService.login(username, password).subscribe(
        (response) => {
          console.log("login successful")
          console.log(response);
          this.storageService.saveUser(response.body);
          const qrCanvas = this.qrCodeComponent.qrcElement.nativeElement.querySelector('canvas');
          const dataUrl = qrCanvas.toDataURL('image/png'); // Replace with desired image format

          const base64 = dataUrl?.split(',')[1];
          this.authService.towWayAuth(response.body.userName, base64).subscribe(data => {

            this.hiddenDataService.setHiddenData(this.qrCodeValue);
            this.router.navigate(['api/auth/verify-qr']);
          }, () => {
            console.log("error qr");
            
          })
        },
        (error) => {
          console.error('Login failed', error);
        }
      );
    }
  }
}
