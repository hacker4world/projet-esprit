import { Component } from '@angular/core';
import { HiddenDataService } from '../../service/HiddenDataService';
import { StorageService } from '../../service/storage.service';
import { AuthService } from '../../service/aut.service';
import { UserDTO } from '../../models/user.dto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-verif-qr',
  templateUrl: './verif-qr.component.html',
  styleUrl: './verif-qr.component.css'
})
export class VerifQrComponent {
  hiddenData: any;
  userName: string = '';
  currentUser!: UserDTO;

  constructor(private hiddenDataService: HiddenDataService,
    private route: Router,
    private store: StorageService,
    private userService: AuthService
  ) { }
  ngOnInit(): void {
    this.hiddenDataService.getHiddenData().subscribe(data => {
      this.hiddenData = data;
      console.log("dqlksnklqndlknsqlkdnqlksd", data);

    });
  }
  resetPass() {
    console.log(this.userName);
    if (this.userName == this.hiddenData) {
      this.currentUser = this.store.getUser();
      if (this.currentUser.role == 'ADMIN') {
        this.route.navigate(['/api/dashboard']);
      } else {
        this.route.navigate(['/api/user']);
      }
    } else {

      this.store.clean();
      this.userService.logout().subscribe(data => {
        console.log("data", data);
      })
    }
  }
}
