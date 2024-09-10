import { Component } from '@angular/core';
import { AuthService } from '../../service/aut.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrl: './forgot-password.component.css'
})
export class ForgotPasswordComponent {
  userName: string = ''; 

  constructor(private auth: AuthService, private route: Router) {
  
  }

  resetPass(){
    console.log(this.userName);
    
    this.auth.forgetPassword(this.userName).subscribe(data=>{
      this.route.navigate(['/']);
    })
  }
}
