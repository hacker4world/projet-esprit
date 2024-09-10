import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { StorageService } from '../../service/storage.service';
import { UserDTO } from '../../models/user.dto';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-side-bar',
  standalone: true,
  imports: [RouterLink,CommonModule],
  templateUrl: './side-bar.component.html',
  styleUrl: './side-bar.component.css'
})
export class SideBarComponent {
  currentUser!: UserDTO;
  constructor(private stor: StorageService){

  }
  ngOnInit(): void {
   this.currentUser= this.stor.getUser();
  }

}
