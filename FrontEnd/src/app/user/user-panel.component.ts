import { Component, OnInit } from '@angular/core';
import { UserDTO } from '../models/user.dto';
import { StorageService } from '../service/storage.service';
import { AuthService } from '../service/aut.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-user-panel',
  templateUrl: './user-panel.component.html',
  styleUrl: './user-panel.component.css'
})
export class UserPanelComponent implements OnInit {
  currentUser!: UserDTO;

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
  ngOnInit(): void {
    this.currentUser=this.token.getUser();
  }
}
