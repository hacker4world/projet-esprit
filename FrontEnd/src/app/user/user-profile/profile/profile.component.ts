import { Component, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { StorageService } from '../../../service/storage.service';
import { UserService } from '../../../service/user.service';
import { HttpErrorResponse } from '@angular/common/http';
import { UserDTO } from '../../../models/user.dto';
import { ResumeService } from '../../../service/resume.service';
import { resumeDTO } from '../../../models/resume.dto';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
onSubmitSaveResume() {

  
}
saveResume() {
  if (this.resumeForm.invalid) {
    return;
  }
  if(this.resumeUser?.id == null ){
    this.resumeService.createResume(this.currentUser.id??0,this.resumeForm.value).subscribe({
      next: (data) => {
        console.log(data);
        this.formReset();
      },
      error: (err) => {
        this.errorMessage = err.error.message;
      }
    });
  }else{
    this.resumeService.updateResume(this.currentUser.id??0,this.resumeUser.id ?? 0, this.resumeForm.value).subscribe({
      next: (data) => {
        console.log(data);
        this.formReset();
      },
      error: (err) => {
        this.errorMessage = err.error.message;
      }
    });
  }
}
  currentUser!: UserDTO;
  selectedFile: any;
  resumeUser!: resumeDTO;
saveUser() {
console.log(this.registerForm,"ssssssssssssssssssssssssssssssssssssssssssssssssss");

}
  @ViewChild('fileInput') fileInput!: ElementRef<HTMLInputElement>; // Reference to the file input element
  @ViewChild('pdfInput') pdfInput!: ElementRef<HTMLInputElement>; // Reference to the file input element

  registerForm!: FormGroup;
  resumeForm!:FormGroup;
  isSubmitted = false;
  errorMessage = '';
  imageData!: Blob;
  userImage!: HTMLElement | null;
  image!: string | Blob;

  constructor(private fb: FormBuilder,
    private storage: StorageService,
    private user: UserService,
    private resumeService: ResumeService
  ) { }

 

  passwordMatchValidator(form: FormGroup) {
    const password = form.get('password');
    const confirmPassword = form.get('confirmPassword');

    if (password && confirmPassword && password.value !== confirmPassword.value) {
      confirmPassword.setErrors({ mismatch: true });
    } else {
      confirmPassword?.setErrors(null);
    }

    return null;
  }
 

  ngOnInit(): void {
    this.currentUser=this.storage.getUser();
    this.getUserResume();
    this.formReset();
   this.getUserProfile();
   this.getUserResume();
  }
  getUserResume() {
    this.resumeService.getResumeById(this.currentUser.id??0).subscribe(data=>{
      this.resumeUser=data;
      this.formReset();
    })
  }
  formReset() {
    this.resumeForm = this.fb.group({
      email: [this.resumeUser?.email??"", [Validators.required, Validators.email]],
      dateOfBirth: [this.resumeUser?.dateOfBirth??"", [Validators.required, Validators.minLength(3)]],
      education: [this.resumeUser?.education??"",Validators.required],
      experience: [this.resumeUser?.experience??"", Validators.required],
      skils: [this.resumeUser?.skils??"", Validators.required],
      langue: [this.resumeUser?.langue??"", Validators.required]
    });
    this.registerForm = this.fb.group({
      firstName: [this.currentUser?.firstName, [Validators.required, Validators.minLength(3)]],
      lastName: [this.currentUser?.lastName, [Validators.required, Validators.minLength(3)]],
      email: [this.currentUser?.email, [Validators.required, Validators.email]],
       password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required]
    }, { validator: this.passwordMatchValidator });
  }
  getUserProfile() {
    this.image = this.currentUser.profilePicURI ?? '';
    this.user.downloadFile(this.image).subscribe({
      next: (data) => {
        this.imageData = data;
        // Assuming data is a Blob containing image data
        const imageURL = window.URL.createObjectURL(data);
        const image = document.getElementById('user-image') as HTMLImageElement;
        if (image) {
          image.src = imageURL; // Now you can access the src property
        } else {
          console.error('Element with ID "user-image" not found');
        }
      },
      error: (err) => {
        console.error('Error fetching image', err);
      }
    });
  }

  get f() { return this.registerForm.controls; }
  onSubmit(): void {
    this.isSubmitted = true;
    if (this.registerForm.invalid) {
      return;
    }
    this.user.updateUser(this.currentUser.id ?? 0, this.registerForm.value).subscribe({
      next: (data) => {
        console.log(data);
        this.storage.saveUser(data);
      },
      error: (err) => {
        this.errorMessage = err.error.message;
      }
    });
  }
  openFileDialog() {
    this.fileInput.nativeElement.click(); // Programmatically trigger the file input click
  }

  onFileChange(event: any) {
    const reader = new FileReader();
    if (event.target.files && event.target.files[0]) {
      reader.onload = (e: any) => {
        // Update the image source with the temporary URL from the selected file
        this.userImage = e.target.result;
      };
      reader.readAsDataURL(event.target.files[0]);

      // Upload the selected image file to the server
      this.uploadImage(event.target.files[0]);
    }
  }
  onPdfChange(event: any) {
    const file = event.target.files[0];
  
    // Check if a file is selected
    if (file) {
      this.selectedFile = file;
    } else {
      this.selectedFile = null;
    }
  }
  openPdfDialog() {
    this.pdfInput.nativeElement.click(); // Programmatically trigger the file input click
  }
  uploadImage(imageFile: File) {
    this.user.updateProfilePicture( this.currentUser.id?? 0, imageFile)
      .subscribe(
        (userDTO: UserDTO) => {
          console.log('Image upload successful:', userDTO);
          // Update user data or display success message (e.g., update user profile picture displayed)
          let curentUser =this.currentUser;
          curentUser.profilePicURI=userDTO.profilePicURI;
          this.storage.saveUser(curentUser);
          this.getUserProfile();
        },
        (error: HttpErrorResponse) => {
          console.error('Error uploading image:', error);
          // Handle upload errors (e.g., display error message)
        }
      );
  }
}