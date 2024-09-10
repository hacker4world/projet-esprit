import { Component } from '@angular/core';
import { UserDTO } from '../../../models/user.dto';
import { UserService } from '../../../service/user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent {
  users: UserDTO[] = [];
  currentUserId: any;
  selectedUser: UserDTO | null = null;
  userForm!: FormGroup;
  searchText: string = '';
  constructor(private userService: UserService,
    private cookieService: CookieService,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    console.log("jwtCookie = this.cookieService.get('jwtCookie');",this.cookieService.get('jwtCookie'));
    this.getAllUsers();
    this.resetForm();
    //this.getAllUsers();
  }


  getAllUsers(): void {
    this.userService.getAllUsers().subscribe(data => {
      this.users = data;
    });
  }
  resetForm() {
    this.userForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required],
      role: ['', Validators.required],
      userOption: ['', Validators.required]
    }, { validator: this.passwordMatchValidator });
  }
  passwordMatchValidator(form: FormGroup) {
    return form.get('password')?.value === form.get('confirmPassword')?.value
      ? null : { 'mismatch': true };
  }

  getUserById(id: number): void {
    this.userService.getUserById(id).subscribe(data => {
      this.selectedUser = data;
    });
  }
  onSubmit() {
    if (this.userForm.valid) {
      console.log(this.userForm.value);
      if (!this.currentUserId) {
        this.userService.createUser(this.userForm.value).subscribe(() => {
          this.getAllUsers();
          this.resetForm();
        });
      } else {
        this.userService.updateUser(this.currentUserId, this.userForm.value).subscribe(() => {
          this.getAllUsers();
          this.resetForm();
        });
      }
    } else {
      console.log('Form is invalid');
    }
  }
  deleteUser(id: number): void {
    this.userService.deleteUser(id).subscribe(() => {
      this.users = this.users.filter(user => user.id !== id);
    });
  }

  // Example method to select a user (could be used in the template)
  onSelectUser(id: any,selectedUser: UserDTO): void {
    if (id == this.currentUserId) {
      this.currentUserId = null; 
      this.resetForm();
    } else {
      this.currentUserId = id;
      this.userForm.patchValue({
        firstName: selectedUser.firstName,
        lastName: selectedUser.lastName,
        // Assuming you don't populate password and confirmPassword for security reasons
        role: selectedUser.role
      });
    
    }

  }

  // Example method to handle delete action from the template
  public onDeleteUser(id: any): void {
    if (confirm('Are you sure you want to delete this user?')) {
      this.deleteUser(id);
    }
  }
   // Method to filter users by name
   filterUsers(): void {
    if (this.searchText) {
      const lowerCaseSearchText = this.searchText.toLowerCase();
      this.users = this.users.filter(user =>
        user.firstName.toLowerCase().includes(lowerCaseSearchText) ||
        user.lastName.toLowerCase().includes(lowerCaseSearchText)
      );
    }
  }
}
