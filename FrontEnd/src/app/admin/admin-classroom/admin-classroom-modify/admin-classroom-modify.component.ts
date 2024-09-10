import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ClassroomService } from '../../../service/classroom.service';

@Component({
  selector: 'app-admin-classroom-modify',
  templateUrl: './admin-classroom-modify.component.html',
  styleUrls: ['./admin-classroom-modify.component.css']
})
export class AdminClassroomModifyComponent implements OnInit {
  classroomForm!: FormGroup;
  levels = ['FirstGrade', 'SecondGrade', 'ThirdGrade'];
  classroomId: any =this.route.snapshot.paramMap.get('id');
  errorMessage: string = "";

  constructor(
    private fb: FormBuilder,
    private classroomService: ClassroomService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.classroomForm = this.fb.group({
      groupName: ['', Validators.required],
      level: ['', Validators.required]
    });

    this.route.paramMap.subscribe(params => {
      this.loadClassroomDetails(this.classroomId);
    });
  }

  loadClassroomDetails(id: number): void {
    this.classroomService.getClassroomById(id).subscribe(
      (data) => {
        this.classroomForm.patchValue({
          groupName: data.groupName,
          level: data.level
        });
      },
      (error) => {
        console.error("[HTTP ERROR] AdminClassroomModifyComponent -> loadClassroomDetails()", error);
        this.errorMessage = 'Error loading classroom details';
      }
    );
  }

  onSubmit(): void {
    if (this.classroomForm.valid) {
      const updateRequest = {
        id: this.classroomId,
        ...this.classroomForm.value
      };

      this.classroomService.put(updateRequest).subscribe(
        (res) => {
          this.router.navigate(['/api/dashboard/admin/admin-classroom/details', this.classroomId]);
        },
        (err) => {
          console.error("[HTTP ERROR] AdminClassroomModifyComponent -> onSubmit()", err);
          this.errorMessage = 'Error updating classroom. Please try again.';
        }
      );
    }
  }
}
