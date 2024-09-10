import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ClassroomService } from '../../../service/classroom.service';

@Component({
  selector: 'app-admin-classroom-create',
  templateUrl: './admin-classroom-create.component.html',
  styleUrls: ['./admin-classroom-create.component.css']
})
export class AdminClassroomCreateComponent  implements OnInit {
  classroomForm!: FormGroup;
  levels = ['FirstGrade', 'SecondGrade', 'ThirdGrade']; // Replace with your actual levels
  errorMessage: string = "";

  constructor(
    private fb: FormBuilder,
    private classroomService: ClassroomService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.classroomForm = this.fb.group({
      groupName: ['', Validators.required],
      level: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.classroomForm.valid) {

      console.log("form value:")
      console.log(this.classroomForm.value);

      this.classroomService.post(this.classroomForm.value).subscribe(
        (res) => {
          this.router.navigate(['/api/dashboard/admin/admin-classroom/details/', res.id]);
        },
        (err) => {
          this.errorMessage = 'Error creating classroom. Please try again.';
          console.log("[HTTP ERROR] AdminClassroomCreateComponent -> onSubmit()", err);
        }
      );
    }
  }
}
