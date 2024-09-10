import { Component } from '@angular/core';
import { UserService } from '../service/user.service';
import { StorageService } from '../service/storage.service';
import { AuthService } from '../service/aut.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrl: './admin-panel.component.css'
})
export class AdminPanelComponent {
  constructor(private userService: AuthService,
    private token: StorageService,
    private router: Router
  ) { }

  logOut() {
    this.token.clean();
    this.userService.logout().subscribe(data => {
      this.router.navigate(['/api/auth']);
    })
  }

}
